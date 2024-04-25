package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webapp.escola_completo.Model.Aluno;
import webapp.escola_completo.Repository.AlunoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlunoController {
    boolean acessoInternoAdm = false;

    @Autowired
    private AlunoRepository alr;

@PostMapping("acesso-aluno")
public ModelAndView acessoAlunoLogin(@RequestParam String ra, @RequestParam String senha,
        RedirectAttributes attributes) {
    ModelAndView mv = new ModelAndView("redirect:/interna-aluno");// pagina interna de acesso
    try {
        Aluno aluno = alr.findByRa(ra);
        if (aluno != null) {
            boolean acessoSenha = senha.equals(aluno.getSenha());

            if (acessoSenha) {
                String mensagem = "Login realizado com sucesso!";
                System.out.println(mensagem);
                acessoInternoAdm = true;
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "verde");
            } else {
                String mensagem = "Senha incorreta!";
                System.out.println(mensagem);
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-aluno");
            }
        } else {
            String mensagem = "RA não encontrado!";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-aluno");
        }
    } catch (Exception e) {
        String mensagem = "Ocorreu um erro durante o login: " + e.getMessage();
        System.out.println(mensagem);
        attributes.addFlashAttribute("msg", mensagem);
        attributes.addFlashAttribute("classe", "vermelho");
        mv.setViewName("redirect:/login-aluno");
    }
    return mv;
}

    @PostMapping("logout-aluno")
    public ModelAndView AdmLogout(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/login-aluno");
        String mensagem = "Logout Efetuado com sucesso!";
        System.out.println(mensagem);
        attributes.addFlashAttribute("msg", mensagem);
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoAdm = false;
        return mv;
    }

    
    @GetMapping("/interna-aluno")
    public ModelAndView acessoPageInternaAdm(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna-aluno/internaAluno");
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-aluno");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }
}
