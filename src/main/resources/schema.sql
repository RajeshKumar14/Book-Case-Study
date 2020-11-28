CREATE DATABASE book_case_study;
USE book_case_study;
CREATE TABLE user_master(
  user_id    int NOT NULL AUTO_INCREMENT,
  username   VARCHAR(50) NULL,
  password   VARCHAR(50) NULL,
  role       VARCHAR(50) NULL,
  enabled    boolean NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NULL
);
CREATE TABLE book_master(
  id           int NOT NULL AUTO_INCREMENT,
  belongs_to   VARCHAR(50) NULL,
  authors      VARCHAR(50) NULL,
  book_name    VARCHAR(50) NULL,
  created_at   TIMESTAMP NOT NULL,
  updated_at   TIMESTAMP NULL
);