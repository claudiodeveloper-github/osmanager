package com.osmanager.controller;

import com.osmanager.entity.Status;
import com.osmanager.repository.ClienteRepository;
import com.osmanager.repository.EquipamentoRepository;
import com.osmanager.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final OrdemServicoService ordemServicoService;
    private final ClienteRepository clienteRepository;
    private final EquipamentoRepository equipamentoRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("osAbertas",
                ordemServicoService.contarPorStatus(Status.ABERTA));
        model.addAttribute("osFinalizadas",
                ordemServicoService.contarPorStatus(Status.FINALIZADA));
        model.addAttribute("totalClientes",
                clienteRepository.count());
        model.addAttribute("totalEquipamentos",
                equipamentoRepository.count());
        return "dashboard";
    }
}