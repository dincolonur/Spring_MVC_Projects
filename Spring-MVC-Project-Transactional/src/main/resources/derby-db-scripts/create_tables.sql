CREATE TABLE users (
  userId  INTEGER PRIMARY KEY,
  username VARCHAR(30),
  firstName VARCHAR(30),
  lastName VARCHAR(30),
  phoneNumbers VARCHAR(1000),
  address VARCHAR(100),
  password VARCHAR(30),
  roles VARCHAR(1000)
);

CREATE TABLE phone_numbers (
    phoneId  INTEGER PRIMARY KEY,
    phoneNumber VARCHAR(30),
    phoneCompanyId INTEGER
);

CREATE TABLE phone_companies (
    companyId INTEGER PRIMARY KEY,
    companyName VARCHAR(60),
    companyAddress VARCHAR(100),
    changeOperatorCost VARCHAR(30)
);

CREATE TABLE user_accounts (
    accountId INTEGER PRIMARY KEY,
    accountName  VARCHAR(30),
    username VARCHAR(30),
    phoneCompanyId INTEGER,
    amount VARCHAR(30)
);

INSERT INTO users VALUES(5, 'Karl', 'Karl', 'Feldcamp', '950 4445', '67900 Boston', '12345', 'REGISTERED_USER');
INSERT INTO phone_numbers VALUES(1, '950 4445', 4);
INSERT INTO phone_companies VALUES(4, 'Sprint', '321 Toronto', '14.0');
INSERT INTO users VALUES(6, 'John', 'John', 'Kolt', '950 5555', '88800 Boston', '33124', 'REGISTERED_USER;BOOKING_MANAGER');
INSERT INTO phone_numbers VALUES(2, '950 5555', 4);

--User Accounts information is inserted while startup.
INSERT INTO user_accounts VALUES(1, 'User_Account_James', 'James', 1, '50.0');
INSERT INTO user_accounts VALUES(2, 'User_Account_Mike', 'Mike', 2, '4.0');
INSERT INTO user_accounts VALUES(3, 'User_Account_Tom', 'Tom', 3, '80.0');
INSERT INTO user_accounts VALUES(4, 'User_Account_Hans', 'Hans', 2, '80.0');
INSERT INTO user_accounts VALUES(5, 'User_Account_Karl', 'Karl', 4, '100.0');
INSERT INTO user_accounts VALUES(6, 'User_Account_John', 'John', 4, '10.0');

CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);