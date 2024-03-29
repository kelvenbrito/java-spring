package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView acessoAdmLogin(@RequestParam String cpf, @RequestParam String senha) {
        ModelAndView mv = new ModelAndView("interna/interna-adm");//pagina interna de acesso
    
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
            }
        } catch (Exception e) {
            // Aqui você pode lidar com a exceção como desejar, por exemplo, registrando-a ou retornando uma mensagem de erro para o usuário.
            String mensagem = "Ocorreu um erro durante o login: ";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
    
        return mv;
    }
   
    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        String acesso = "";
        if (acessoInternoAdm) {
            acesso = "interna/interna-adm";
        }else{
            ModelAndView mv = new ModelAndView();
            acesso = "login/login-adm";
            String mensagem = "Acesso não permitido - faça login ";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        return acesso;
    }
    

}
