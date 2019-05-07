CREATE SEQUENCE user_realm_sequence;

CREATE TABLE user_realm (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    key CHAR(32) NOT NULL
);
