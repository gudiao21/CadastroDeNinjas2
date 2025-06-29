package dev.java10x.CadastroDeNinja.Ninjas;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // é uma 'annotation' para identificar que esta classe passa a ser um controller entre o 'usuário' e o 'BD', por exemplo.
@RequestMapping ("/ninjas")// Trabalha junto com o '@RestController' para controladores, haja visto, que esta classe também mapeará rotas.
public class NinjaController {

    private NinjaService ninjaService; // Faz a injeção de dependência do ninjaService, que faz parte da camada de 'Service', ou seja, é aqui que ocorre a comunicação entre as camadas 'controller' e 'service'.

    public NinjaController(NinjaService ninjaService) { // Inicialização do construtor para 'NinjaService'.
        this.ninjaService = ninjaService;
    }

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
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas(); // Retorna a instância do meu serviço chamado 'ninjaService', que por sua vez acessa o método 'listarNinjas()'. O 'ninjaService.listarNinjas' é possível aqui porque fizemos a injeção de dependência na linha 11 e a criação do construtor (linha 13 a 15). Como o 'service está conectado ao 'repository', então por isto do acesso a query da JPA.
    }

    // Mostrar Ninja por id (3 - READ)
    @GetMapping("/listarID/{id}")
    public ResponseEntity<NinjaModel> listarNinjaByID(@PathVariable Long id) {
        return ninjaService.listarNinjaByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
