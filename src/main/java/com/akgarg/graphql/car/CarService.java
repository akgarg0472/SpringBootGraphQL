package com.akgarg.graphql.car;

import com.akgarg.graphql.car.inputs.CarInput;
import com.akgarg.graphql.car.inputs.QueryParams;
import com.akgarg.graphql.car.types.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Service
@RequiredArgsConstructor
public class CarService {

    private static final Logger logger = Logger.getLogger(CarService.class.getSimpleName());

    private final CarDao carDao;

    public Car addCar(final CarInput carInput) {
        logger.info("Adding car: " + carInput);

        CarUtils.validateAddCarRequest(carInput);

        final String id = UUID.randomUUID().toString().replace("-", "");
        final Car car = new Car(id);
        mapCarInputToCar(carInput, car);

        return this.carDao.addCar(car);
    }

    public Collection<Car> getAllCars() {
        logger.info("Fetching all cars");
        return this.carDao.getAllCars();
    }

    public Collection<Car> getCarByModel(final String model) {
        logger.info("Fetching cars by model: " + model);
        return this.carDao.getCarByModel(model);
    }

    public Collection<Car> getCarByManufacturer(final String manufacturer) {
        logger.info("Fetching cars by manufacturer: " + manufacturer);
        return this.carDao.getCarByManufacturer(manufacturer);
    }

    public Collection<Car> getCarByManufacturingYear(final Integer manufacturingYear) {
        logger.info("Fetching cars by mfg year: " + manufacturingYear);
        return carDao.getCarByManufacturingYear(manufacturingYear);
    }

    public Collection<Car> getCarByTransmissionType(final String transmissionType) {
        logger.info("Fetching cars by transmission type: " + transmissionType);
        return this.carDao.getCarByTransmissionType(transmissionType);
    }

    public Car updateCar(final String id, final CarInput carInput) {
        final Car car = this.carDao.getCarById(id)
                .orElseThrow(() -> new CarException("No car found with id: " + id, null));

        this.mapCarInputToCar(carInput, car);

        return this.carDao.updateCar(car);
    }

    public Car deleteCar(final String id) {
        logger.warning("Deleting car: " + id);

        final Car car = this.carDao.getCarById(id)
                .orElseThrow(() -> new CarException("No car found with id: " + id, null));

        final int deleteCount = this.carDao.deleteCar(id);

        return deleteCount == 1 ? car : null;
    }

    public Collection<Car> getCarByParams(final Collection<QueryParams> params) {
        logger.info("[CarService] fetching cars for params: " + params);

        try {
            final Map<String, Object> parameters = new HashMap<>(params.size());

            for (final QueryParams param : params) {
                parameters.put(param.getKey(), param.getValue());
            }

            final Object model = parameters.get("model");
            final Object manufacturer = parameters.get("manufacturer");
            final Object manufacturingYear = parameters.get("manufacturingYear");

            // todo: this logic could be improved using Specifications provided by JPA using Criteria API
            Collection<Car> cars = this.carDao.getAllCars();

            if (CarUtils.isStringObjectValid(model)) {
                cars = cars.stream()
                        .filter(car -> ((String) model).equalsIgnoreCase(car.getModel()))
                        .toList();
            }

            if (CarUtils.isStringObjectValid(manufacturer)) {
                cars = cars.stream()
                        .filter(car -> ((String) manufacturer).equalsIgnoreCase(car.getManufacturer()))
                        .toList();
            }

            if (CarUtils.isNumberObjectValid(manufacturingYear)) {
                cars = cars.stream()
                        .filter(car -> manufacturingYear.equals(car.getManufacturingYear()))
                        .toList();
            }

            return cars;
        } catch (Throwable e) {
            throw new CarException("Error fetching cars by params", e);
        }
    }

    private void mapCarInputToCar(final CarInput carInput, final Car car) {
        car.setManufacturer(carInput.getManufacturer());
        car.setModel(carInput.getModel());
        car.setTransmissionType(carInput.getTransmissionType());
        car.setManufacturingYear(carInput.getManufacturingYear());
        car.setManufacturingMonth(carInput.getManufacturingMonth());
        car.setManufacturingDate(carInput.getManufacturingDate());
        car.setMileage(carInput.getMileage());
    }

}
