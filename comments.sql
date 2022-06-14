DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
  id SERIAL PRIMARY KEY,
  name TEXT,
  email TEXT,
  comment TEXT
);

INSERT INTO comments(name, email, comment) VALUES ('Test Name', 'Test Email', 'Test Comment');