CREATE TABLE IF NOT EXISTS anime (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    image text);

--CREATE TABLE IF NOT EXISTS anime (
   -- userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
   -- username varchar(24) NOT NULL UNIQUE,
   -- password varchar(255) NOT NULL);

