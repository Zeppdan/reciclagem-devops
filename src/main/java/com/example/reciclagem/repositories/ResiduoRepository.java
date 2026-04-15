package com.example.reciclagem.repositories;

import com.example.reciclagem.models.ResiduoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResiduoRepository extends JpaRepository<ResiduoModel, Long> {

    boolean existsByTipoIgnoreCase(String tipo);
}
