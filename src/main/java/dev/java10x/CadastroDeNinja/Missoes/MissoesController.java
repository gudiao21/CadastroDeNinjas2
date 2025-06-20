package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.web.bind.annotation.*;

/* A camada de controller também é conhecido como camada de apresentação, pois ela está mais próxima do usuário. */
@RestController // Usado para controlar a rota, ou seja, para dizer que toda a classe "MissoesController" será um controller!
@RequestMapping("/missoes") // Annotation para mapear as minha APIs, ou seja, as 'paths' após o 'localhost:8080'. "/missoes" adicionará "/missoes..." em todas as rotas abaixo, neste caso, como pode ser visto.
public class MissoesController {

    // Get -- Mandar uma requisição para mostrar as missões
    @GetMapping("/listar")
    public String listarMissao() {
        return "Missões listadas com sucesso.";
    }

    // Post -- Mandar uma requisição para criar as missões
    @PostMapping("/criar") // O 'localhost:8080' será implícito
    public String criarMissao() {
        return "Missao criada com sucesso.";
    }

    // Put -- Mandar uma requisição para alterar as missões
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso.";
    }

    // Delete -- Manda uma requisição para deletar uma missão
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missao deletada com sucesso.";
    }
}
