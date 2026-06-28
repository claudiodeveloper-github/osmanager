package com.osmanager.controller;

import com.osmanager.entity.Equipamento;
import com.osmanager.repository.ClienteRepository;
import com.osmanager.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoRepository equipamentoRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("equipamentos", equipamentoRepository.findAll());
        return "equipamentos/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("equipamento", new Equipamento());
        // Enviamos a lista de clientes para a tela, pois todo equipamento pertence a um cliente
        model.addAttribute("clientes", clienteRepository.findAll());
        return "equipamentos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Equipamento equipamento) {
        equipamentoRepository.save(equipamento);
        return "redirect:/equipamentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("equipamento", equipamentoRepository.findById(id).orElse(new Equipamento()));
        model.addAttribute("clientes", clienteRepository.findAll());
        return "equipamentos/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        equipamentoRepository.deleteById(id);
        return "redirect:/equipamentos";
    }
}