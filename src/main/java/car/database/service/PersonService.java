package car.database.service;

import car.database.entity.Car;
import car.database.entity.Person;
import car.database.repository.EntityRepository;
import car.database.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService extends AbstractEntityService<Person> {

    public PersonService(EntityRepository<Person> repository) {
        super(repository);
    }

    @Transactional
    public List<Car> getCarsOwnedBy(Long id) {
        return ((PersonRepository) repository).getCarsOwnedBy(id);
    }

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

}
