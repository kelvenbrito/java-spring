package webapp.escola_completo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;


@Controller
public class indexController {
    //classe para criação das rotas de navegação

    @GetMapping("/home")
    public ModelAndView acessoHomePage() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @GetMapping("")
    public String acessoHomePage2() {
        return "index";
    }

    //Acesso ADM
    @GetMapping("/login-adm")
    public String acessoLoginAdm() {
        return "login/login-adm";
    }
    @GetMapping("/cadastro-adm")
    public String acessoCadastroAdm() {
        return "cadastro/cadastro-adm";
    }

    @GetMapping("/cadastro-professor")
    public String acessoCadastroProfessor() {
        return "interna-adm/cadastro-professor";
    }
    
    
    
    @GetMapping("/cadastro-aluno")
    public String acessoCadastroaluno() {
        return "interna-adm/cadastro-aluno";
    }

      
    @GetMapping("/cadastro-materia")
    public String acessoCadastromateria() {
        return "interna-adm/cadastro-materia";
    }
    

    //Acesso Aluno
    @GetMapping("/login-aluno")
    public String acessoLoginAluno() {
        return "login/login-aluno";
    }
    
         //Acesso Professor
    @GetMapping("/login-professor")
    public String acessoLoginProfessor() {
        return "login/login-professor";
    }
    

    
}