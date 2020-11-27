CREATE  TABLE  user_master(
  user_id   int NOT NULL AUTO_INCREMENT,
  username   VARCHAR(50) NULL,
  password   VARCHAR(50) NULL,
  role   VARCHAR(50) NULL,
  enabled   boolean NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NULL
);