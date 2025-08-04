package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Transforma esta classe em uma camada de 'serviço'
public class MissoesService { // Para 'service se comunicar com o 'repositório' é preciso eu fazer a injeção de dependência e a criação do construtor.

    private final MissoesRepository missoesRepository; // Injeta a dependência do meu repositório 'MissoesRepository.java' no meu 'MissoesService.java'. É aqui que a camada 'Service' se comunica com a 'Repository'. Aqui é importante ser 'final'
    private final MissoesMapper missoesMapper; // Inicia uma instância do missoesMapper, sendo importante ser 'final'.

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) { // Optamos por criar este constructor, mas poderia ser pela annotation '@Autowired', sendo um ou outro, sabendo que há algumas diferenças entre eles. Aqui a dependência da linha 10 deste arquivo é inicializada efetivamente, ou seja, através deste contrutor. Inicializar por contrutor é mais indicado, porém pode ser por 'annotation' também (menos indicado).
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Listar TODOS as minhas missões (implementa a lógica para listar todas as missões)
    public List<MissoesDTO> listarMissoes() { // Este método é chamado lá no "MissoesController"
        List<MissoesModel> missoes = missoesRepository.findAll(); // Este método do repositório 'missoesRepository' é possível graças à linha 11, onde 'Service' se comunica com 'Repository' que por sua vez se comunica com o BD. O 'findAll' é equivalente ao 'SELECT * FROM TB_CADASTRO' da query do BD MySQL, ou seja, o 'fndAll' seria uma 'query' em forma de método. Aqui, efetivamente, ocorre a consulta usando o Model.
        return missoes.stream()
            .map(missoesMapper::map) // Para cada objeto MissoesModel que passa na esteira, ela chama o método map de um objeto chamado missoesMapper. Esse missoesMapper é uma classe que você criou (um Mapper/Converter) cuja única responsabilidade é saber como converter um MissoesModel em um MissoesDTO e vice-versa. Aqui, efetivamente, ocorre a conversão de cada MissoesModel em MissoesDTO. Veja que o 'Service' lida com o Model internamente, mas retorna DTOs para o Controller (camada de apresentação).
                .collect(Collectors.toList());
        //Antigo -> return missoesRepository.findAll(); // Este método do repositório 'missoesRepository' é possível graças ao 'Service' se comunicar com o 'Repository' que por sua vez se comunica com o BD. O 'findAll' é equivalente ao 'SELECT' que simula as queries do BD (ex: H2, MySQL, ...)
    } /* Ex. de como é feita esta consulta '(findall)' no sql: "SELECT + FROM TB_CADASTRO;", por exemplo. */

    // Listar missões por ID
    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id); /* 'Optional' porque o 'id' do ninja pode existir ou não */
        return missaoPorId.map(missoesMapper::map).orElse(null); // 'orElse' é um método que vem da própria classe "java.util.Optional", que significa que vai retornar o NinjaDTO (se o ninja foi encontrado e mapeado) ou Null (Se o ninja não foi encontrado). "ninjaPorId.map" transforma o 'NinjaModel' em um 'NinjaDTO', justamente para se retornar um 'DTO' e não um 'Model', expondo assim a segurança da aplicação. */
    } /* Ex. de como é feita esta consulta(findById) no sql: "SELECT * FROM TB_CADASTRO WHERE id=2;", por exemplo. */

    // Criar uma nova missão
    public MissoesDTO criarMissao(MissoesDTO missaoDTO) {
        MissoesModel missao = missoesMapper.map(missaoDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao); /* = "INSERT INTO TB_MISSOES (id, nome, dificuldade) VALUE (1, "Naruto", "Fácil"). */
    }

    // Deletar Missao
    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id); // = "DELETE FROM TB_MISSOES WHERE id = 2", por exemplo. Chama a instância do 'missoesRepository' para se utilizar do método JPA 'deleteById'.
    }

    // Novo - Atualizar missao
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO) {
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id); // Sobrescreve o 'id' antigo para o novo.
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada); //Sobrescreve e atualiza os dados atualizados da missao.
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
}
