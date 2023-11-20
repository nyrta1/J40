CREATE TABLE BankCard (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    cardNumber VARCHAR(255),
    cvv VARCHAR(10),
    expiryDate VARCHAR(20),
    bankName VARCHAR(20)
);

CREATE TABLE Role (
    id BIGSERIAL PRIMARY KEY,
    roleStatus VARCHAR(50)
);

CREATE TABLE "users" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    username VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE Subject (
    id BIGSERIAL PRIMARY KEY,
    owner VARCHAR(255),
    subjectName VARCHAR(255),
    costSubject BIGINT
);

CREATE TABLE Lesson (
    id BIGSERIAL PRIMARY KEY,
    subjectName VARCHAR(255),
    lessonTitle VARCHAR(255)
);

CREATE TABLE UserRoleMapping (
    username VARCHAR(255),
    roleId BIGINT
);

CREATE TABLE boughtsubject (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    subjectName VARCHAR(255)
);



INSERT INTO role (id, rolestatus) VALUES ('1', 'ADMIN');
INSERT INTO role (id, rolestatus) VALUES ('2', 'TEACHER');
INSERT INTO role (id, rolestatus) VALUES ('3', 'STUDENT');

INSERT INTO users (name, surname, username, password) VALUES ('Ali', 'Kuandyk', 'alikuandyk', 'YWxpa3VhbmR5aw==');
INSERT INTO userrolemapping (username, roleId) VALUES ('alikuandyk', 1);

INSERT INTO users (name, surname, username, password) VALUES ('Yersaiyn', 'Saipbek', 'yersaiynsaipbek', 'eWVyc2FpeW5zYWlwYmVr');
INSERT INTO userrolemapping (username, roleId) VALUES ('yersaiynsaipbek', 2);

INSERT INTO users (name, surname, username, password) VALUES ('Asylzhan', 'Kabibolla', 'asylzhankabibolla', 'YXN5bHpoYW5rYWJpYm9sbGE=');
INSERT INTO userrolemapping (username, roleId) VALUES ('asylzhankabibolla', 3);