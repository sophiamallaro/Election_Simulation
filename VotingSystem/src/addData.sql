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
  stateid INT NOT NULL,
  precinctid VARCHAR(2) NOT NULL
);

INSERT INTO precincts(stateid, precinctid)
VALUES(1, '01'),
(1, '02'),
(1, '03'),
(1, '04'),
(1, '05'),
(1, '06'),
(1, '07');

INSERT INTO precincts(stateid, precinctid)
VALUES
(5, '01'),
(5, '02'),
(5, '03'),
(5, '04'),
(5, '05'),
(5, '06'),
(5, '07'),
(5, '08'),
(5, '09'),
(5, '10'),
(5, '11'),
(5, '12'),
(5, '13'),
(5, '14'),
(5, '15'),
(5, '16'),
(5, '17'),
(5, '18'),
(5, '19'),
(5, '20'),
(5, '03'),
(5, '21'),
(5, '22'),
(5, '23'),
(5, '24'),
(5, '25'),
(5, '26'),
(5, '27'),
(5, '28'),
(5, '29'),
(5, '30'),
(5, '31'),
(5, '32'),
(5, '33'),
(5, '34'),
(5, '35'),
(5, '36'),
(5, '37'),
(5, '38'),
(5, '39'),
(5, '40'),
(5, '41'),
(5, '42'),
(5, '43'),
(5, '44'),
(5, '45'),
(5, '46'),
(5, '47'),
(5, '48'),
(5, '49'),
(5, '50'),
(5, '51'),
(5, '52'),
(5, '53');


INSERT INTO precincts(stateid, precinctid)
VALUES
(10, '01'),
(10, '02'),
(10, '03'),
(10, '04'),
(10, '05'),
(10, '06'),
(10, '07'),
(10, '08'),
(10, '09'),
(10, '10'),
(10, '11'),
(10, '12'),
(10, '13'),
(10, '14');


INSERT INTO precincts(stateid, precinctid)
VALUES
(11, '01'),
(11, '02');

INSERT INTO position(positionTitle, availablePrecincts)
VALUES ('House of Representatives', '0101');

INSERT INTO candidates(firstName, lastName, party, voteCount, positionid)
VALUES ('Gary', 'Johnson', 'Libertarian', 0, 1),
        ('Jill', 'Stein', 'Green', 0, 1);


INSERT INTO precincts(stateid, precinctid)
VALUES (15, '01'),
(15, '02'),
(15, '03'),
(15, '04');


INSERT INTO position(positionTitle, availablePrecincts)
VALUES ('Governor', '1500'),
('House of Representatives', '1502');


INSERT INTO candidates(firstName, lastName, party, voteCount, positionid)
VALUES ('Terry', 'Branstad', 'Republican', 0, 4),
        ('David', 'Loebsack', 'Democrat', 0, 5);






        UPDATE candidates SET voteCount=0 WHERE candidateid=2;



















