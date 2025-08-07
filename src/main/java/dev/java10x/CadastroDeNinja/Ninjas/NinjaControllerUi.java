package dev.java10x.CadastroDeNinja.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService; // Injeção de dependência

    public NinjaControllerUi(NinjaService ninjaService) { // Inicializando com Construtor e não por annotation.
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "Ninjas/listarNinjas"; // Retorna justamente o "resources/templates/listarNinjas.html", que é o nome da página que reenderiza.
    }

    @GetMapping("/deletar/{id}") // Atenção aqui para não usar a annotation "@DeleteMapping", estamos retornando a relistagem dos ninjas após a exclusão, atravéz do comando 'return "redirect:/ninjas/ui/listar"', logo aqui abaixo. Quem vai deletar mesmo vai ser o método 'deletarMissaoPorId' dentro de 'NinjaService.java'.
    public String deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id); // Esse é o momento em que o controller delega a responsabilidade de deletar para o serviço (Service Layer).
        return "redirect:/ninjas/ui/listar"; // Por causa deste retorno é que usamos a annotation '@GetMapping' e não '@DeleteMapping'.
    }

    // Ao clicar em "Ver Detalhes":
    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "Ninjas/detalhesninja";
        } else {  // Caso não tenha o ninja no bd
            model.addAttribute("messagem", "Ninja não encontrado.");
            return "listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "Ninjas/adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("messagem", "Ninja cadastrado com sucesso.");
        return "redirect:/ninjas/ui/listar";
    }
}
