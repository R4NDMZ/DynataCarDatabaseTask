package car.database.utility;

import car.database.entity.Car;
import car.database.entity.Person;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DataResolver {

    private DataResolver() {
    }

    private static Pattern compilePattern(String searchFor) {
        return Pattern.compile(searchFor, Pattern.CASE_INSENSITIVE);
    }

    private static String replacePattern(String text, String searchFor, String replacement) {
        Pattern pattern = compilePattern(searchFor);
        return replacePattern(text, pattern, replacement);
    }

    private static String replacePattern(String text, Pattern pattern, String replacement) {
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.replaceAll(replacement);
        }

        return text;
    }

    private static int findCarLoopBegin(String text) {
        Pattern pattern = compilePattern(Fields.Car.LOOP_BEGIN);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.end();
        }

        return 0;
    }

    private static int findCarLoopEnd(String text, int start) {
        Pattern pattern = compilePattern(Fields.Car.LOOP_END);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find(start)) {
            return matcher.start() - 1;
        }

        return 0;
    }

    public static String resolvePersonData(String original, Person person) {
        String resolved = replacePattern(original, Fields.Person.NAME, person.getName());
        resolved = replacePattern(resolved, Fields.Person.COUNTRY, person.getCountry());
        return replacePattern(resolved, Fields.Person.DATE_OF_BIRTH, person.getDateOfBirth().toString());
    }

    public static String resolveCarsData(String original, List<Car> cars) {
        int carsLoopStart = findCarLoopBegin(original);
        int carsLoopEnd = findCarLoopEnd(original, carsLoopStart);

        String carsLoop = original.substring(carsLoopStart, carsLoopEnd);

        StringBuilder finalMessageBuilder = new StringBuilder(original.substring(0, carsLoopStart - Fields.Car.LOOP_BEGIN.length()));
        StringBuilder carsLoopBuilder = new StringBuilder();

        Pattern brandPattern = compilePattern(Fields.Car.BRAND);
        Pattern typePattern = compilePattern(Fields.Car.TYPE);
        Pattern plateNumberPattern = compilePattern(Fields.Car.PLATE_NUMBER);
        Pattern yearOfManufacturePattern = compilePattern(Fields.Car.YEAR_OF_MANUFACTURE);
        Pattern drivenDistancePattern = compilePattern(Fields.Car.DRIVEN_DISTANCE);
        Pattern calculatedValuePattern = compilePattern(Fields.Car.CALCULATED_VALUE);

        for (Car car : cars) {
            String currentCarData = replacePattern(carsLoop, brandPattern, car.getBrand());
            currentCarData = replacePattern(currentCarData, typePattern, car.getType());
            currentCarData = replacePattern(currentCarData, plateNumberPattern, car.getPlateNumber());
            currentCarData = replacePattern(currentCarData, yearOfManufacturePattern, car.getYearOfManufacture().toString());
            currentCarData = replacePattern(currentCarData, drivenDistancePattern, car.getDrivenDistance().toString());
            currentCarData = replacePattern(currentCarData, calculatedValuePattern, car.getCalculatedValue().toString());

            carsLoopBuilder.append(currentCarData);
        }

        finalMessageBuilder.append(carsLoopBuilder.toString());
        finalMessageBuilder.append(original.substring(carsLoopEnd + Fields.Car.LOOP_END.length() + 1));
        return finalMessageBuilder.toString();
    }

}
