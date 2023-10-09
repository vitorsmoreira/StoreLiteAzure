package br.com.fiap.api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.api.models.Mercado;
import br.com.fiap.api.models.Produto;
import br.com.fiap.api.models.Usuario;
import br.com.fiap.api.repositories.MercadoRepository;
import br.com.fiap.api.repositories.ProdutoRepository;
import br.com.fiap.api.repositories.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    MercadoRepository mercadoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        mercadoRepository.saveAll(List.of(
            Mercado.builder().nome("Danaceno").telefone("(11) 97550-2172").expediente_inicio(LocalDateTime.now()).expediente_termino(LocalDateTime.now()).cnpj("26.833.533/0001-48").preco_minimo(20).build()
            
            ));


        produtoRepository.saveAll(List.of(
            Produto.builder().nome("Sprite").fabricante("Cocacola").peso(1).volume(4).preco(10).frete(5).quantidade(1).vencimento(LocalDateTime.now()).build()
    
            ));

        usuarioRepository.save(Usuario.builder()
            .nome("teste")
            .email("teste@gmail.com")
            .senha("$2a$10$6rEhuixx0qI7bTdNePXd5OhxmSVtVx1G2QyrcoWl8txZBnwJPuTh.")
            .build()
        );

    
       
    }
    
}

