CREATE TABLE student(
	id SERIAL PRIMARY KEY,
	firstName VARCHAR(255) NOT null,
	lastName VARCHAR(255) NOT null,
	nbClass int NOT null,
	dateOfDiploma date DEFAULT current_date
);

SELECT * FROM student;