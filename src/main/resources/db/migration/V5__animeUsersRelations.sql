create table fav_animes_from_user(
     animeid uuid references anime(animeid) on delete cascade,
     imagen text references anime(image) on delete cascade,
     userid uuid references users(userid) on delete cascade,
     primary key (animeid,userid)
);
--animes favoritos del usuario


INSERT INTO fav_animes_from_user VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT userid FROM users WHERE username='user1'),(SELECT image FROM anime WHERE name='Fullmetal Alchemist')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT userid FROM users WHERE username='user2'),(SELECT image FROM anime WHERE name='Fullmetal Alchemist')),
   ((SELECT animeid FROM anime WHERE name='Soul Eater'),(SELECT userid FROM users WHERE username='user2'),(SELECT image FROM anime WHERE name='Soul Eater'));
--añadir relaciones

create table seen_animes_user(
     animeid uuid references anime(animeid) on delete cascade,
     userid uuid references users(userid) on delete cascade,
     primary key (animeid,userid)
);