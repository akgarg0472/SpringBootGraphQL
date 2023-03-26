package com.akgarg.graphql.car.types;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    private String id;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer manufacturingYear;

    @Column(nullable = false)
    private Integer manufacturingMonth;

    @Column(nullable = false)
    private Integer manufacturingDate;

    @Column(nullable = false)
    private Double mileage;

    @Column(nullable = false)
    private String transmissionType;

    public Car(final String id) {
        this.id = id;
    }

}
