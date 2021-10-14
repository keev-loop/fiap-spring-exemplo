package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@SequenceGenerator(name = "categoria", sequenceName = "SQ_CATEGORIA", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria")
	private int codigo;
	private String nome;
	

}
