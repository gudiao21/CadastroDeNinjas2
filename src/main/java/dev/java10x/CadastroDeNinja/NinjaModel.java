package dev.java10x.CadastroDeNinja;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity // Para se ter disponível esta 'annotation' é preciso ter instalado a dependência de persistência de dados, a 'Spring data JPA'(Java Persistence API), no 'pom.xml'. E somente assim, eu consigo transformar toda a classe imediatamente abaixo('public class NinjaModel') em uma Entidade(Entity) do BD.
@Table(name = "tb_cadastro") // nome, por convenção, tem que ser em 'snake case' e tudo minúsculo e com 'tb' como prefixo.
public class NinjaModel { // É uma classe e não uma 'Entidade'!

    @Id // As 'annotations' sempre começam com @ + primeira letra em maiúscula. Indica que o atributo imediatamente abaixo terá características de 'id'
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A '@Id' e '@GeneratedValue' são usadas ao mesmo tempo para fazer a mesma coisa. Passa a extratégia de como vai se gerar o 'id', através de 'strategy = GenerationType.IDENTITY'. Com 'GenerationType.AUTO' funcionaria, mas para indicar que quer trabalhar com números apenas se usa 'GenerationType.IDENTITY', gerando sequêncialmente números únicos.
    private Long id; // É necessário ter o id, porque torna único cada registro. É do tipo 'Long' porque poderá ter muitos registros. O 'java' vai incrementar automaticamente.
    private String nome;
    private String email;
    private int idade;

    public NinjaModel() { // 'no args constructor' é um construtor vazio
    }

    public NinjaModel(String nome, String email, int idade) { // 'All args Constructor' é um construtor com todos os atributos
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NinjaModel{" +
                "idade=" + idade +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}

//'Annotations' são, teoricamente, um amontoado de código "pré-pronto", que chamamos usando o '@'.
