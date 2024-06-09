INSERT INTO email_templates (id, text)
VALUES (1, E'Dear <name> (country: <country>, date of birth: <dateOfBirth>)!\n\nYou can read here the description of your uploaded cars:\n\n<carsLoopBegin>\nBrand: <brand>\nType: <type>\nPlate number: <plateNumber>\nYear of manufacture: <yearOfManufacture>\nDriven distance (km): <drivenDistance>\n\nBased on the data above our system considers the following market price that suits to your car: <calculatedValue> Euro.\n\n<carsLoopEnd>\nThank you for using our services!\n\nKindest regards,\nTeam CarEvaluator\n'),
       (2, E'Kedves <name> (ország: <country>, születési idő: <dateOfBirth>)!\n\nAz ön által feltöltött autók jellemzését alább olvashatja:\n\n<carsLoopBegin>\nMárka: <brand>\nTípus: <type>\nRendszám: <plateNumber>\nGyártási év: <yearOfManufacture>\nMegtett kilométer: <drivenDistance>\n\nA fenti adatok alapján rendszerünk a következő piaci értéket tartja reálisnak az Ön autója esetén: <calculatedValue> Euro.\n\n<carsLoopEnd>\nKöszönjük, hogy igénybe vette szolgáltatásunkat!\n\nÜdvözlettel,\nA CarEvaluator csapata\n');

INSERT INTO person_data (id, name, date_of_birth, country, language_id)
VALUES (1, 'Jake Greenfield', '1974-08-15', 'United Kingdom', 1),
       (2, 'Horváth Hedvig', '1982-02-19', 'Hungary', 2),
       (3, 'Erwin Lefavre', '1969-11-05', 'France', 1);

INSERT INTO cars (id, brand, "type", plate_number, year_of_manufacture, calculated_value, driven_distance, is_sent)
VALUES (1, 'Opel', 'Vectra', 'UK 123 45678', 2008, 11140, 125000, 0),
       (2, 'Mini', 'Cooper', 'UK 456 12345', 2015, 0, 10000, 0),
       (3, 'Suzuki', 'Swift', 'MTK 128', 2014, 12295, 26000, 1),
       (4, 'Peugeot', '206', 'FR 4567 TT', 2004, 3900, 195700, 0),
       (5, 'Citroen', 'C4 cactus', 'FR 8912 CC', 2014, 15750, 36500, 0);

INSERT INTO cars_of_people (person_id, car_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (3, 5);
