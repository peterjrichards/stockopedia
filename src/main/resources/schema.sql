DROP TABLE IF EXISTS facts;
DROP TABLE IF EXISTS attributes;
DROP TABLE IF EXISTS securities;

DROP SCHEMA IF EXISTS security_analytics;

CREATE SCHEMA security_analytics AUTHORIZATION sa;

CREATE TABLE security_analytics.securities (
  id INT PRIMARY KEY,
  symbol VARCHAR(10) NOT NULL
);

CREATE TABLE security_analytics.attributes (
  id INT PRIMARY KEY,
  attribute VARCHAR(250) NOT NULL
);

CREATE TABLE security_analytics.facts (
  security_id INT,
  attribute_id INT,
  value DECIMAL DEFAULT NULL,
  FOREIGN KEY(security_id) REFERENCES securities(id),
  FOREIGN KEY(attribute_id) REFERENCES attributes(id),
  PRIMARY KEY(security_id, attribute_id)
);