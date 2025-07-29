package dev.java10x.CadastroDeNinja.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.CadastroDeNinja.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table (name = "tb_Missoes")
@Data // annotation do Lombok para gerar os 'getters' e 'setters'.
@NoArgsConstructor // annotation do Lombok para gerar, ocultamente, o 'NoArgsContructor'.
@AllArgsConstructor // annotation do Lombok para gerar, ocultamente, o 'AllArgsConstructor'.
@ToString
public class MissoesModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    // Seria uma missão para muitos ninjas
    @OneToMany(mappedBy = "missoes") // Aqui indica que a chave-estrangeira declarada em 'NinjaModel.java', na linha 29, será "missoes" (nome do campo criado) como declarado aqui. Normalmente, o 'mappedBy' é usado no lado do '@OneToMany', pois o lado do '@ManyToOne' nunca usa o 'mappedBy' por via de regra!
    @JsonIgnore // 'annotation' que Ignora a serialização em um 'loop de serialização' da lista de todos os ninjas que se refere a linha de código abaixo.
    private List<NinjaModel> ninjas;

}
