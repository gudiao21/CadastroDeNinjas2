package dev.java10x.CadastroDeNinja.Ninjas;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service // Tranforma está classe em uma camada de 'serviço'
public class NinjaService { // Para o 'service' se comunicar com o 'repositório' é preciso eu fazer a injeção de dependência (linha 10) e criação do construtor (linhas 12 a 13).

    private NinjaRepository ninjaRepository; // Injeta a dependência do meu repositório 'NinjaRepository.java', porque é o repositório tem que se conectar com o meu BD. Então é aqui que a camada 'Service' se comunica com a camada 'Repository. O 'repository' (NinjaRepository.java) extends 'JpaRepository'.

    public NinjaService(NinjaRepository ninjaRepository) { // Optamos pelo constructor, mas poderia ser pela annotation @Autowired, sendo um ou outro, sabendo que há algumas diferenças entre eles. Aqui a dependência da linha 10 deste arquivo é inicializada efetivamente, ou seja, através deste contrutor. Inicializar por contrutor é mais indicado, porém pode ser por annotation também.
        this.ninjaRepository = ninjaRepository;
    }

    // Listar TODOS os meus ninjas (implementa a lógica para listar todos os ninjas)
    public List<NinjaModel> listarNinjas() { // Este método é chamado lá no 'NinjaController', linha 31.
        return ninjaRepository.findAll(); // Este método do repositório 'ninjaRepository' é possível graças à linha 10, onde 'Service' se comunica com 'Repository' que por sua vez se comunica com o BD. O 'findAll' é equivalente ao 'SELECT' da query do BD.
    } /* Ex. de como é feita esta consulta(findall) no sql: "SELECT * FROM TB_CADASTRO;", por exemplo. */

    // Listar ninjas por ID
    public NinjaModel listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id); /* 'Optional' porque o 'id' do ninja pode existir ou não */
        return ninjaPorId.orElse(null);
    } /* Ex. de como é feita esta consulta(findById) no sql: "SELECT * FROM TB_CADASTRO WHERE id=2;", por exemplo. */
}

/*
 1) O Ato de se pegar do BD algo ou do nosso Model e levar para uma área externa é chamado de 'SERIALIZAÇÃO', em um padrão JSON (JavaScript Object Notation), por exemplo.
 */