package br.com.fiap;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.com.fiap.controller.ProdutoRestController;
import br.com.fiap.model.Produto;
import br.com.fiap.service.ProdutoServiceImpl;

import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProdutoRestController.class)
public class ProdutoRestControllerIntegrationTest {

	
	@Autowired
    private MockMvc mvc;
    @MockBean
    private ProdutoServiceImpl service;

    
    @Before
    public void setUp() throws Exception {
    }
    

    @Test
    public void whenPostEmployee_thenCreateEmployee() throws Exception {
        Gson gson = new Gson();

        Produto alex = new Produto("alex",4);
        given(service.save(Mockito.any())).willReturn(alex);

        mvc.perform(post("/api/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(alex)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("alex")));
        verify(service, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(service);
    }
    

    @Test
    public void givenProdutos_whenGetProdutos_thenReturnJsonArray() throws Exception {
        Gson gson = new Gson();

        Produto alex = new Produto("alex",2);
        Produto john = new Produto("john",5);
        Produto bob = new Produto("bob",4);

        List<Produto> allEmployees = Arrays.asList(alex, john, bob);

        given(service.getAllProdutos()).willReturn(allEmployees);

        mvc.perform(get("/api/listar").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nome", is(alex.getNome())))
                .andExpect(jsonPath("$[1].nome", is(john.getNome())))
                .andExpect(jsonPath("$[2].nome", is(bob.getNome())));
        verify(service, VerificationModeFactory.times(1)).getAllProdutos();
        reset(service);
    }
    
    
}
