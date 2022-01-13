Create table authors (
    authorid uuid not null default gen_random_uuid() primary key,
    name text,
    imageurl text
);

--como un autor puede tener muchos animes y viceversa se hace una tabla que los relaciones
create table author_movie_relation(
    animeid uuid references anime(animeid) on delete cascade,
    authorid uuid references authors(authorid) on delete cascade,
    primary key (animeid,authorid)
);


Create table genres (
    genreid uuid not null default gen_random_uuid() primary key,
    label text,
    imageurl text
);

create table genres_movie_relation(
    animeid uuid references anime(animeid) on delete cascade,
    genreid uuid references genres(genreid) on delete cascade,
    primary key (animeid,genreid)
);