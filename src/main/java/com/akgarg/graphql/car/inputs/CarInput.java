package com.akgarg.graphql.car.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Getter
@Setter
@ToString
public class CarInput {

    private String manufacturer;
    private String model;
    private Integer manufacturingYear;
    private Integer manufacturingMonth;
    private Integer manufacturingDate;
    private Double mileage;
    private String transmissionType;

}
