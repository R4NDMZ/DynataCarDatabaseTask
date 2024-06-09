package car.database.controller;

import car.database.entity.Car;
import car.database.service.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AbstractController.API + "/cars")
public class CarController extends AbstractController<Car> {

    @Autowired
    public CarController(AbstractEntityService<Car> service) {
        super(service);
    }

}
