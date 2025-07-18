package dev.java10x.CadastroDeNinja.Ninjas;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public List<NinjaDTO> listarNinjas() { // Este método é chamado lá no 'NinjaController', linha 31.
        List<NinjaModel> ninjas =  ninjaRepository.findAll(); // Este método do repositório 'ninjaRepository' é possível graças à linha 10, onde 'Service' se comunica com 'Repository' que por sua vez se comunica com o BD. O 'findAll' é equivalente ao 'SELECT * FROM TB_CADASTRO' da query do BD, ou seja, o 'fndAll' seria uma 'query' em forma de método.
        return ninjas.stream()
            .map(ninjaMapper::map)// Para cada objeto NinjaModel que passa na esteira, ela chama o método map de um objeto chamado ninjaMapper. Esse ninjaMapper é uma classe que você criou (um Mapper/Converter) cuja única responsabilidade é saber como converter um NinjaModel em um NinjaDTO.
            .collect(Collectors.toList()); //O método .collect() pega todos os itens que já foram processados na esteira (que agora são objetos NinjaDTO) e os agrupa em um "pacote final". Collectors.toList() especifica qual deve ser o formato desse pacote: uma nova List.
    } /* Ex. de como é feita esta consulta(findall) no sql: "SELECT * FROM TB_CADASTRO;", por exemplo. */

    // Listar ninjas por ID
    public NinjaDTO listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id); /* 'Optional' porque o 'id' do ninja pode existir ou não */
        return ninjaPorId.map(ninjaMapper::map).orElse(null); /* 'orElse' é um método que vem da própria classe "java.util.Optional", que significa que vai retornar o NinjaDTO (se o ninja foi encontrado e mapeado) ou Null (Se o ninja não foi encontrado). "ninjaPorId.map" transforma o 'NinjaModel' em um 'NinjaDTO', justamente para se retornar um 'DTO' e não um 'Model', expondo assim a segurança da aplicação. */
    } /* Ex. de como é feita esta consulta(findById) no sql: "SELECT * FROM TB_CADASTRO WHERE id=2;", por exemplo. */

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO); // Conversão DTO -> Model - Usa um mapper (classe separada, como NinjaMapper.java) para transformar o DTO em um objeto da entidade NinjaModel, que é a classe que o JPA usa para salvar no BD.
        ninja = ninjaRepository.save(ninja); // = "INSERT INTO TB_CADASTRO (id, nome, email, idade, img_url, missoes_id, rank) VALUES ('Sakura Haruno', 'sakura@email.com', 15, 'https://url_da_imagem.com', ', 'Gennin');", no BD. Persiste o ninja no banco de dados usando o JpaRepository. O JPA retorna o objeto atualizado (com o id preenchido, por exemplo).
        return ninjaMapper.map(ninja); // Converte "Model" para "DTO", ou seja, converte novamente o "NinjaModel" salvo para "NinjaDTO" e devolve como resposta para o controller.
    }

    // Deletar Ninja - Tem que ser um método VOID
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id); // Chama a instância do 'ninjaRepository' para habilitar a deleção do ninja pelo método do JPA 'deleteById'.
    }

    //Novo - Atualizar ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }

    // Antigo - Atualizar ninja - É a junção do método 'getbyid' (para saber se o 'id' exite) para somente depois se utilizar do método 'Post'. Já o parâmetro 'NinjaModel ninjaAtualizado' passa o corpo da requisição em json que se quer alterar.
    //public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaAtualizado) { // O parâmetro 'Long id' é para informar qual 'id' se quer atualizar.
        //if (ninjaRepository.existsById(id)) { // Validação simples para saber se o 'id' informado existe entre uma lista que está salva em BD. Isto através do método pronto do JPA 'existById(id)'.
            //ninjaAtualizado.setId(id); // Atualiza o registro do 'id' recebido como parâmetro, ou seja, sobrescreve o 'id' antigo. Se o mesmo não existir, ele sai do 'if' e vai para o 'return' de fora do bloco 'return ninjaRepository.save(ninjaAtualizado);'.
            //return ninjaRepository.save(ninjaAtualizado); // Pega todo o 'ninjaAtualizado', que é o corpo da requisição em Json para ser guardado em BD através do método do JPA 'save'
        //}
        //return null; // Será usado caso o 'id' que se quer atualizar não exista no BD.
    //}
}

//  1) O Ato de se pegar do BD algo ou do nosso Model e levar para uma área externa é chamado de 'SERIALIZAÇÃO', em um padrão JSON (JavaScript Object Notation), por exemplo.