DROP TABLE IF EXISTS andis;
 
CREATE TABLE andis (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  role VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO andis (first_name, last_name, role) VALUES
  ('Aliko', 'Dangote', 'Product Developer'),
  ('Bill', 'Gates', 'Product Analyst'),
  ('Folrunsho', 'Alakija', 'Squad Lead');