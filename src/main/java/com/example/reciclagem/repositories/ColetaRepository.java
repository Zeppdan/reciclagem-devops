package com.example.reciclagem.repositories;

import com.example.reciclagem.models.ColetaModel;
import com.example.reciclagem.models.ResiduoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ColetaRepository extends JpaRepository<ColetaModel, Long> {

    List<ColetaModel> findByResiduo(ResiduoModel residuo);

    List<ColetaModel> findByDataColetaBetween(LocalDateTime inicio, LocalDateTime fim);
}
