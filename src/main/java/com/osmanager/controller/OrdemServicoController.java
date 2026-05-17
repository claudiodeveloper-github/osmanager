package com.osmanager.controller;

import com.osmanager.entity.OrdemServico;
import com.osmanager.entity.Status;
import com.osmanager.repository.ClienteRepository;
import com.osmanager.repository.EquipamentoRepository;
import com.osmanager.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/os")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;
    private final ClienteRepository clienteRepository;
    private final EquipamentoRepository equipamentoRepository;

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "ordens",
                ordemServicoService.listarTodas()
        );

        return "os/listar";
    }

    @GetMapping("/nova")
    public String nova(Model model) {

        model.addAttribute("os", new OrdemServico());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("equipamentos", equipamentoRepository.findAll());
        model.addAttribute("statusList", Status.values());

        return "os/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute OrdemServico os) {

        ordemServicoService.abrirOS(os);

        return "redirect:/os";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        model.addAttribute(
                "os",
                ordemServicoService.buscarPorId(id)
        );

        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("equipamentos", equipamentoRepository.findAll());
        model.addAttribute("statusList", Status.values());

        return "os/form";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute OrdemServico os) {

        ordemServicoService.atualizarStatus(
                os.getId(),
                os.getStatus()
        );

        return "redirect:/os";
    }
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        ordemServicoService.excluir(id);
        return "redirect:/os";
    }
}