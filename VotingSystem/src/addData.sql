DROP TABLE IF EXISTS states;

CREATE TABLE states (
  addressid SERIAL PRIMARY KEY NOT NULL,
  stateName VARCHAR(15) NOT NULL,
  numDistricts INT NOT NULL
);

INSERT INTO states(stateName, numDistricts)
    VALUES('Alabama', 7),
      ('Alaska', 1),
      ('Arizona', 9),
      ('Arkansas', 4),
      ('California', 53),
      ('Colorado', 7),
      ('Connecticut', 5),
      ('Delaware', 1),
      ('Florida', 27),
      ('Georgia', 14),
      ('Hawaii', 2),
      ('Idaho', 2),
      ('Illinois', 18),
      ('Indiana', 9),
      ('Iowa', 4),
      ('Kansas', 4),
      ('Kentucky', 6),
      ('Louisiana', 6),
      ('Maine', 2),
      ('Maryland', 8),
      ('Massachusetts', 9),
      ('Michigan', 14),
      ('Minnesota', 8),
      ('Mississippi', 4),
      ('Missouri', 8),
      ('Montana', 1),
      ('Nebraska', 3),
      ('Nevada', 4),
      ('New Hampshire', 2),
      ('New Jersey', 12),
      ('New Mexico', 3),
      ('New York', 27),
      ('North Carolina', 13),
      ('North Dakota', 1),
      ('Ohio', 16),
      ('Oklahoma', 5),
      ('Oregon', 5),
      ('Pennsylvania', 18),
      ('Road Island', 2),
      ('South Carolina', 7),
      ('South Dakota', 1),
      ('Tennessee', 9),
      ('Texas', 36),
      ('Utah', 4),
      ('Vermont', 1),
      ('Virginia', 11),
      ('Washington', 10),
      ('West Virginia', 3),
      ('Wisconsin', 8),
      ('Wyoming', 1);

DROP TABLE IF EXISTS candidates;

DROP TABLE IF EXISTS candidates;
CREATE TABLE candidates (
  candidateid SERIAL PRIMARY KEY NOT NULL,
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  party VARCHAR(30) NOT NULL,
  voteCount INT NOT NULL,
  positionid INT NOT NULL
);


DROP TABLE IF EXISTS position;
CREATE TABLE position (
  positionid SERIAL PRIMARY KEY NOT NULL,
  positionTitle VARCHAR(30) NOT NULL,
  availablePrecincts VARCHAR(4) NOT NULL
);

  INSERT INTO position(positionTitle, availablePrecincts)
      VALUES('President', '0000'),
        ('Vice President', '0000');


DROP TABLE IF EXISTS precincts;
CREATE TABLE precincts (
  positionid SERIAL PRIMARY KEY NOT NULL,
  stateid VARCHAR(2) NOT NULL,
  precinctid VARCHAR(2) NOT NULL
);

INSERT INTO precincts(stateid, precinctid)
VALUES('01', '01'),
('01', '02'),
('01', '03'),
('01', '04'),
('01', '05'),
('01', '06'),
('01', '07');




