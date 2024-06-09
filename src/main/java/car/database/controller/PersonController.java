package car.database.controller;

import car.database.entity.Car;
import car.database.entity.Person;
import car.database.service.AbstractEntityService;
import car.database.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = AbstractController.API + "/people")
public class PersonController extends AbstractController<Person> {

    @Autowired
    public PersonController(AbstractEntityService<Person> service) {
        super(service);
    }

    @GetMapping(path = "/{id}/cars")
    public ResponseEntity<String> getCarsByPersonId(@PathVariable Long id) {
        ResponseEntity<String> response;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Car> cars = ((PersonService) service).getCarsOwnedBy(id);

            if (cars.isEmpty()) {
                response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                response = ResponseEntity.ok(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars));
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return response;
    }

}
