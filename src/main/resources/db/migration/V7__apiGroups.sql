CREATE TABLE IF NOT EXISTS grupos (
    groupid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    nombre_grupo text,
    descripcion text);

INSERT INTO grupos (nombre_grupo, descripcion) VALUES(
    'Grupo prueba1','Descripcion'
);
create table miembros_grupo(
         groupid uuid references grupos(groupid) on delete cascade,
         userid uuid references users(userid) on delete cascade,
         primary key(groupid, userid)
);

INSERT INTO miembros_grupo VALUES
    ((SELECT groupid from grupos where nombre_grupo='Grupo prueba1'), (select userid from users where username='user1'));

