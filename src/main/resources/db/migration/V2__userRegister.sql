CREATE EXTENSION IF NOT EXISTS pgcrypto;
  INSERT INTO users (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));