package dev.java10x.CadastroDeNinja.Ninjas;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // é uma 'annotation' para identificar que esta classe passa a ser um controller entre o 'usuário' e o 'BD', por exemplo.
@RequestMapping ("/ninjas")// Trabalha junto com o '@RestController' para controladores, haja vista, que esta classe também mapeará rotas.
public class NinjaController {

    private final NinjaService ninjaService; // Faz a 'injeção de dependência' do 'ninjaService.java', que faz parte da camada de 'Service', ou seja, é aqui que ocorre a comunicação entre as camadas 'controller' e 'service'. É de boa prática a injeção de dependência ser 'final'.

    public NinjaController(NinjaService ninjaService) { // Inicialização do construtor para 'NinjaService'.
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas") // Na verdade, ocultamente, o nome completo da rota será 'localhost:8080/BoasVindas', é um 'endpoint'
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }

    // Adicionar Ninja (1 - CREATE)
    @PostMapping("/criar") // O método POST é o contrário do GET, pois ele pega o registro em forma de Json, do usário, e DESSERIALIZA(JSON -> Ojeto Java) para o bd, exceto o 'id' que é automático.
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) { // O '@RequestBody' é a annotation que pega um registro do usuário e o DESERIALIZA para o BD, em Json (JavaScript object notation). Não é mais "NinjaModel" e sim "NinjaDTO" para conferir mais camadas a aplicação proporcionando mais segurança. Esta mudança é recomendada depois de se fazer o "NinjaMapper.java".
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED) // Retorna para o SERVIDOR uma mensagem de status code de criado.
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId()); // Retorna para o USUÁRIO uma mensagem personalizada de registro criado.
    }

    // Mostrar TODOS os Ninjas (2 - READ)
    @GetMapping("/listar") // O GET faz uma serialização do BD, em Json, para mostrar para o usuário
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas(); // Retorna a instância do meu serviço chamado 'ninjaService', que por sua vez acessa o método 'listarNinjas()'. O 'ninjaService.listarNinjas' é possível aqui porque fizemos a injeção de dependência na linha 11 e a criação do construtor (linha 13 a 15). Como o 'service' está conectado ao 'repository', então por isto do acesso a query da JPA. "ninjaService" é a instância do serviço que foi injetada via construtor ou com @Autowired. Aqui, literalmente, ocorre a troca do DTO com o service.
        if (ninjas.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build(); // Sem conteúdo, sem body
        }
        return ResponseEntity.ok(ninjas); // O 'Ok' é simplesmente o status code 200
    }

    // Mostrar Ninja por id (2 - READ)
    @GetMapping("/listar/{id}") // O '{id}' é chamado de 'path variable'
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninjaExistente = ninjaService.listarNinjasPorId(id);
            if (ninjaExistente != null) {
                return ResponseEntity.ok(ninjaExistente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja de Id: " + id + ", NÃO foi encontrado ou NÃO exite no banco de dados!");
        }
    }

   // Alterar dados do Ninja (4 - UPDATE)
    @PutMapping("/alterar/{id}") // O 'Put' é uma mistura do 'getbyid' com o 'Post'
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaDTO) { // 'ninjaAtualizado' seria o corpo da requisição alterado pelo usuário.
        NinjaDTO ninjaExistente = ninjaService.listarNinjasPorId(id);

        if (ninjaExistente != null) {
            NinjaDTO ninjaAtualizado = ninjaService.atualizarNinja(id,ninjaDTO);
            return ResponseEntity.ok(ninjaExistente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja de Id: " + id + ", NÃO foi encontrado ou não existe no banco de dados!");
            }
    }

    // Deletar Ninja (5 - DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) { // Se o ninja com o respectivo 'id' existir
            ninjaService.deletarNinjaPorId(id); // Deletar ninja, pois a verificação se existe eu já fiz acima
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
       } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o id " + id + " não encontrado.");
            }
    }
}
