package br.com.fiap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProdutoRepositoryIntegrationTest {

	
	@Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProdutoRepository _produtoRepo;

    
    @Test
    public void whenFindByName_thenReturnProduct() {
        // given
        Produto lousa = new Produto("lousa escolar 2m", 2.90);
        entityManager.persist(lousa);
        entityManager.flush();

        // when
        Produto found = _produtoRepo.findByNome(lousa.getNome()).get(0);

        // then
        assertThat(found.getNome())
                .isEqualTo(lousa.getNome());
        assertThat(found.getPreco())
                .isEqualTo(lousa.getPreco());
    }
	
	
}
