package dev.java10x.CadastroDeNinja.Ninjas;

import org.springframework.stereotype.Component;

//@Mapper -> 'annotation' que é responsável por fazer a correlação entre os atributos do 'DTO' e do 'Model' e vice-versa. É necessário baixar a sua dependência. Contudo, poderá ser feito manualmente como estamos fazendo neste caso deste arquivo que estamos.
@Component // "annotation" obrigatória no "Mapper".
public class NinjaMapper {
    public NinjaModel map(NinjaDTO ninjaDTO) { // Como é uma etapa repetitiva, pode-se recorrer ao 'ChatGPT'. Mapeamento de uma entidade (Model) para um DTO.
        NinjaModel ninjaModel = new NinjaModel(); // Inicializa um novo objeto do tipo 'NinjaModel'.

        ninjaModel.setId(ninjaDTO.getId()); // 'setId' é para a gente colocar um valor. "Pegue o id vindo da requisição (DTO) e coloque esse valor dentro do Model, que será salvo no banco."
        ninjaModel.setNome(ninjaDTO.getNome()); // Pega o atributo 'nome' do 'Model' e o coloca (através de correlação) no 'DTO'.
        ninjaModel.setEmail(ninjaDTO.getEmail()); // Pega o atributo 'email' do 'Model' e o coloca (através de correlação) no 'DTO'.
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl()); // Pega o atributo 'imgUrl' do 'Model' e o coloca (através de correlação) no 'DTO'.
        ninjaModel.setIdade(ninjaDTO.getIdade()); // Pega o atributo 'idade' do 'Model' e o coloca (através de correlação) no 'DTO'.
        ninjaModel.setRank(ninjaDTO.getRank()); // Pega o atributo 'rank' do 'Model' e o coloca (através de correlação) no 'DTO'.
        ninjaModel.setMissoes(ninjaDTO.getMissoes()); // // Pega o atributo 'missoes' do 'Model' e o coloca (através de correlação) no 'DTO'.

        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel) {  // Mapeando de uma entidade para um DTO
        NinjaDTO ninjaDTO = new NinjaDTO(); // Inicializa um novo objeto do tipo 'NinjaDTO'.

        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setRank(ninjaModel.getRank());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());

        return ninjaDTO;
    }
}
// OBS: O 'Mapper' faz a relação entre as propriedades (id, nome, email,...) do 'DTO' com as propriedades (id, nome, email,...) do 'Model'.
// OBS2: O 'DTO' é importante para quando se quer escalar o código. Sendo mais fácil para testar, vai ser mais uma camada de segurança, alé de deixar o código mais organizado.
// OBS3: O 'Mapper' é uma explicação para o Java (spring), que os campos do 'DTO' terá a mesma equivalência dos campos do 'Model' (Entidade).