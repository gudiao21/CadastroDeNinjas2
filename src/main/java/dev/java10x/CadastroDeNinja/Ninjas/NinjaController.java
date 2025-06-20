package dev.java10x.CadastroDeNinja.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController // é uma 'annotation' para identificar uma classe como uma controladora entre o 'usuário' e o 'BD', por exemplo.
@RequestMapping // Trabalha junto com o '@RestController' para controladores, haja visto, que esta classe também mapeará rotas.
public class NinjaController {

    @GetMapping("/boasvindas") // Na verdade, ocultamente, o nome completo da rota será 'localhost:8080/BoasVindas', é um 'endpoint'
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }

    // Adicionar Ninja (1 - CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    // Mostrar todos os Ninjas (2 - READ)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas() {
        return "Mostrar todo os ninjas";
    }

    // Mostrar Ninja por id (3 - READ)
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorID() {
        return "Mostrar Ninja por id";
    }

   // Alterar dados dos Ninjas (4 - UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorId() {
        return "Alterar Ninja por id";
    }

    // Deletar Ninja (5 - DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId() {
        return "Ninja deletado por id";
    }
}
