-- Collector will autoinstall the following schema. Just to take if you want to install manually

CREATE TABLE ENVIROMENT_INFORMATION(
	version VARCHAR(10)
);

CREATE TABLE COLLECTION(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(25),
	UNIQUE(name)
);

CREATE TABLE PARAMETER_TYPE(
	id SMALLINT PRIMARY KEY,
	name VARCHAR(25),
	description VARCHAR(140)
);

CREATE TABLE TRAINING_PARAMETERS(
	id SMALLINT,
	collection_id INT REFERENCES COLLECTION(id),
	name VARCHAR(25),
	description VARCHAR(140),
	type_id SMALLINT REFERENCES PARAMETER_TYPE(id),
	PRIMARY KEY(collection_id, id)
);