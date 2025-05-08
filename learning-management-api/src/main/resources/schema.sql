--DROP TABLE IF EXISTS TAREFA;
--DROP TABLE IF EXISTS MATRICULA;
--DROP TABLE IF EXISTS ESTUDANTE;
--DROP TABLE IF EXISTS CURSO;
--DROP TABLE IF EXISTS CATEGORIA_TAREFA;

--delete from MATRICULA where estudante_id > 0;
CREATE TABLE IF NOT EXISTS ESTUDANTE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    primeiro_nome VARCHAR(255) NOT NULL,
    ultimo_nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(25) NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS CURSO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    data_conclusao DATE NOT NULL,
    deletado BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS MATRICULA (
    estudante_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    CONSTRAINT pk_matricula PRIMARY KEY (estudante_id, curso_id),
    CONSTRAINT fk_matricula_estudante FOREIGN KEY (estudante_id) REFERENCES estudante(id),
    CONSTRAINT fk_matricula_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
);


CREATE TABLE IF NOT EXISTS CATEGORIA_TAREFA (
    nome VARCHAR(255) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS TAREFA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudante_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    categoria_tarefa VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    tempo_gasto TIME NOT NULL,

    CONSTRAINT fk_tarefa_matricula FOREIGN KEY (estudante_id, curso_id)
        REFERENCES MATRICULA(estudante_id, curso_id),

    CONSTRAINT fk_tarefa_categoria FOREIGN KEY (categoria_tarefa)
            REFERENCES CATEGORIA_TAREFA(nome)
);

--delete from categoria_tarefa;
--insert into categoria_tarefa (nome) values ('PESQUISA');
--insert into categoria_tarefa (nome) values ('PRATICA');
--insert into categoria_tarefa (nome) values ('ASSISTIR_VIDEOAULA');
--
--delete from curso where id > 0;
--insert into curso (data_inicio, data_conclusao, deletado, nome)
--values ('2025-05-01', '2025-11-01',false,'Ingles');
--insert into curso (data_inicio, data_conclusao, deletado, nome)
--values ('2025-05-01', '2025-11-01',false,'Portugues');
--insert into curso (data_inicio, data_conclusao, deletado, nome)
--values ('2025-05-01','2025-11-01',false,'Matemática');
--
--
--delete from estudante where id > 0;
--insert into estudante (email, primeiro_nome, ultimo_nome, telefone, data_nascimento)
--values ('e1@gmail.com','John','Test','123','1990-10-10');
--insert into estudante (email, primeiro_nome, ultimo_nome, telefone, data_nascimento)
--values ('e2@gmail.com','Ana','Test','123', '1995-10-10');