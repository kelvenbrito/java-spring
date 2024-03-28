package webapp.escola_completo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class indexController {
    //classe para criação das rotas de navegação

    @GetMapping("/home")
    public String acessoHomePage() {
        return "index";
    }
    
    
    @GetMapping("")
    public String acessoHomePage2() {
        return "index";
    }

    @GetMapping("/login-adm")
    public String acessoLoginAdm() {
        return "login/login-adm";
    }
    @GetMapping("/cadatro-adm")
    public String acessoCadastroAdm() {
        return "cadastro/cadastro-adm";
    }
    
    
    
}
