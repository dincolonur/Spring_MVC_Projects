CREATE TABLE users (
  userId  INTEGER PRIMARY KEY,
  firstName VARCHAR(30),
  lastName VARCHAR(30),
  phoneNumbers VARCHAR(1000),
  address VARCHAR(100)
);

CREATE TABLE phone_numbers (
    phoneId  INTEGER PRIMARY KEY,
    phoneNumber VARCHAR(30),
    phoneCompanyId INTEGER
);

CREATE TABLE phone_companies (
    companyId INTEGER PRIMARY KEY,
    companyName VARCHAR(60),
    companyAddress VARCHAR(100)
);