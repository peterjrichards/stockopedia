CREATE SCHEMA IF NOT EXISTS security_analytics AUTHORIZATION sa;

DROP TABLE IF EXISTS security_analytics.facts;
DROP TABLE IF EXISTS security_analytics.attributes;
DROP TABLE IF EXISTS security_analytics.securities;

CREATE TABLE security_analytics.securities (
  id INT PRIMARY KEY,
  symbol VARCHAR(10) NOT NULL
);

CREATE TABLE security_analytics.attributes (
  id INT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE security_analytics.facts (
  security_id INT,
  attribute_id INT,
  value DECIMAL DEFAULT NULL,
  FOREIGN KEY(security_id) REFERENCES securities(id),
  FOREIGN KEY(attribute_id) REFERENCES attributes(id),
  PRIMARY KEY(security_id, attribute_id)
);