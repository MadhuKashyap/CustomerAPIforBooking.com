DROP TABLE IF EXISTS HOTEL;

CREATE TABLE HOTEL
(
  id VARCHAR AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  lat long NOT NULL,
  lon long NOT NULL
);
