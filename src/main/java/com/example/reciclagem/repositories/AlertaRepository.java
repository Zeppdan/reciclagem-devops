package com.example.reciclagem.repositories;

import com.example.reciclagem.models.AlertaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<AlertaModel, Long> {

    List<AlertaModel> findByAtivoTrue();

    List<AlertaModel> findByTipoIgnoreCase(String tipo);
}
