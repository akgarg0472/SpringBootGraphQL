package com.akgarg.graphql.car;

import com.akgarg.graphql.car.inputs.CarInput;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
final class CarUtils {

    private CarUtils() {
        throw new UnsupportedOperationException();
    }

    static void validateAddCarRequest(final CarInput carInput) {
        if (carInput == null) throw new CarException("Car input is invalid", null);

        if (isStringInvalid(carInput.getManufacturer())) {
            throw new CarException("Invalid car manufacturer", null);
        }

        if (isStringInvalid(carInput.getModel())) {
            throw new CarException("Invalid car model", null);
        }

        if (isStringInvalid(carInput.getTransmissionType())) {
            throw new CarException("Invalid car transmission type", null);
        }

        if (!isNumberObjectValid(carInput.getManufacturingYear())) {
            throw new CarException("Invalid car manufacturing year", null);
        }

        if (!isNumberObjectValid(carInput.getManufacturingMonth())) {
            throw new CarException("Invalid car manufacturing month", null);
        }

        if (!isNumberObjectValid(carInput.getManufacturingDate())) {
            throw new CarException("Invalid car manufacturing date", null);
        }

        if (!isNumberObjectValid(carInput.getMileage())) {
            throw new CarException("Invalid car mileage", null);
        }
    }

    static boolean isStringInvalid(final String string) {
        return string == null || string.trim().length() == 0;
    }

    public static boolean isStringObjectValid(
            final Object stringObject
    ) {
        return stringObject instanceof String && ((String) stringObject).trim().length() > 0;
    }

    public static boolean isNumberObjectValid(final Object numberObject) {
        return numberObject instanceof Number;
    }

}
