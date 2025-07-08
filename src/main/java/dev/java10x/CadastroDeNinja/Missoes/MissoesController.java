package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/* A camada de controller também é conhecido como camada de apresentação, pois ela está mais próxima do usuário. */
@RestController // Usado para controlar a rota, ou seja, para dizer que toda a classe "MissoesController" será um controller!
@RequestMapping("/missoes") // Annotation para mapear as minhas APIs, ou seja, as 'paths' após o 'localhost:8080'. "/missoes" adicionará "/missoes..." em todas as rotas abaixo, neste caso, como pode ser visto.
public class MissoesController {

    private MissoesService missoesService; // Faz a 'injeção de dependência' do 'missoesService.java', que faz parte da camada de 'Serviço', ou seja, aqui acorre a comunicação entre as camadas 'controller' e 'service' em uma Arquitetura de Camadas (Layered architecture).
    public MissoesController(MissoesService missoesService) { // Inicialização do construtor para 'MissoesService'. Lembrando que "Injeção de dependência" entrega para uma classe os objetos que ela precisa, ao invés da própria classe criar ou buscar esse(s) objeto(s).
        this.missoesService = missoesService;
    }

    // Post -- Mandar uma requisição para criar a missão
    @PostMapping("/criar") // O 'localhost:8080' será implícito. DESCERIALIZAÇÃO, ou seja, o 'POST' faz uma serialização, mas inversa.
    public MissoesModel criarMissao(@RequestBody MissoesModel missao) { // O '@RequestBody' é a annotation que pega um registro do usuário e o DESCERIALIZA para o BD, em Json.
        return missoesService.criarMissao(missao); // Retorna a instância 'missoesService' para quem o chamou. O nome 'missao' é escolhido pelo dev.
    }

    // Get -- Mandar uma requisição para mostrar TODAS as missões. SERIALIZAÇÃO
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes(); /* "missoesService" é a instância do serviço que foi injetada via construtor ou com @Autowired. */
    }

    // Mostrar Missão por id (READ)
    @GetMapping("/listar/{id}") /* O '{id}' é chamado de 'path variable' */
    public MissoesModel listarMissaoPorId(@PathVariable Long id) { // O '{id}' é chamado de 'path variable'
        return missoesService.listarMissoesPorId(id);
    }

    // Put -- Mandar uma requisição para alterar as missões
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso.";
    }

    // Delete -- Manda uma requisição para deletar uma missão
    @DeleteMapping("/deletar/{id}") // Criando o 'endpoint' para deletar
    public void deletarMissaoPorId(@PathVariable Long id) { // O método para deletar, sempre vai ser VOID.
        missoesService.deletarMissaoPorId(id); // Se utiliza da instância "missoesService" para chamar o método 'deletarMissaoPorId'.
    }
}
