package car.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car extends AbstractEntity {

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private String type;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "year_of_manufacture")
    private Short yearOfManufacture;

    @Column(name = "calculated_value")
    private Integer calculatedValue;

    @Column(name = "driven_distance")
    private Integer drivenDistance;

    @Column(name = "is_sent")
    private Short isSent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cars_of_people",
            joinColumns = @JoinColumn(
                    name = "car_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "person_id",
                    referencedColumnName = "id"
            )
    )
    private Person owner;

}
