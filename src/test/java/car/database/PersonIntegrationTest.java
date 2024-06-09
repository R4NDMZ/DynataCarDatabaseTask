package car.database;

import car.database.entity.Car;
import car.database.entity.EmailTemplate;
import car.database.entity.Person;
import car.database.repository.CarRepository;
import car.database.repository.EmailTemplateRepository;
import car.database.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest()
@ActiveProfiles("test")
@Slf4j
class PersonIntegrationTest {

    private final Long TEST_ID = -1L;
    private final Long OTHER_TEST_ID = -2L;
    private final Long WRONG_TEST_ID = -5L;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private PersonRepository personRepository;

    private Car testCar;
    private Car otherTestCar;

    @Test
    void testGetCarsOwnedBySuccess() {
        List<Car> cars = personRepository.getCarsOwnedBy(TEST_ID);
        List<Long> carIds = cars.stream().map(Car::getId).toList();

        Assertions.assertThat(cars).isNotNull();
        Assertions.assertThat(carIds)
            .isNotNull()
            .contains(TEST_ID)
            .contains(OTHER_TEST_ID);
        Assertions.assertThat(cars).hasSameSizeAs(carIds);
    }

    @Test
    void testGetCarsOwnedByFailed() {
        List<Car> cars = personRepository.getCarsOwnedBy(WRONG_TEST_ID);

        Assertions.assertThat(cars).isEmpty();
    }

    private Car createTestCar(Long id) {
        Car car = new Car();
        car.setId(id);
        car.setBrand("Opel");
        car.setType("Vectra");
        car.setPlateNumber("PPS 123");
        car.setYearOfManufacture(Short.valueOf("1974"));
        car.setCalculatedValue(15000);
        car.setDrivenDistance(5500);
        car.setIsSent(Short.valueOf("1"));
        return car;
    }

    @BeforeEach
    public void setUp() {
        testCar = createTestCar(TEST_ID);
        otherTestCar = createTestCar(OTHER_TEST_ID);

        carRepository.save(testCar);
        carRepository.save(otherTestCar);

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setId(TEST_ID);
        emailTemplate.setText("test");
        emailTemplateRepository.save(emailTemplate);

        Person person = new Person();
        person.setId(TEST_ID);
        person.setName("Példa Béla");
        person.setDateOfBirth(new Date(169776000000L));
        person.setCountry("Hungary");
        person.setLanguage(TEST_ID);
        Set<Car> cars = new HashSet<>();
        cars.add(testCar);
        cars.add(otherTestCar);
        person.setCars(cars);
        personRepository.save(person);
    }

    @AfterEach
    public void cleanUp() {
        carRepository.delete(testCar);
        carRepository.delete(otherTestCar);
        personRepository.deleteById(TEST_ID);
        emailTemplateRepository.deleteById(TEST_ID);
    }

}
