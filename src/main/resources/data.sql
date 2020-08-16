DROP TABLE IF EXISTS car;

CREATE TABLE car (
  id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,
  make VARCHAR(250) NOT NULL,
  model VARCHAR(250) NOT NULL,
  NUMBER_OF_SEATS INT NOT NULL,
  type VARCHAR(250) DEFAULT NULL
);

INSERT INTO car (make, model, NUMBER_OF_SEATS, type) VALUES
  ('GM', 'Spin', 7, 'MINIVAN'),
  ('Ford', 'Ecosport', 5, 'SUV'),
  ('VW', 'Up!', 5, 'MINICAR'),
  ('Fiat', 'Doblo', 7, 'MINIVAN');