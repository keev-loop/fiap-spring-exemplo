package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	
	List<Produto> findByNome(String prod);
	List<Produto> findByNomeAndPreco(String prod, double preco);
	
	List<Produto> findByPrecoGreaterThan(double preco);
	List<Produto> findByPrecoOrderByNomeDesc(double preco);
	List<Produto> findByNomeIgnoreCaseOrderByPrecoAsc(String nome);
	
	@Query("from Produto where nome like ?1 and preco = ?2")
	List<Produto> buscarPorNome(String nome, double preco);
	
	@Query("select count(p) from Produto p where preco > ?1")
	public long contarPorPrecoMaior(double preco);
	
	
}
