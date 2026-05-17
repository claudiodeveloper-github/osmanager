package com.osmanager.service;

import com.osmanager.entity.OrdemServico;
import com.osmanager.entity.Status;
import com.osmanager.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository repository;

    public List<OrdemServico> listarTodas() {
        return repository.findAll();
    }

    public OrdemServico abrirOS(OrdemServico os) {

        os.setDataEntrada(LocalDate.now());

        if (os.getStatus() == null) {
            os.setStatus(Status.ABERTA);
        }

        String numero = "OS-"
                + LocalDate.now().toString().replace("-", "")
                + "-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 4)
                .toUpperCase();

        os.setNumeroOS(numero);

        return repository.save(os);
    }

    public OrdemServico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("OS não encontrada: " + id));
    }

    public OrdemServico atualizarStatus(Long id, Status status) {

        OrdemServico os = buscarPorId(id);

        os.setStatus(status);

        if (status == Status.FINALIZADA) {
            os.setDataSaida(LocalDate.now());
        }

        return repository.save(os);
    }

    public long contarPorStatus(Status status) {
        return repository.countByStatus(status);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}