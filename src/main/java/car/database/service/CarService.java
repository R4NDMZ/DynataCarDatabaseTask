package car.database.service;

import car.database.entity.Car;
import car.database.repository.CarRepository;
import car.database.repository.EntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService extends AbstractEntityService<Car> {

    public CarService(EntityRepository<Car> repository) {
        super(repository);
    }

    @Transactional
    public List<Car> getAlreadyPaidAndNotSent() {
        return ((CarRepository) repository).getAlreadyPaidAndNotSent();
    }

    @Override
    protected Class<Car> getEntityClass() {
        return Car.class;
    }

}
