-- Criação da tabela de Missões
CREATE TABLE tb_Missoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    dificuldade VARCHAR(255)
);

-- Criação da tabela de Ninjas
CREATE TABLE tb_cadastro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    img_url VARCHAR(255),
    idade INT,
    missoes_id BIGINT,
    CONSTRAINT fk_missoes FOREIGN KEY (missoes_id) REFERENCES tb_Missoes(id)
);