package car.database.repository;

import car.database.entity.Car;
import car.database.entity.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends EntityRepository<Person> {

    @Query("""
            SELECT person.cars
            FROM Person person
            LEFT JOIN person.cars
            WHERE person.id = :id
            """)
    List<Car> getCarsOwnedBy(Long id);

}
