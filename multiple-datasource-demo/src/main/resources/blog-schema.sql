DROP TABLE IF EXISTS Post; -- Only doing for demo purpose to clean everything on restart.

CREATE TABLE Post (
    id varchar(500) NOT NULL,
    title varchar(500) NOT NULL,
    slug varchar(500) NOT NULL,
    date date NOT NULL,
    time_to_read int NOT NULL,
    tags varchar(500),
    PRIMARY KEY (id)
);

INSERT INTO Post
(id, title, slug, date, time_to_read, tags)
VALUES ('1234','Hello World!', 'hellow Spring multiple jdbc data source project', CURRENT_DATE, 5, 'spring, java');

