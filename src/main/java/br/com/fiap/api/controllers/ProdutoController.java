package br.com.fiap.api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api.exceptions.RestNotFoundException;
import br.com.fiap.api.models.Produto;
import br.com.fiap.api.repositories.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/produtos")
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    ProdutoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "produto")

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Produto> produtos = (busca == null)?
            repository.findAll(pageable):
            repository.findByNomeContaining(busca, pageable);
        
        return assembler.toModel(produtos.map(Produto::toEntityModel));
    }


    
    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Campos enviados são inválidos")
        })
    public ResponseEntity<Object> create(@RequestBody @Valid Produto produto){
        log.info("Cadastrando produto" + produto);
        repository.save(produto);
        return ResponseEntity
        .created(produto.toEntityModel().getRequiredLink("self").toUri())
        .body(produto.toEntityModel());
    }



    @GetMapping("{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id) {
        log.info("Detalhando produto" + id);
        var produto = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("produto não encontrado"));

        return ResponseEntity.ok(produto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id) {
        log.info("Apagando produto" + id);
        var produto = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, produto não encontrado"));

        repository.delete(produto);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto produto){
        log.info("Atualizando produto" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, produto não encontrado"));

        produto.setId(id);
        repository.save(produto);

        return ResponseEntity.ok(produto);
        
    }

   
}