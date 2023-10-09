package br.com.fiap.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;



import br.com.fiap.api.controllers.ProdutoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String fabricante;

    @NotNull
    private double peso;

    private double volume;

    @NotNull
    private double preco;

    private double frete;


    private int quantidade;

   
    private LocalDateTime vencimento;

    @ManyToOne
    private Mercado mercado;

    public EntityModel<Produto> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(ProdutoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ProdutoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ProdutoController.class).index(null, Pageable.unpaged())).withRel("all")
            
        );
    }

}