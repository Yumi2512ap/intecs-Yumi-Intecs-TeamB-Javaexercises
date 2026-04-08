
CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY 'pass';

GRANT SELECT, UPDATE, INSERT, DELETE 
ON meetingroomB.* 
TO 'user'@'localhost';

DROP DATABASE IF EXISTS meetingroomB;
CREATE DATABASE meetingroomB;
USE meetingroomB;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS reservation;

CREATE TABLE user (
        id VARCHAR(7) PRIMARY KEY,
        password VARCHAR(10) NOT NULL,
        name VARCHAR(10),
        address VARCHAR(30)
);

CREATE TABLE room (
        id VARCHAR(4) PRIMARY KEY,
        name VARCHAR(20)
);

CREATE TABLE reservation (
        id INT PRIMARY KEY AUTO_INCREMENT,
        roomid VARCHAR(4) NOT NULL,
        date DATE NOT NULL,
        start TIME NOT NULL,
        end TIME NOT NULL,
        userid VARCHAR(7) NOT NULL,
        FOREIGN KEY(roomid) REFERENCES room(id),
        FOREIGN KEY(userid) REFERENCES user(id),
        UNIQUE(roomid, date, start)
);

