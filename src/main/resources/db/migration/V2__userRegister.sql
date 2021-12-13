CREATE EXTENSION IF NOT EXISTS pgcrypto;
  INSERT INTO users (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));

  INSERT INTO users (username, password) VALUES ('user1', crypt('pass', gen_salt('bf')));
  INSERT INTO users (username, password) VALUES ('user2', crypt('pass', gen_salt('bf')));
  INSERT INTO users (username, password) VALUES ('user3', crypt('pass', gen_salt('bf')));
  INSERT INTO users (username, password) VALUES ('user4', crypt('pass', gen_salt('bf')));
  INSERT INTO users (username, password) VALUES ('user5', crypt('pass', gen_salt('bf')));