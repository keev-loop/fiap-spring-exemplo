package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Produto;
import br.com.fiap.service.ProdutoServiceImpl;


@RestController
@RequestMapping("api")
public class ProdutoRestController {
	
	
    @Autowired
    private ProdutoServiceImpl _produtoServImp;

    
    @GetMapping("listar")
    public List<Produto> getAllProducts() {
        return _produtoServImp.getAllProdutos();
    }
    

    @GetMapping("{codigo}")
    public Produto buscar(@PathVariable int codigo) {
        return _produtoServImp.getProdutoById(codigo);
    }
    

    @GetMapping("pesquisar")
    public List<Produto> pesquisar(@RequestParam String nome) {
        return _produtoServImp.getProdutoByName(nome);
    }
    

    @GetMapping("contar")
    public long contar(@RequestParam double preco) {
        return _produtoServImp.contarPorPrecoMaior(preco);
    }
    

    @PostMapping("cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@RequestBody Produto produto) {
        return _produtoServImp.save(produto);
    }
    

    @PutMapping("{id}")
    public Produto atualizar(@RequestBody Produto produto, @PathVariable int id) {
        produto.setCodigo(id);
        return _produtoServImp.save(produto);
    }
    

    @DeleteMapping("{codigo}")
    public void remover(@PathVariable int codigo) {
    	_produtoServImp.deleteById(codigo);
    }
    
    
}
