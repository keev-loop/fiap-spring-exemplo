package br.com.fiap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	
    @Autowired
    private ProdutoRepository _produtoRepo;
    @Autowired
    private CategoriaRepository _categoriaRepo;

    
    @GetMapping("cadastrar")
    public String abrirFormulario(Produto produto, Model model){
        model.addAttribute("categorias", _categoriaRepo.findAll());
        return "produto/form";
    }

    
    @PostMapping("cadastrar")
    public String processarForm(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            return "produto/form";
        }

        redirectAttributes.addFlashAttribute("msg","Cadastrado!");
        _produtoRepo.save(produto);
        return "redirect:/produto/listar";
    }
    

    @PostMapping("excluir")
    public String excluirProduto(int codigo, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg","Produto excluido!");
        _produtoRepo.deleteById(codigo);
        return "redirect:/produto/listar";
    }
    

    @GetMapping("listar")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", _produtoRepo.findAll());
        return "produto/lista";
    }
    

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") int codigo, Model model){
        model.addAttribute("produto",_produtoRepo.findById(codigo));
        return "produto/form";
    }
    
	
}
