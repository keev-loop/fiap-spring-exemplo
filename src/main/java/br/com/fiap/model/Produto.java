package br.com.fiap.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name = "produto", sequenceName = "SQ_PRODUTO", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
	private int codigo;
	@NotBlank(message = "Nome obrigatório!")
	@Size(max=50)
	private String nome;
	@Min(0)
	private double preco;
	private boolean novo;
	@Past
	private LocalDate dataFabricacao;
	@ManyToOne
	private Categoria categoria;

	public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }


    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String nome, double preco, boolean novo, LocalDate dataFabricacao) {
        this.nome = nome;
        this.preco = preco;
        this.novo = novo;
        this.dataFabricacao = dataFabricacao;
    }

    public Produto(String nome, double preco, boolean novo, LocalDate dataFabricacao, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.novo = novo;
        this.dataFabricacao = dataFabricacao;
        this.categoria = categoria;
    }
	
}
