package dev.java10x.CadastroDeNinja.Ninjas.Controller.Service;

import dev.java10x.CadastroDeNinja.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Para se ter disponível esta 'annotation' é preciso ter instalado a dependência de persistência de dados, a 'Spring data JPA'(Java Persistence API), no 'pom.xml'. E somente assim, eu consigo transformar toda a classe imediatamente abaixo('public class NinjaModel') em uma Entidade(Entity) do BD.
@Table(name = "tb_cadastro") // nome, por convenção, tem que ser em 'snake case' e tudo minúsculo e com 'tb' como prefixo.
@Data // Com esta annotation, não se precisará criar os 'getters' e 'setters' manualmente, pois estes dois já vem embutido nesta 'annotation', que vem da dependência 'Lombok'.
@NoArgsConstructor
@AllArgsConstructor // Se for adicionados campos novos, como "private String corDoOlho", o Lombok, através desta annotation o incluirá automaticamente.

public class NinjaModel { // É uma classe e não uma 'Entidade'!

    @Id // As 'annotations' sempre começam com @ + primeira letra em maiúscula. Indica que o atributo imediatamente abaixo terá características de 'id'
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A '@Id' e '@GeneratedValue' são usadas ao mesmo tempo para fazer a mesma coisa. Passa a extratégia de como vai se gerar o 'id', através de 'strategy = GenerationType.IDENTITY'. Com 'GenerationType.AUTO' funcionaria, mas para indicar que quer trabalhar com números apenas se usa 'GenerationType.IDENTITY', gerando sequêncialmente números únicos.
    private Long id; // É necessário ter o id, porque torna único cada registro. É do tipo 'Long' porque poderá ter muitos registros. O 'java' vai incrementar automaticamente.

    private String nome;

    @Column(unique = true) // Torna o email bloqueado para repetições. Esta 'annotation' também é usado para CPF, RG, número de passaporte.
    private String email;

    private int idade;

    //Cada Ninja (NinjaModel) só pode estar associado a uma única missão (MissoesModel).
    //Mas uma missão pode estar associada a vários ninjas.
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreing Key ou chave estrangeira. Aqui foi criado um campo novo chamado "missoes_id".
    private MissoesModel missoes; // Terei que fazer a classe 'MissoesModel'

    //public NinjaModel() { // 'No args constructor' é um construtor vazio.
    //} Foi comentado porque já foi declarado através da annotation "Lombok", na linha 11 deste arquivo, pois caso contrário, obterei o erro: "Constructor without parameters".

/*    public NinjaModel(String nome, String email, int idade, MissoesModel missoes) { // Quase 'All args Constructor'. Porque faltou os campos novos criados em relação à Missoes.
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.missoes = missoes;
    } Está comentado pelo fato de já se ter a annotation do Lombok, @AllargsContructor, na linha 13 */
    //OBS: Os 'getters' e 'setters' também não são necessários por conta da dependência 'Lombok'.

    @Override // Sobrecarga do método "toString"
    public String toString() {
        return "NinjaModel{" +
                "idade=" + idade +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}

//'Annotations' são, teoricamente, um amontoado de código "pré-pronto", que chamamos usando o '@'.
