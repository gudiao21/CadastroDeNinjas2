package dev.java10x.CadastroDeNinja.Ninjas;

import dev.java10x.CadastroDeNinja.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Vem do lombok para gerar os 'getters' e 'setters'.
@AllArgsConstructor // Vem do 'lombok' para gerar ocultamente o 'AllArgsContructor'.
@NoArgsConstructor // Vem do 'lombok' para gerar ocultamente (por debaixo dos panos) o 'NoArgsConstructor'.
public class NinjaDTO {

    private long id;

    private String nome;

    private String email;

    private String imgUrl;

    private int idade;

    private String rank; // Esta propriedade foi adicionada a migration V2 e primeiramente mencionada nesta linha e depois no "NinjaModel.java".

    private MissoesModel missoes;
}

// OBS: O 'DTO' serve para tirar a responsabilidade da nossa 'Entidade' (Model). Fazendo assim, o 'DTO' conversar com o 'Service' e não mais o "Model" com o "Servicce", pois o 'DTO' é, praticamente, uma cópia do meu 'Model'.
// OBS2: O 'DTO", fazendo uma analogia, seria uma camada a mais de proteção em minha aplicação.
// OBS3: O 'DTO' é, praticamente, uma cópia do meu 'Model', porém não tem acesso direto ao meu BD, se tornando assim mais seguro por ser uma abstração.
// OBS4: O 'DTO' é literalmente a cópia do meu 'Model' sem as característica de 'entidade' como: algumas annotations (@Id, @Column, @Entity, @ManyToOne, ...).
