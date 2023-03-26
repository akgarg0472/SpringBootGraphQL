package com.akgarg.graphql.car;

import com.akgarg.graphql.car.inputs.CarInput;
import com.akgarg.graphql.car.inputs.QueryParams;
import com.akgarg.graphql.car.types.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
// @RestController
@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @MutationMapping("addCar")
    public Car addCar(@Argument("car") final CarInput addCarInput) {
        return this.carService.addCar(addCarInput);
    }

    @QueryMapping("allCars")
    public Collection<Car> allCars() {
        return this.carService.getAllCars();
    }

    @QueryMapping("carByParams")
    public Collection<Car> carByProps(@Argument("params") final Collection<QueryParams> params) {
        return this.carService.getCarByParams(params);
    }

    @QueryMapping("carByModel")
    public Collection<Car> carByModel(@Argument("model") final String model) {
        return this.carService.getCarByModel(model);
    }

    @QueryMapping("carByManufacturer")
    public Collection<Car> carByManufacturer(@Argument("manufacturer") final String manufacturer) {
        return this.carService.getCarByManufacturer(manufacturer);
    }

    @QueryMapping("carByManufacturingYear")
    public Collection<Car> carByManufacturingYear(@Argument("mfgYear") final Integer manufacturingYear) {
        return this.carService.getCarByManufacturingYear(manufacturingYear);
    }

    @QueryMapping("carByTransmissionType")
    public Collection<Car> carByTransmissionType(@Argument("transmissionType") final String transmissionType) {
        return this.carService.getCarByTransmissionType(transmissionType);
    }

    @MutationMapping("updateCar")
    public Car updateCar(@Argument("id") final String id, @Argument("updateCar") final CarInput updateCarInput) {
        return this.carService.updateCar(id, updateCarInput);
    }

    @MutationMapping("deleteCar")
    public Car deleteCar(@Argument("id") final String id) {
        return this.carService.deleteCar(id);
    }

}
