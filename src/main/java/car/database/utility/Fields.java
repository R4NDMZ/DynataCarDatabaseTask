package car.database.utility;

public final class Fields {

    private Fields() {
    }

    public static class Car {
        private Car() {
        }

        public static final String LOOP_BEGIN = "<carsLoopBegin>";
        public static final String LOOP_END = "<carsLoopEnd>";

        public static final String BRAND = "<brand>";
        public static final String CALCULATED_VALUE = "<calculatedValue>";
        public static final String DRIVEN_DISTANCE = "<drivenDistance>";
        public static final String PLATE_NUMBER = "<plateNumber>";
        public static final String TYPE = "<type>";
        public static final String YEAR_OF_MANUFACTURE = "<yearOfManufacture>";
    }

    public static class Person {
        private Person() {
        }

        public static final String COUNTRY = "<country>";
        public static final String DATE_OF_BIRTH = "<dateOfBirth>";
        public static final String NAME = "<name>";
    }

}
