package car.database;

import car.database.entity.Car;
import car.database.entity.EmailTemplate;
import car.database.entity.Person;
import car.database.repository.CarRepository;
import car.database.repository.EmailTemplateRepository;
import car.database.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EndPointTest {

	private final TestRestTemplate TEST_REST_TEMPLATE = new TestRestTemplate();

	private final Long TEST_ID = -1L;
	private final Long WRONG_TEST_ID = -5L;

	@LocalServerPort
	private int port;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Test
	void testGetCarByIdSuccess() {
		ResponseEntity<String> response = TEST_REST_TEMPLATE.getForEntity("http://localhost:" + port + "/api/cars/" + TEST_ID, String.class);
		Assertions.assertThat(response).isNotNull();
	}

	@Test
	void testGetCarByIdFailed() {
		ResponseEntity<String> response = TEST_REST_TEMPLATE.getForEntity("http://localhost:" + port + "/api/cars/" + WRONG_TEST_ID, String.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	void testGetCarByOwnerIdSuccess() {
		ResponseEntity<String> response = TEST_REST_TEMPLATE.getForEntity("http://localhost:" + port + "/api/people/" + TEST_ID + "/cars", String.class);

		Assertions.assertThat(response).isNotNull();
	}

	@Test
	void testGetCarByOwnerIdFailed() {
		ResponseEntity<String> response = TEST_REST_TEMPLATE.getForEntity("http://localhost:" + port + "/api/people/" + WRONG_TEST_ID + "/cars", String.class);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@BeforeEach
	public void setUp() {
		Car car = new Car();
		car.setId(TEST_ID);
		car.setBrand("Opel");
		car.setType("Vectra");
		car.setPlateNumber("PPS 123");
		car.setYearOfManufacture(Short.valueOf("1974"));
		car.setCalculatedValue(15000);
		car.setDrivenDistance(5500);
		car.setIsSent(Short.valueOf("1"));
		carRepository.save(car);

		EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setId(TEST_ID);
		emailTemplate.setText("test");
		emailTemplateRepository.save(emailTemplate);

		Person person = new Person();
		person.setId(TEST_ID);
		person.setName("Példa Béla");
		person.setDateOfBirth(new Date(169776000000L));
		person.setCountry("Hungary");
		person.setLanguage(TEST_ID);
		Set<Car> cars = new HashSet<>();
		cars.add(car);
		person.setCars(cars);
		personRepository.save(person);
	}

	@AfterEach
	public void cleanUp() {
		personRepository.deleteById(TEST_ID);
		carRepository.deleteById(TEST_ID);
		emailTemplateRepository.deleteById(TEST_ID);
	}

}
