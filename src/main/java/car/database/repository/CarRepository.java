package car.database.repository;

import car.database.entity.Car;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends EntityRepository<Car> {

    @Query("""
            SELECT car
            FROM Car car
            WHERE car.calculatedValue > 0 AND car.isSent = 0
            """)
    List<Car> getAlreadyPaidAndNotSent();

}
