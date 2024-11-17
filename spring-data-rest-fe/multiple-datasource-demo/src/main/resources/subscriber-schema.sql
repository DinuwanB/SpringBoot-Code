DROP TABLE IF EXISTS Subscriber; -- Only doing for demo purpose to clean everything on restart.

CREATE TABLE Subscriber (
    id varchar(500) NOT NULL,
    name varchar(500) NOT NULL,
    email varchar(500) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO Subscriber
(id, name, email)
VALUES ('1234', 'John', 'john@mail.com');

