package dev.java10x.CadastroDeNinja.Ninjas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // é uma 'annotation' para identificar uma classe como uma controladora entre o 'usuário' e o 'BD', por exemplo.
@RequestMapping // Trabalha junto com o '@RestController' para controladores, haja visto, que esta classe também mapeará rotas.
public class NinjaController {

    @GetMapping("/boasvindas") // Na verdade, ocultamente, o nome completo da rota será 'localhost:8080/BoasVindas'
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }
}
