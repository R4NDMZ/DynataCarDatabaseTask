package car.database;

import car.database.entity.Car;
import car.database.entity.EmailTemplate;
import car.database.entity.Person;
import car.database.service.CarService;
import car.database.service.EmailTemplateService;
import car.database.utility.DataResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmailProcessor {

    private final CarService carService;
    private final EmailTemplateService emailTemplateService;

    private Map<Long, String> emailTemplates;
    private Map<Person, List<Car>> carsByPerson;

    public EmailProcessor(CarService carService, EmailTemplateService emailTemplateService) {
        this.carService = carService;
        this.emailTemplateService = emailTemplateService;
    }

    public void processUnsentEmails() {
        fetchData();

        for (Map.Entry<Person, List<Car>> entry : carsByPerson.entrySet()) {
            String dynamicEmail = emailTemplates.get(entry.getKey().getLanguage());

            dynamicEmail = DataResolver.resolvePersonData(dynamicEmail, entry.getKey());
            dynamicEmail = DataResolver.resolveCarsData(dynamicEmail, entry.getValue());
            log.info(dynamicEmail);
        }
    }

    private void fetchData() {
        emailTemplates = emailTemplateService.getAll().stream().collect(Collectors.toMap(EmailTemplate::getId, EmailTemplate::getText));
        carsByPerson = carService.getAlreadyPaidAndNotSent().stream().collect(Collectors.groupingBy(Car::getOwner));
    }

}
