package dev.java10x.CadastroDeNinja.Ninjas;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // é uma 'annotation' para identificar que esta classe passa a ser um controller entre o 'usuário' e o 'BD', por exemplo.
@RequestMapping ("/ninjas")// Trabalha junto com o '@RestController' para controladores, haja vista, que esta classe também mapeará rotas.
public class NinjaController {

    private NinjaService ninjaService; // Faz a 'injeção de dependência' do 'ninjaService.java', que faz parte da camada de 'Service', ou seja, é aqui que ocorre a comunicação entre as camadas 'controller' e 'service'.

    public NinjaController(NinjaService ninjaService) { // Inicialização do construtor para 'NinjaService'.
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas") // Na verdade, ocultamente, o nome completo da rota será 'localhost:8080/BoasVindas', é um 'endpoint'
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }

    // Adicionar Ninja (1 - CREATE)
    @PostMapping("/criar") // O método POST é o contrário do GET, pois ele pega o registro em forma de Json, do usário, e DESERIALIZA para o bd, exceto o 'id' que é automático.
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) { // O '@RequestBody' é a annotation que pega um registro do usuário e o DESERIALIZA para o BD, em Json (JavaScript object notation). Não é mais "NinjaModel" e sim "NinjaDTO" para conferir mais camadas a aplicação proporcionando mais segurança. Esta mudança é recomendada depois de se fazer o "NinjaMapper.java".
        return ninjaService.criarNinja(ninja);
    }

    // Mostrar TODOS os Ninjas (2 - READ)
    @GetMapping("/listar") // O GET faz uma serialização do BD, em Json, para mostrar para o usuário
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas(); // Retorna a instância do meu serviço chamado 'ninjaService', que por sua vez acessa o método 'listarNinjas()'. O 'ninjaService.listarNinjas' é possível aqui porque fizemos a injeção de dependência na linha 11 e a criação do construtor (linha 13 a 15). Como o 'service' está conectado ao 'repository', então por isto do acesso a query da JPA. "ninjaService" é a instância do serviço que foi injetada via construtor ou com @Autowired.
    }

    // Mostrar Ninja por id (3 - READ)
    @GetMapping("/listar/{id}") // O '{id}' é chamado de 'path variable'
    public NinjaModel listarNinjasPorId(@PathVariable Long id) { // Uma vez passado o 'id', o mesmo vai fazer parte do Path por causa da annotation "@PathVariable".
        return ninjaService.listarNinjasPorId(id);
    }

   // Alterar dados do Ninja (4 - UPDATE)
    @PutMapping("/alterar/{id}") // O 'Put' é uma mistura do 'getbyid' com o 'Post'
    public NinjaModel atualizarNinjaPorId(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado) { // 'ninjaAtualizado' seria o corpo da requisição alterado pelo usuário.
        return ninjaService.atualizarNinja(id, ninjaAtualizado); // 'ninjaService' é a instância do meu 'service', aqui dentro do controller para que se possa ter acesso aos métodos do 'NinjaService.java' chamado 'atualizarNinja'.
    }

    // Deletar Ninja (5 - DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
    }
}
