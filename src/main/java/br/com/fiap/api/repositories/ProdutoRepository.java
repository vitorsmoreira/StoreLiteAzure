package br.com.fiap.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.api.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    Page<Produto> findByNomeContaining(String busca, Pageable pageable);

}