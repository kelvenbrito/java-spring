package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Administrador;
import webapp.escola_completo.Repository.AdministradorRepository;
import webapp.escola_completo.Repository.VerificaCadastroAdmRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdministradorControlle {
    boolean acessoInternoAdm = false;

    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private VerificaCadastroAdmRepository vcar;

    @PostMapping("cadastrar-adm")
    public ModelAndView cadastroAdmBD(Administrador adm) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());
        ModelAndView mv = new ModelAndView("login/login-adm");
        if (verificaCpf) {

            ar.save(adm);
            String mensagem = "Cadastro realizado com sucesso!";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        }else{
            String mensagem = "Cadastro não realizado!";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("acesso-adm")
    public ModelAndView acessoAdmLogin(@RequestParam String cpf, @RequestParam String senha, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-adm");//pagina interna de acesso

    
        try {
            boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
            boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());
      
    
            if (acessoSenha && acessoCPF) {
                String mensagem = "Login realizado com sucesso!";
                System.out.println(mensagem);
                acessoInternoAdm = true;
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "verde");
           
            } else {
                String mensagem = "Login não efetuado!";
                System.out.println(mensagem);
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "vermelho");
                mv.setViewName("redirect:/login-adm");
     
            }
        } catch (Exception e) {
            String mensagem = "Ocorreu um erro durante o login: ";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("redirect:/login-adm");
        }
    
        return mv;
    }
   
   
    
   @GetMapping("/interna-adm")
    public ModelAndView acessoPageInternaAdm(RedirectAttributes attributes) {
        ModelAndView mv =  new ModelAndView("interna-adm/interna");
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
        } else{
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-adm");
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho"); 
        }

        return mv;
    }

    
    
   @GetMapping("cadastrar-aluno")
   public ModelAndView acessoPageInternaAdmAluno(RedirectAttributes attributes) {
       ModelAndView mv =  new ModelAndView("interna-adm/cadastro-aluno");
       if (acessoInternoAdm) {
           System.out.println("Acesso Permitido");
       } else{
           String mensagem = "Acesso não Permitido - faça Login";
           System.out.println(mensagem);
           mv.setViewName("redirect:/login-adm");
           mv.addObject("msg", mensagem);
           mv.addObject("classe", "vermelho"); 
       }

       return mv;
   }

   @GetMapping("cadastrar-professor")
   public ModelAndView acessoPageInternaAdmProfessor(RedirectAttributes attributes) {
       ModelAndView mv =  new ModelAndView("interna-adm/cadastro-professor");
       if (acessoInternoAdm) {
           System.out.println("Acesso Permitido");
       } else{
           String mensagem = "Acesso não Permitido - faça Login";
           System.out.println(mensagem);
           mv.setViewName("redirect:/login-adm");
           mv.addObject("msg", mensagem);
           mv.addObject("classe", "vermelho"); 
       }

       return mv;
   }
    // @GetMapping("/interna-adm")
    // public String acessoPageInternaAdm() {
    //     String acesso = "";
    //     ModelAndView mv = new ModelAndView();
    //     if (acessoInternoAdm) {
    //         System.out.println("Acesso Permitido");
    //         acesso = "interna/interna-adm";

    //     } else{
    //         String mensagem = "Acesso não Permitido - faça Login";
    //         System.out.println(mensagem);
    //         acesso = "login/login-adm";
    //         mv.addObject("msg", mensagem);
    //         mv.addObject("classe", "vermelho"); 
    //     }

    //     return acesso;
    // }
    

}
