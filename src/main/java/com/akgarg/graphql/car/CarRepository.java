package com.akgarg.graphql.car;

import com.akgarg.graphql.car.types.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findCarsByManufacturerContainingIgnoreCase(String manufacturer);

    List<Car> findCarsByManufacturingYearEquals(Integer manufacturingYear);

    List<Car> findCarsByTransmissionTypeContainingIgnoreCase(String transmissionType);

    List<Car> findCarsByModelContainingIgnoreCase(String model);

    @Modifying
    @Transactional
    int deleteCarById(String id);

}
