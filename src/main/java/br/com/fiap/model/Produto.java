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

	
}
