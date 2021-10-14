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
import br.com.fiap.repository.ProdutoRepository;


@RestController
@RequestMapping("/produto")
public class ProdutoResource {
	
	
	@Autowired
	private ProdutoRepository _produtoRepo;
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Produto> listar() {
		return _produtoRepo.findAll();
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{codigo}")
	public Produto buscar(@PathVariable int codigo) {
		return _produtoRepo.findById(codigo).get();
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Produto cadastrar(@RequestBody Produto produto) {
		return _produtoRepo.save(produto);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("{codigo}")
	public Produto atualizar(@PathVariable int codigo, @RequestBody Produto produto) {
		produto.setCodigo(codigo);
		return _produtoRepo.save(produto);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		_produtoRepo.deleteById(codigo);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("pesquisa")
	public List<Produto> buscar(@RequestParam(required=false) String nome, @RequestParam(defaultValue = "false") boolean novo) {
		return nome != null ? 
				_produtoRepo.findByNomeAndNovo(nome, novo) 
				: _produtoRepo.findByNovo(novo);
	}

}
