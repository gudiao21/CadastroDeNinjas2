package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* A camada de controller também é conhecido como camada de apresentação, pois ela está mais próxima do usuário. */
@RestController // Usado para controlar a rota, ou seja, para dizer que toda a classe "MissoesController" será um controller!
@RequestMapping("/missoes") // Annotation para mapear as minhas APIs, ou seja, as 'paths' após o 'localhost:8080'. "/missoes" adicionará "/missoes..." em todas as rotas abaixo, neste caso, como pode ser visto.
public class MissoesController {

    private final MissoesService missoesService; // Faz a 'injeção de dependência' do 'missoesService.java', que faz parte da camada de 'Serviço', ou seja, aqui acorre a comunicação entre as camadas 'controller' e 'service' em uma Arquitetura de Camadas (Layered architecture). É importante as injeções de dependências serem 'final', aqui neste caso.

    public MissoesController(MissoesService missoesService) { // Inicialização do construtor para 'MissoesService'. Lembrando que "Injeção de dependência" entrega para uma classe os objetos que ela precisa, ao invés da própria classe criar ou buscar esse(s) objeto(s).
        this.missoesService = missoesService;
    }

    // Adicionar missão (1 - CREATE)
    @PostMapping("/criar") // O 'localhost:8080' será implícito. DESCERIALIZAÇÃO, ou seja, o 'POST' faz uma serialização, mas inversa.
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao) { // O '@RequestBody' é a annotation que pega um registro do usuário e o DESCERIALIZA para o BD, em Json.
        MissoesDTO novaMissao = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED) // Retorna para o SERVIDOR uma mensage,m de status code de criado.
                .body("Missão " + novaMissao.getNome() + " com o ID: " + novaMissao.getId() + ", criado com sucesso: "); // Retorna para o USUÁRIO uma mensagem personalizada de registro criado.
    }

    // Mostrar TODAS as missões. SERIALIZAÇÃO (2 - READ)
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        if (missoes.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build(); // Sem conteúdo, sem body.
        }
        return ResponseEntity.ok(missoes); // O 'Ok" é simplesmente o status code 200
    }

    // Mostrar Missão por id (2 - READ)
    @GetMapping("/listar/{id}") /* O '{id}' é chamado de 'path variable' */
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id) { // O '{id}' é chamado de 'path variable'
        MissoesDTO missaoExistente = missoesService.listarMissoesPorId(id);
        if (missaoExistente != null) {
            return ResponseEntity.ok(missaoExistente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão de Id: " + id + ", NÂO foi encontrada ou NÂO existe no banco de dados!");
        }
    }

    // Alterar dados da Missão (3 - UPDATE)
    @PutMapping("/alterar/{id}") // O '{id}' é chamado de 'path variable'.
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missaoDTO) {
        MissoesDTO missaoExistente = missoesService.listarMissoesPorId(id);

        if (missaoExistente != null) {
            MissoesDTO missaoAtualizada = missoesService.atualizarMissao(id, missaoDTO);
            return ResponseEntity.ok(missaoExistente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão com id: " + id + ", NÃO foi encontrada ou NÃO existe no banco de dados!");
            }
    }

    // Delete -- Manda uma requisição para Deletar uma missão (5 - DELETE)
    @DeleteMapping("/deletar/{id}") // Criando o 'endpoint' para deletar
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id) { // O método para deletar, sempre vai ser VOID.
        if (missoesService.listarMissoesPorId(id) != null) {
            missoesService.deletarMissaoPorId(id); // Deletar missao, pois a verificação já foi feita acima.
            return ResponseEntity.ok( "Missão com o Id " + id + ", deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão com o Id " + id + " NÃO encontrada!");
        }
    }
}
