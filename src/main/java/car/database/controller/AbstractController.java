package car.database.controller;

import car.database.entity.Entity;
import car.database.service.AbstractEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
public abstract class AbstractController<E extends Entity> {

    public static final String API = "/api";

    protected final AbstractEntityService<E> service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) {
        ResponseEntity<String> response;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response = ResponseEntity.ok(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.getById(id)));
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return response;
    }

}
