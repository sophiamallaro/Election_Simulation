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
(50, '01');
(49, '02'),
(49, '03'),
(49, '04'),
(49, '05'),
(49, '06'),
(49, '07'),
(49, '08');
(47, '09'),
(47, '10');
(46, '11');
(43, '12'),
(43, '13'),
(43, '14'),
(43, '15'),
(43, '16'),
(43, '17'),
(43, '18'),
(43, '19'),
(43, '20'),
(43, '03'),
(43, '21'),
(43, '22'),
(43, '23'),
(43, '24'),
(43, '25'),
(43, '26'),
(43, '27'),
(43, '28'),
(43, '29'),
(43, '30'),
(43, '31'),
(43, '32'),
(43, '33'),
(43, '34'),
(43, '35'),
(43, '36');
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
(42, '01'),
(42, '02'),
(42, '03'),
(42, '04'),
(42, '05'),
(42, '06'),
(42, '07'),
(42, '08'),
(42, '09');
(38, '10'),
(38, '11'),
(38, '12'),
(38, '13'),
(38, '14'),
(38, '15'),
(38, '16'),
(38, '17'),
(38, '18');




INSERT INTO precincts(stateid, precinctid)
VALUES
(29, '01'),
(29, '02');
(28, '03'),
(28, '04');
(25, '05'),
(25, '06'),
(25, '07'),
(25, '08');


INSERT INTO precincts(stateid, precinctid)
VALUES
(19, '01'),
(19, '02');

INSERT INTO position(positionTitle, availablePrecincts)
VALUES ('House of Representatives', '0101');

INSERT INTO candidates(firstName, lastName, party, voteCount, positionid)
VALUES ('Gary', 'Johnson', 'Libertarian', 0, 1),
        ('Jill', 'Stein', 'Green', 0, 1);


INSERT INTO precincts(stateid, precinctid)
VALUES (12, '01'),
(12, '02');


INSERT INTO precincts(stateid, precinctid)
VALUES
(16, '01'),
(16, '02'),
(16, '03'),
(16, '04');





INSERT INTO position(positionTitle, availablePrecincts)
VALUES ('Governor', '1500'),
('House of Representatives', '1502');


INSERT INTO candidates(firstName, lastName, party, voteCount, positionid)
VALUES ('Terry', 'Branstad', 'Republican', 0, 4),
        ('David', 'Loebsack', 'Democrat', 0, 5);






        UPDATE candidates SET voteCount=0;



















