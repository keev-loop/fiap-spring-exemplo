package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	
    @GetMapping("/home")
    public String exibirHome() {
        return "pagina-home";
    }
    
    
}
