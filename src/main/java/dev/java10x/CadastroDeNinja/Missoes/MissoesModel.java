package dev.java10x.CadastroDeNinja.Missoes;

import dev.java10x.CadastroDeNinja.Ninjas.Controller.Service.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "tb_Missoes")
public class MissoesModel {

    @id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    // Seria uma missão para muitos ninjas
    @OneToMany(mappedBy = "missoes") // Aqui indica que a chave-estrangeira declarada em 'NinjaModel.java', na linha 25, será "missoes"(nome do campo criado) como declarado aqui. Normalmente, o 'mappedBy' é usado no lado do '@OneToMany', pois o lado do '@ManyToOne' nunca usa o 'mappedBy' por via de regra!
    private List<NinjaModel> ninja;

}
