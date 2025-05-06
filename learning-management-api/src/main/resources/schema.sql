SELECT * FROM INFORMATION_SCHEMA.TABLES;

DROP TABLE IF EXISTS MATRICULA;
DROP TABLE IF EXISTS ESTUDANTE;
DROP TABLE IF EXISTS CURSO;

CREATE TABLE IF NOT EXISTS ESTUDANTE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    primeiro_nome VARCHAR(255) NOT NULL,
    ultimo_nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(25) NOT NULL,
    data_nascimento DATE NOT NULL
);
ALTER SEQUENCE estudante_seq RESTART WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS CURSO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    deletado BOOLEAN NOT NULL,
    concluido BOOLEAN NOT NULL
);
ALTER SEQUENCE curso_seq RESTART WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS MATRICULA (
    estudante_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    CONSTRAINT pk_matricula PRIMARY KEY (estudante_id, curso_id),
    CONSTRAINT fk_matricula_estudante FOREIGN KEY (estudante_id) REFERENCES estudante(id),
    CONSTRAINT fk_matricula_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE IF NOT EXISTS CATEGORIA_TAREFA (
    name VARCHAR(255) PRIMARY KEY
);

delete from categoria_tarefa;
insert into categoria_tarefa (name) values ('PESQUISA');
insert into categoria_tarefa (name) values ('PRATICA');
insert into categoria_tarefa (name) values ('ASSISTIR_VIDEOAULA');

delete from curso where id > 0;
insert into curso (concluido,data_inicio,deletado,nome, id)
values (false,'2025-05-01',false,'Ingles', select next value for curso_seq);
insert into curso (concluido,data_inicio,deletado,nome, id)
values (false,'2025-05-01',false,'Portugues', select next value for curso_seq);
insert into curso (concluido,data_inicio,deletado,nome, id)
values (false,'2025-05-01',false,'Matemática', select next value for curso_seq);


delete from estudante where id > 0;
insert into estudante (email, primeiro_nome, ultimo_nome, telefone, data_nascimento, id)
values ('e1@gmail.com','John','Test','123','1990-10-10', select next value for estudante_seq);
insert into estudante (email, primeiro_nome, ultimo_nome, telefone, data_nascimento, id)
values ('e2@gmail.com','Ana','Test','123', '1995-10-10', select next value for estudante_seq);