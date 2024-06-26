-- Criar uma nova sequência para a coluna id
CREATE SEQUENCE usuario_id_seq;

-- Alterar a coluna id para usar a sequência
ALTER TABLE USUARIO ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq');

-- Configurar a sequência como propriedade da coluna id
ALTER SEQUENCE usuario_id_seq OWNED BY USUARIO.id;
