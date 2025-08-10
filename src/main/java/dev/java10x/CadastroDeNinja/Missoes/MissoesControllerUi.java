package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {
    private final MissoesService missoesService; // Injeção de dependência

    public MissoesControllerUi(MissoesService missoesService) {  // Inicializando com construtor e não por annotation.
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes"; // Retorna justamente o "resources/templates/listarMissoes.html", que é o nome da página que reenderiza.
    }
}
