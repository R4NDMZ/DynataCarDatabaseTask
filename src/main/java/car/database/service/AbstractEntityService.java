package car.database.service;

import car.database.entity.Entity;
import car.database.repository.EntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractEntityService<E extends Entity> {

    protected final EntityRepository<E> repository;

    protected abstract Class<E> getEntityClass();

    @Transactional
    public E getById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<E> getAll() {
        return repository.findAll();
    }

}
