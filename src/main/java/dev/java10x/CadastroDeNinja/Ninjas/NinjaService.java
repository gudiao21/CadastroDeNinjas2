package dev.java10x.CadastroDeNinja.Ninjas;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service // Transforma esta classe em uma camada de 'serviço'
public class NinjaService { // Para o 'service' se comunicar com o 'repositório' é preciso eu fazer a injeção de dependência (linha 12) e criação do construtor (linhas 14 a 16).

    private NinjaRepository ninjaRepository; // Injeta a dependência do meu repositório 'NinjaRepository.java', porque o repositório tem que se conectar com o meu BD. Então é aqui que a camada 'Service' se comunica com a camada 'Repository. O 'repository' (NinjaRepository.java) extends 'JpaRepository'.
    private NinjaMapper ninjaMapper; // Inicia uma instância do ninjamapper.

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) { // Optamos pelo constructor, mas poderia ser pela annotation @Autowired, sendo um ou outro, sabendo que há algumas diferenças entre eles. Aqui a dependência da linha 10 deste arquivo é inicializada efetivamente, ou seja, através deste contrutor. Inicializar por contrutor é mais indicado, porém pode ser por 'annotation' também (menos indicado).
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar TODOS os meus ninjas (implementa a lógica para listar todos os ninjas)
    public List<NinjaModel> listarNinjas() { // Este método é chamado lá no 'NinjaController', linha 31.
        return ninjaRepository.findAll(); // Este método do repositório 'ninjaRepository' é possível graças à linha 10, onde 'Service' se comunica com 'Repository' que por sua vez se comunica com o BD. O 'findAll' é equivalente ao 'SELECT * FROM TB_CADASTRO' da query do BD, ou seja, o 'fndAll' seria uma 'query' em forma de método.
    } /* Ex. de como é feita esta consulta(findall) no sql: "SELECT * FROM TB_CADASTRO;", por exemplo. */

    // Listar ninjas por ID
    public NinjaModel listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id); /* 'Optional' porque o 'id' do ninja pode existir ou não */
        return ninjaPorId.orElse(null); /* 'orElse' é um método que vem do JPA, que significa que vai mandar o NinjaModel ou o Null */
    } /* Ex. de como é feita esta consulta(findById) no sql: "SELECT * FROM TB_CADASTRO WHERE id=2;", por exemplo. */

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO); //
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja); /* = "INSERT INTO TB_CADASTRO (id, nome, email, idade, img_url, missoes_id, rank) VALUES ('Sakura Haruno', 'sakura@email.com', 15, 'https://url_da_imagem.com', ', 'Gennin');", no BD. */
    }

    // Deletar Ninja - Tem que ser um método VOID
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id); // Chama a instância do 'ninjaRepository' para habilitar a deleção do ninja pelo método do JPA 'deleteById'.
    }

    // Atualizar ninja - É a junção do método 'getbyid' (para saber se o 'id' exite) para somente depois se utilizar do método 'Post'. Já o parâmetro 'NinjaModel ninjaAtualizado' passa o corpo da requisição em json que se quer alterar.
    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado) { // O parâmetro 'Long id' é para informar qual 'id' se quer atualizar.
        if (ninjaRepository.existsById(id)) { // Validação simples para saber se o 'id' informado existe entre uma lista que está salva em BD. Isto através do método pronto do JPA 'existById(id)'.
            ninjaAtualizado.setId(id); // Atualiza o registro do 'id' recebido como parâmetro. Se o mesmo não existir, ele sai do 'if' e vai para o 'return' de fora do bloco 'return ninjaRepository.save(ninjaAtualizado);'.
            return ninjaRepository.save(ninjaAtualizado); // Pega todo o 'ninjaAtualizado', que é o corpo da requisição em Json para ser guardado em BD através do método do JPA 'save'
        }
        return null; // Será usado caso o 'id' que se quer atualizar não exista no BD.
    }
}

//  1) O Ato de se pegar do BD algo ou do nosso Model e levar para uma área externa é chamado de 'SERIALIZAÇÃO', em um padrão JSON (JavaScript Object Notation), por exemplo.