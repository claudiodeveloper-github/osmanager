package com.osmanager.repository;

import com.osmanager.entity.OrdemServico;
import com.osmanager.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    long countByStatus(Status status);

    List<OrdemServico> findByStatus(Status status);

    Optional<OrdemServico> findByNumeroOS(String numeroOS);
}