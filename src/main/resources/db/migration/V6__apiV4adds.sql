


create table comment(
         commentid uuid  NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
         comentario text
);

INSERT INTO comment (comentario) VALUES(
    ('Este anime me ha cambiado la vida')
);

INSERT INTO comment (comentario) VALUES(
    ('El de 2006 era mejor')
);




create table comment_by_user(
         commentid uuid references comment(commentid) on delete cascade,
         userid uuid references users(userid) on delete cascade,
         primary key(commentid, userid)
);

INSERT INTO comment_by_user VALUES
    ((SELECT commentid from comment where comentario='Este anime me ha cambiado la vida'), (select userid from users where username='user1'));

INSERT INTO comment_by_user VALUES
    ((SELECT commentid from comment where comentario='El de 2006 era mejor'), (select userid from users where username='user2'));


create table commented_in_anime(
         commentid uuid references comment(commentid) on delete cascade,
         animeid uuid references anime(animeid) on delete cascade,
         primary key(commentid, animeid)
);

INSERT INTO commented_in_anime VALUES
    ((SELECT commentid from comment where comentario='Este anime me ha cambiado la vida'), (SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'));

INSERT INTO commented_in_anime VALUES
    ((SELECT commentid from comment where comentario='El de 2006 era mejor'), (SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'));
