DROP TABLE IF EXISTS states;

CREATE TABLE states (
  addressid SERIAL PRIMARY KEY NOT NULL,
  stateName VARCHAR(15) NOT NULL,
  numDistricts INT NOT NULL
);

INSERT INTO states(addressid, stateName, numDistricts)
    VALUES(01, 'Alabama', 7),
      (02, 'Alaska', 1),
      (03, 'Arizona', 9),
      (04, 'Arkansas', 4),
      (05, 'California', 53),
      (06, 'Colorado', 7),
      (07, 'Connecticut', 5),
      (08, 'Delaware', 1),
      (09, 'Florida', 27),
      (10, 'Georgia', 14),
      (11, 'Hawaii', 2),
      (12, 'Idaho', 2),
      (13, 'Illinois', 18),
      (14, 'Indiana', 9),
      (15, 'Iowa', 4),
      (16, 'Kansas', 4),
      (17, 'Kentucky', 6),
      (18, 'Louisiana', 6),
      (19, 'Maine', 2),
      (20, 'Maryland', 8),
      (21, 'Massachusetts', 9),
      (22, 'Michigan', 14),
      (23, 'Minnesota', 8),
      (24, 'Mississippi', 4),
      (25, 'Missouri', 8),
      (26, 'Montana', 1),
      (27, 'Nebraska', 3),
      (28, 'Nevada', 4),
      (29, 'New Hampshire', 2),
      (30, 'New Jersey', 12),
      (31, 'New Mexico', 3),
      (32, 'New York', 27),
      (33, 'North Carolina', 13),
      (34, 'North Dakota', 1),
      (35, 'Ohio', 16),
      (36, 'Oklahoma', 5),
      (37, 'Oregon', 5),
      (38, 'Pennsylvania', 18),
      (39, 'Road Island', 2),
      (40, 'South Carolina', 7),
      (41, 'South Dakota', 1),
      (42, 'Tennessee', 9),
      (43, 'Texas', 36),
      (44, 'Utah', 4),
      (45, 'Vermont', 1),
      (46, 'Virginia', 11),
      (47, 'Washington', 10),
      (48, 'West Virginia', 3),
      (49, 'Wisconsin', 8),
      (50, 'Wyoming', 1);

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

  INSERT INTO position(positionid, positionTitle, availablePrecincts)
      VALUES(1,'President', '0000'),
        (2, 'Vice President', '0000');







