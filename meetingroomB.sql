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
DROP TABLE IF EXISTS access_log;
DROP TABLE IF EXISTS room_image;

CREATE TABLE user (
        id VARCHAR(7) PRIMARY KEY,
        password VARCHAR(10) NOT NULL,
        name VARCHAR(10),
        address VARCHAR(30),
        is_admin BOOLEAN NOT NULL DEFAULT FALSE,
        delete_flg BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE room (
        id VARCHAR(4) PRIMARY KEY,
        name VARCHAR(20),
        delete_flg BOOLEAN NOT NULL DEFAULT FALSE
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

CREATE TABLE access_log (
        id INT AUTO_INCREMENT PRIMARY KEY,
        try_user_id VARCHAR(50),
        result VARCHAR(10) NOT NULL,
        ip_address VARCHAR(45),
        user_agent VARCHAR(255),
        when_access DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE room_image (
        image_id INT AUTO_INCREMENT PRIMARY KEY,
        room_id VARCHAR(4) NOT NULL,
        image_name VARCHAR(256),
        image_type VARCHAR(64),
        image_content MEDIUMBLOB,
        image_size INT,
        created_at DATETIME,
        FOREIGN KEY (room_id) REFERENCES room(id)
);