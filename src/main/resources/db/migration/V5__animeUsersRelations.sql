create table fav_animes_from_user(
     animeid uuid references anime(animeid) on delete cascade,
     userid uuid references users(userid) on delete cascade,
     primary key (animeid,userid)
);
--animes favoritos del usuario


INSERT INTO fav_animes_from_user VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT userid FROM users WHERE username='user1')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT userid FROM users WHERE username='user2')),
   ((SELECT animeid FROM anime WHERE name='Soul Eater'),(SELECT userid FROM users WHERE username='user2'));
--añadir relaciones

create table user_follows(--a quien sigue
     actual_user uuid references users(userid) on delete cascade,--el usuario actual
     users_followed uuid references users(userid) on delete cascade,--quien él sigue
     primary key (actual_user,users_followed)
);

INSERT INTO user_follows VALUES
    ((SELECT userid from users where username='user1'), (select userid from users where username='user2')),
    ((SELECT userid from users where username='user1'), (select userid from users where username='user3')),
    ((SELECT userid from users where username='user1'), (select userid from users where username='user4'));
    --este user sigue a 3 users
--añadir relaciones
