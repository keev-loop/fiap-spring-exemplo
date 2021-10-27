package br.com.fiap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;


@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

	
    @Autowired
    private ProdutoRepository _produtoRepo;

    
    @Override
    public Produto getProdutoById(int id) {
        return _produtoRepo.findById(id).orElse(null);
    }

    
    @Override
    public List<Produto> getProdutoByName(String nome) {
        return _produtoRepo.findByNome(nome);
    }

    
    @Override
    public List<Produto> getAllProdutos() {
        return _produtoRepo.findAll();
    }

    
    @Override
    public boolean exists(String nome) {
        if (_produtoRepo.findByNome(nome) != null) {
            return true;
        }
        return false;
    }

    
    @Override
    public Produto save(Produto prod) {
        return _produtoRepo.save(prod);
    }

    
    @Override
    public long contarPorPrecoMaior(double preco) {
        return _produtoRepo.contarPorPrecoMaior(preco);
    }

    
    @Override
    public void deleteById(int id) {
    	_produtoRepo.deleteById(id);
    }
    
    
}
