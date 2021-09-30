create table user(
 seq int auto_increment PRIMARY KEY,
 id VARCHAR(100) NOT NULL,
 password VARCHAR(100) NOT NULL, 
 name VARCHAR(100) NOT NULL,
 created_date TIMESTAMP NOT NULL,
 updated_date TIMESTAMP,
 UNIQUE KEY name (id)
);