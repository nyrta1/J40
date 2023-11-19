CREATE TABLE BankCard (
    cardId BIGINT PRIMARY KEY,
    userId BIGINT,
    cardNumber VARCHAR(255),
    cvv VARCHAR(10),
    expiryDate VARCHAR(20)
);

CREATE TABLE Lesson (
    lessonId BIGINT PRIMARY KEY,
    userId BIGINT,
    subjectId BIGINT,
    lessonTitle VARCHAR(255)
);

CREATE TABLE Role (
    ID BIGINT PRIMARY KEY,
    roleStatus VARCHAR(50)
);

CREATE TABLE "users" (
    ID BIGINT PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    username VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE Subject (
    subjectId BIGINT PRIMARY KEY,
    subjectName VARCHAR(255),
    costSubject BIGINT
);

CREATE TABLE UserRoleMapping (
    userId BIGINT,
    roleId BIGINT
);
