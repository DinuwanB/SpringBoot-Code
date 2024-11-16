
CREATE TABLE IF NOT EXISTS category (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Use AUTO_INCREMENT for auto-numbering
  description VARCHAR(255),
  name        VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
  id                 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Use AUTO_INCREMENT for auto-numbering
  available_quantity DOUBLE PRECISION NOT NULL,
  description        VARCHAR(255),
  name               VARCHAR(255),
  price              DECIMAL(38, 2),  -- Use DECIMAL for currency
  category_id        INT,
  FOREIGN KEY (category_id) REFERENCES category(id)
);

-- MySQL doesn't use sequences for auto-incrementing values, handled by AUTO_INCREMENT in table definition

