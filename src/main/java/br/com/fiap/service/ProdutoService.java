package br.com.fiap.service;

import java.util.List;
import br.com.fiap.model.Produto;


public interface ProdutoService {

	
    public Produto getProdutoById(int id);

    
    public List<Produto> getProdutoByName(String nome);

    
    public List<Produto> getAllProdutos();

    
    public boolean exists(String nome);

    
    public Produto save(Produto prod);

    
    public long contarPorPrecoMaior(double preco);

    
    public void deleteById(int id);
    
    
}
