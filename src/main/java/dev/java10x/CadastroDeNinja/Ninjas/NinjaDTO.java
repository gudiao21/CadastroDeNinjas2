package dev.java10x.CadastroDeNinja.Ninjas;
import dev.java10x.CadastroDeNinja.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Vem do lombok para gerar os 'getters' e 'setters'.
@AllArgsConstructor // Vem do 'lombok' para gerar ocultamente o 'AllArgsContructor'.
@NoArgsConstructor // Vem do 'lombok' para gerar ocultamente (por debaixo dos panos) o 'NoArgsContructor'.
public class NinjaDTO {

    private long id;

    private String nome;

    private String email;

    private String imgUrl;

    private int idade;

    private String rank;

    private MissoesModel missoes;
}

// OBS: O 'DTO' serve para tirar a responsabilidade da nossa Entidade. Fazendo assim, o 'DTO' conversar com o 'Service' e não mais o "Model" com o "Servicce", pois o 'DTO' é, praticamente, uma cópia do meu 'Model'.
// OBS2: O 'DTO", fazendo uma analogia, seria uma camada a mais de proteção em minha aplicação.
