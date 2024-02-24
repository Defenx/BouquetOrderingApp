CREATE TABLE bouquets (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          photo_url VARCHAR(1024),
                          description TEXT
);