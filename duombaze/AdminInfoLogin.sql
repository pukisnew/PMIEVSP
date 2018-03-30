BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `AdminInfoLogin` (
  `Nr`            INTEGER,
  `Vardas`        TEXT,
  `Pavardė`       TEXT,
  `Elpaštas`      TEXT,
  `Telefonas`     TEXT,
  `Prisijungimas` TEXT,
  `Slaptažodis`   TEXT
);
INSERT INTO `AdminInfoLogin` (Nr, Vardas, Pavardė, Elpaštas, Telefonas, Prisijungimas, Slaptažodis)
VALUES (1, 'John', 'Smit', 'dwhiszx@gmail.com', '860009251', 'admin', 'admin'),
  (2, 'Johan', 'Smit', 'pukisnew@usa.com', '860009252', 'pukis', 'alus');
COMMIT;
