package br.com.fiap.pet_tech.pet_tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //ResponseEntity é a resposta para o nosso client , o spring serializa a resposta atraves desse objeto
    @GetMapping
    public ResponseEntity<Collection<Produto>> findAll() {
        var produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable UUID id) {
        var produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        produto = produtoService.save(produto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable UUID id,
                                          @RequestBody Produto produto) {
        produto = produtoService.update(id,produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable UUID id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
