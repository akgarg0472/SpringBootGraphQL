package com.akgarg.graphql.car;

import com.akgarg.graphql.car.types.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Component
@RequiredArgsConstructor
public final class CarDao {

    private final CarRepository carRepository;

    public Car addCar(final Car car) {
        try {
            return this.carRepository.save(car);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error adding new car", e);
        }
    }

    public Optional<Car> getCarById(final String id) {
        try {
            return this.carRepository.findById(id);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error fetching car by id: " + id, e);
        }
    }

    public Collection<Car> getCarByManufacturer(final String manufacturer) {
        try {
            return this.carRepository.findCarsByManufacturerContainingIgnoreCase(manufacturer);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error fetching cars by manufacturer: " + manufacturer, e);
        }
    }

    public Collection<Car> getCarByManufacturingYear(final Integer manufacturingYear) {
        try {
            return this.carRepository.findCarsByManufacturingYearEquals(manufacturingYear);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error fetching cars by manufacturing year: " + manufacturingYear, e);
        }
    }

    public Collection<Car> getCarByTransmissionType(final String transmissionType) {
        try {
            return this.carRepository.findCarsByTransmissionTypeContainingIgnoreCase(transmissionType);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error fetching cars by transmission type: " + transmissionType, e);
        }
    }

    public Collection<Car> getCarByModel(final String model) {
        try {
            return this.carRepository.findCarsByModelContainingIgnoreCase(model);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error fetching cars by model: " + model, e);
        }
    }

    public Collection<Car> getAllCars() {
        try {
            return this.carRepository.findAll();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error fetching all cars", e);
        }
    }

    public int deleteCar(final String id) {
        try {
            return this.carRepository.deleteCarById(id);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error deleting car with id: " + id, e);
        }
    }

    Car updateCar(final Car car) {
        try {
            return this.carRepository.save(car);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CarException("Error updating car", e);
        }
    }

}
