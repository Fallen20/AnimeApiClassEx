CREATE TABLE IF NOT EXISTS anime (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    image text);

insert into anime(name, description, type, year, image) values
('Fullmetal Alchemist','alchimia anime','action', 2007,'/image/123'),
('Soul Eater','almas','action',2006,'/image/124');

CREATE TABLE IF NOT EXISTS users (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS files (
    fileid  UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea
);

