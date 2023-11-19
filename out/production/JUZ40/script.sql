CREATE TABLE BankCard (
    id BIGINT PRIMARY KEY,
    cardId BIGINT,
    userId BIGINT,
    cardNumber VARCHAR(255),
    cvv VARCHAR(10),
    expiryDate VARCHAR(20)
);

CREATE TABLE Lesson (
    id BIGINT PRIMARY KEY,
    lessonId BIGINT,
    userId BIGINT,
    subjectId BIGINT,
    lessonTitle VARCHAR(255)
);

CREATE TABLE Role (
    id BIGINT PRIMARY KEY,
    roleStatus VARCHAR(50)
);

CREATE TABLE "users" (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    username VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE Subject (
    id BIGINT PRIMARY KEY,
    subjectId BIGINT,
    subjectName VARCHAR(255),
    costSubject BIGINT
);

CREATE TABLE UserRoleMapping (
    userId BIGINT,
    roleId BIGINT
);
