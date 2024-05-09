package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Professor;
import webapp.escola_completo.Repository.ProfessorRepository;

@Controller
public class ProfessorController {
    boolean acessoInternoProf = false;

    @Autowired
    private ProfessorRepository prof;

    @PostMapping("acesso-professor")
    public ModelAndView acessoProfessorLogin(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-professor");// pagina interna de acesso
        try {
            Professor aluno = prof.findByCpf(cpf);
            if (aluno != null) {
                boolean acessoSenha = senha.equals(aluno.getSenha());
                if (acessoSenha) {
                    String mensagem = "Login realizado com sucesso!";
                    System.out.println(mensagem);
                    acessoInternoProf = true;
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
                String mensagem = "CPF não encontrado!";
                System.out.println(mensagem);
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-professor");
            }
        } catch (Exception e) {
            String mensagem = "Ocorreu um erro durante o login: " + e.getMessage();
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-professor");
        }
        return mv;
    }

    @PostMapping("logout-professor")
    public ModelAndView AdmLogout(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/login-professor");
        String mensagem = "Logout Efetuado com sucesso!";
        System.out.println(mensagem);
        attributes.addFlashAttribute("msg", mensagem);
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoProf = false;
        return mv;
    }

    @GetMapping("/interna-professor")
    public ModelAndView acessoPageInternaAdm(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna-professor/internaProfessor");
        if (acessoInternoProf) {
            System.out.println("Acesso Permitido");
        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-professor");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }
}