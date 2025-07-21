package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.stereotype.Component;

//@Mapper -> 'annotation' que é responsável por fazer a correlação entre os atributos do 'DTO' e do 'Model' e vice-versa de forma mais rápida e automática. É necessário baixar a sua dependência. Contudo, poderá ser feito manualmente como estamos fazendo neste caso deste arquivo que estamos.
@Component // Para esta classe, efetivamente, tornar-se um 'mapper' é obrigatório esta annotation.
public class MissoesMapper {
    public MissoesModel map(MissoesDTO missoesDTO) { // Mapeamento de uma entidade (Model) para um DTO.
        MissoesModel missoesModel = new MissoesModel(); // Inicializa um novo objeto do tipo 'MissoesModel'.

        missoesModel.setId(missoesDTO.getId()); // Pega o 'id' vendo da requisição (DTO) e coloca esse valor dentro do Model, que será salvo no banco.
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel) {
        MissoesDTO missoesDTO = new MissoesDTO(); // Inicializa um novo objeto do tipo 'NinjaDTO'.

        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());

        return missoesDTO;
    }
}
