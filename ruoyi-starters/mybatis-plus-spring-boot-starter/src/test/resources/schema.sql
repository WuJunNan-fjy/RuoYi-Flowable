CREATE TABLE t_demo (
  id BIGINT PRIMARY KEY,
  code VARCHAR(64),
  name VARCHAR(128),
  create_by VARCHAR(64),
  create_time TIMESTAMP,
  update_by VARCHAR(64),
  update_time TIMESTAMP
);