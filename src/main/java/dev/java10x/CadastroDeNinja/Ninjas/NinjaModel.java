package dev.java10x.CadastroDeNinja.Ninjas;

import dev.java10x.CadastroDeNinja.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cadastro")
@Data // Vem direto do Lombok e disponibiliza os getters, setters
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column (name = "img_url")
    private String imgUrl;

    @Column (name = "rank") // Adicionado aqui após ser mencionado no 'NinjaDTO.java', que é uma 'cópia' sem responsabilidades e conexões com o BD. O ideal é não alterar o 'Model'. Contudo, se for trabalhar com 'DTO', poderá assim alterar o seu 'Model' se utilizando sempre de migrations com suas respectivas versões.
    private String rank;

    @Column (name = "idade")
    private int idade;

    // @ManyToOne - Um ninja tem uma única missão
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreing Key ou chave estrangeira
    private MissoesModel missoes;
}

/*  Uma vez inicializado o seu banco de dados, este model não deve ser alterado, pois isso pode causar problemas na sua aplicação ou "conflito de banco de dados". Isto deverá ser feito apenas por "migrations"  */
