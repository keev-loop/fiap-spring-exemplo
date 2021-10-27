package br.com.fiap;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.service.ProdutoService;
import br.com.fiap.service.ProdutoServiceImpl;


@RunWith(SpringRunner.class)
public class ProdutoServiceImplIntegrationTest {

	
	@TestConfiguration
    static class ProdutoServiceImplTestContextConfiguration {

        @Bean
        public ProdutoService produtoService() {
            return new ProdutoServiceImpl();
        }
    }
	

    @Autowired
    private ProdutoService produtoService;
    @MockBean
    private ProdutoRepository produtoRepository;

    
    @Before
    public void setUp() {
        List<Produto> result = new ArrayList<>();
        Produto prod = new Produto("canetinha", 5);
        result.add(prod);

        Mockito.when(produtoRepository.findByNome(prod.getNome()))
                .thenReturn(result);
    }
    

    @Test
    public void whenValidName_thenProductShouldBeFound() {
        String name = "canetinha";
        List<Produto> found = produtoService.getProdutoByName(name);

        assertThat(found.get(0).getNome())
                .isEqualTo(name);
    }
    
    
}
