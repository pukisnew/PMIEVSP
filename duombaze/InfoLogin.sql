BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `InfoLogin` (
  `Nr`        INTEGER,
  `Vardas`    TEXT,
  `Pavarde`   TEXT,
  `Elpastas`  TEXT,
  `Telefonas` INTEGER,
  `Login`     TEXT,
  `Password`  TEXT,
  `Data_Nuo`  INTEGER,
  `Data_Iki`  TEXT
);
INSERT INTO `InfoLogin` (Nr, Vardas, Pavarde, Elpastas, Telefonas, Login, Password, Data_Nuo, Data_Iki)
VALUES (1, 'John', 'Smit', 'dwhiszx@gmail.com', 860009251, 'dwhiszx@gmail.com', '123123', '2018-01-01', '2018-06-30'),
  (2, 'Johan', 'Smit', 'pukisnew@usa.com', 860009252, 'pukisnew@usa.com', '0000', '2018-01-01', '2018-01-01'),
  (7, 'Jonas', 'Janas', 'j.janas@gmail.com', 860009725, 'j.janas@gmail.com', 'qwertas', '2018-01-01', '2018-02-30');
COMMIT;
