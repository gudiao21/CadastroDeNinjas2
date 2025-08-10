-- V2: Migração para criar a tabela de cadastro
CREATE TABLE tb_cadastro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    idade INT NOT NULL,
    img_url VARCHAR(255),
    nome VARCHAR(255) NOT NULL,
    missoes_id BIGINT,
    rank VARCHAR(255),
    FOREIGN KEY (missoes_id) REFERENCES tb_missoes(id)
);

// O Spring Boot executa automaticamente as migrations Flyway ao iniciar a aplicação.
//   Ou seja, você só precisa rodar normalmente: mvn spring-boot:run

// Você pode limpar e rodar novamente com: mvn clean spring-boot:run
// Isso funciona bem com banco H2 em memória, que reinicia do zero a cada execução.