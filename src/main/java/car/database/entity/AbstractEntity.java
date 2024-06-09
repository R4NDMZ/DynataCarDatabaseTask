package car.database.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@MappedSuperclass
@Slf4j
@Getter
@Setter
public class AbstractEntity implements Entity {

    @Id
    private Long id;

}
