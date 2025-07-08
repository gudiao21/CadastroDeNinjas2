package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Transforma esta classe em uma camada de 'serviço'
public class MissoesService { // Para 'service se comunicar com o 'repositório' é preciso eu fazer a injeção de dependência e a criação do construtor.

    private MissoesRepository missoesRepository; // Injeta a dependência do meu repositório 'MissoesRepository.java' no meu 'MissoesService.java'. É aqui que a camada 'Service' se comunica com a 'Repository'.

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Listar TODOS as minhas missões (implementa a lógica para listar todas as missões)
    public List<MissoesModel> listarMissoes() { // Este método é chamado lá no "MissoesController"
        return missoesRepository.findAll(); // Este método do repositório 'missoesRepository' é possível graças ao 'Service' se comunicar com o 'Repository' que por sua vez se comunica com o BD. O 'findAll' é equivalente ao 'SELECT' que simula as queries do BD (ex: H2, MySQL, ...)
    } /* Ex. de como é feita esta consulta '(findall)' no sql: "SELECT + FROM TB_CADASTRO;", por exemplo. */

    // Listar missões por ID
    public MissoesModel listarMissoesPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id); /* 'Optional' porque o 'id' do ninja pode existir ou não */
        return missaoPorId.orElse(null);
    } /* Ex. de como é feita esta consulta(findById) no sql: "SELECT * FROM TB_CADASTRO WHERE id=2;", por exemplo. */

    // Criar uma nova missão
    public MissoesModel criarMissao(MissoesModel missao) {
        return missoesRepository.save(missao); /* = "INSERT INTO TB_CADASTRO (id, nome, dificuldade) VALUE (1, "Naruto", "Fácil"). O 'return' retorna o objeto salvo para quem chamou o método 'criarmissao'. Quem registra no BD é o método 'save' do JPA. */
    }

    // Deletar Missao - Tem que ser um método VOID
    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id); // = "DELETE FROM TB_MISSOES WHERE id = 2", por exemplo. Chama a instância do 'missoesRepository' para se utilizar do método JPA 'deleteById'.
    }
}
