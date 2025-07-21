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

    // Adicionar missão (1 - CREATE)
    @PostMapping("/criar") // O 'localhost:8080' será implícito. DESCERIALIZAÇÃO, ou seja, o 'POST' faz uma serialização, mas inversa.
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missao) { // O '@RequestBody' é a annotation que pega um registro do usuário e o DESCERIALIZA para o BD, em Json.
        return missoesService.criarMissao(missao); // Retorna a instância 'missoesService' para quem o chamou. O nome 'missao' é escolhido pelo dev.
    }

    // Mostrar TODAS as missões. SERIALIZAÇÃO (2 - READ)
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes(); /* "missoesService" é a instância do serviço que foi injetada via construtor ou com @Autowired. */
    }

    // Mostrar Missão por id (2 - READ)
    @GetMapping("/listar/{id}") /* O '{id}' é chamado de 'path variable' */
    public MissoesDTO listarMissaoPorId(@PathVariable Long id) { // O '{id}' é chamado de 'path variable'
        return missoesService.listarMissoesPorId(id);
    }

    // Alterar dados da Missão (3 - UPDATE)
    @PutMapping("/alterar/{id}") // O '{id}' é chamado de 'path variable'.
    public MissoesDTO atualizarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada) {
        return missoesService.atualizarMissao(id, missaoAtualizada);
    }

    // Delete -- Manda uma requisição para Deletar uma missão (5 - DELETE)
    @DeleteMapping("/deletar/{id}") // Criando o 'endpoint' para deletar
    public void deletarMissaoPorId(@PathVariable Long id) { // O método para deletar, sempre vai ser VOID.
        missoesService.deletarMissaoPorId(id); // Se utiliza da instância "missoesService" para chamar o método 'deletarMissaoPorId'.
    }
}
