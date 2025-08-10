-- V1: Migração para criar a tabela de missões
CREATE TABLE tb_missoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dificuldade VARCHAR(255),
    nome VARCHAR(255) NOT NULL
);

// O Spring Boot executa automaticamente as migrations Flyway ao iniciar a aplicação.
//   Ou seja, você só precisa rodar normalmente: mvn spring-boot:run

// Você pode limpar e rodar novamente com: mvn clean spring-boot:run
// Isso funciona bem com banco H2 em memória, que reinicia do zero a cada execução.