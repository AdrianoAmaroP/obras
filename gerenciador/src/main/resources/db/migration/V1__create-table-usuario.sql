CREATE TABLE USUARIO(
    id int primary key,
    nome varchar(100) not null unique,
    senha varchar(255) not null
)