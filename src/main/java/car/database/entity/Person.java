package car.database.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "person_data")
@Getter
@Setter
public class Person extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "country")
    private String country;

    @Column(name = "language_id")
    private Long language;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cars_of_people",
            joinColumns = @JoinColumn(
                    name = "person_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "car_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Car> cars;

}
