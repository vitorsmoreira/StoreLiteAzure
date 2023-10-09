package br.com.fiap.api.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import br.com.fiap.api.controllers.MercadoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class Mercado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String telefone;

  
    private LocalDateTime expediente_inicio;


    private LocalDateTime expediente_termino;

    @NotNull
    private String cnpj;
    
    private double preco_minimo;

    public EntityModel<Mercado> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(MercadoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MercadoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(MercadoController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
