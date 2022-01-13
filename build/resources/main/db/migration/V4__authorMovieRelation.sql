INSERT INTO authors(name, imageurl) VALUES
    ('Autor One','autor1.jpg'),
    ('Autor Two','autor2.jpg');

INSERT INTO genres(label, imageurl) VALUES
    ('Genero One','genre1.jpg'),
    ('Genero Two','genre2.jpg');

INSERT INTO author_movie_relation VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT authorid FROM authors WHERE name='Autor One')),
   ((SELECT animeid FROM anime WHERE name='Soul Eater'),(SELECT authorid FROM authors WHERE name='Autor Two'));

INSERT INTO genres_movie_relation VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT genreid FROM genres WHERE label='Genero One')),
   ((SELECT animeid FROM anime WHERE name='Soul Eater'),(SELECT genreid FROM genres WHERE label='Genero Two'));