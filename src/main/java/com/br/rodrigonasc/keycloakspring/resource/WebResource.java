package com.br.rodrigonasc.keycloakspring.resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebResource {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/security")
    public String security(Model model){
        model.addAttribute("message","Olá! você está acessando o ambiente seguro do sistema.");
        return "security";
    }
}
