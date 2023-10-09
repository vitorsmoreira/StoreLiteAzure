package br.com.fiap.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.api.models.Mercado;

public interface MercadoRepository extends JpaRepository<Mercado, Long> {
    
    Page<Mercado> findByNomeContaining(String busca, Pageable pageable);

}

