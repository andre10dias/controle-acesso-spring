CREATE TABLE IF NOT EXISTS usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    login VARCHAR(255) UNIQUE,
    senha VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS grupo (
    id UUID PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usuario_grupo (
    id UUID PRIMARY KEY,
    usuario_id UUID,
    grupo_id UUID,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_grupo FOREIGN KEY (grupo_id) REFERENCES grupo(id)
);

-- Evita duplicidade de associação
CREATE UNIQUE INDEX ux_usuario_grupo_usuario_grupo
ON usuario_grupo (usuario_id, grupo_id);

-- Indexa colunas de busca frequente
CREATE INDEX idx_usuario_login ON usuario(login);
CREATE INDEX idx_grupo_nome ON grupo(nome);

-- Usuários (senha: 123)
INSERT INTO usuario(id, nome, login, senha)
VALUES ('b7840061-792b-4a08-bccd-deae8228dc60', 'João Admin', 'admin', '$2a$12$4ncCYpag3AHEdasxSAjC6e1vDVTqHrDTNVNHqCmnPlel40eJvvzqq');

INSERT INTO usuario(id, nome, login, senha)
VALUES ('e75e6b3c-311b-4992-a023-350849a8868d', 'Maria Ger', 'gerente', '$2a$12$4ncCYpag3AHEdasxSAjC6e1vDVTqHrDTNVNHqCmnPlel40eJvvzqq');

INSERT INTO usuario(id, nome, login, senha)
VALUES ('8a93ca9d-f64a-4709-b45f-4bcf64c9640c', 'Pedro Téc', 'tecnico', '$2a$12$4ncCYpag3AHEdasxSAjC6e1vDVTqHrDTNVNHqCmnPlel40eJvvzqq');

-- Grupos
INSERT INTO grupo(id, nome)
VALUES ('bf4ec3dc-b2e4-4b68-ae27-bf21ee7acbf5', 'ADMIN');

INSERT INTO grupo(id, nome)
VALUES ('bf563d16-a5d6-4a46-a738-6a4460b8c6de', 'GERENTE_RH');

INSERT INTO grupo(id, nome)
VALUES ('0cefec9a-eae6-41b7-866c-11aa9d0372f1', 'TECNICO_RH');

-- Usuarios-Grupos
INSERT INTO usuario_grupo(id, usuario_id, grupo_id)
VALUES ('aa94bd11-b740-480f-bd82-7535db0bb88f', 'b7840061-792b-4a08-bccd-deae8228dc60', 'bf4ec3dc-b2e4-4b68-ae27-bf21ee7acbf5');

INSERT INTO usuario_grupo(id, usuario_id, grupo_id)
VALUES ('f3e7f746-e640-4496-ab4a-1a9ac34c5895', 'e75e6b3c-311b-4992-a023-350849a8868d', 'bf563d16-a5d6-4a46-a738-6a4460b8c6de');

INSERT INTO usuario_grupo(id, usuario_id, grupo_id)
VALUES ('543bd018-9b67-4469-9142-58633216d6cd', '8a93ca9d-f64a-4709-b45f-4bcf64c9640c', '0cefec9a-eae6-41b7-866c-11aa9d0372f1');
