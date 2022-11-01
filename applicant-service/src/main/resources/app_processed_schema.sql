DROP TABLE app_processed IF EXISTS;

CREATE TABLE app_processed (
	applicationid INTEGER AUTO_INCREMENT PRIMARY KEY,
	app_status VARCHAR(255),
	app_submission_date DATETIME(6),
	country_of_birth VARCHAR(255),
	covid_vacc_status VARCHAR(255),
	dob DATE,
	name VARCHAR(255) NOT NULL,
	race VARCHAR(255)
);