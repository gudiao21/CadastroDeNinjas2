package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController // Usado para controlar a rota, ou seja, para dizer que toda a classe "MissoesController" será um controller!
@RequestMapping("missoes") // Anotation para mapear as minha APIs, ou seja, as 'paths' após o 'localhost:8080'. "missoes" adicionará "missoes/..." em todas as rotas abaixo.
public class MissoesController {

    // Get -- Mandar uma requisição para mostrar as missões
    @GetMapping("/listar")
    public String listarMissao() {
        return "Missões listadas com sucesso";
    }

    // Post -- Mandar uma requisição para criar as missões
    @PostMapping("/criar") // O 'localhost:8080' será implícito
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    // Put -- Mandar uma requisição para alterar as missões
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso";
    }

    // Delete -- Manda uma requisição para deletar uma missão
    @DeleteMapping("/deletar")
    public String deletarMisao() {
        return "Missao deletada com sucesso";
    }
}
