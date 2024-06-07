package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.crud_escola.Model.Professor;
import webapp.crud_escola.Repository.ProfessoresRepository;
import webapp.crud_escola.Repository.VerificaCadastroProfRepository;

@Controller
public class ProfessorController {
    boolean acessoInternoProf = false;

    @Autowired
    ProfessoresRepository pr;
    @Autowired
    private VerificaCadastroProfRepository vcar;

    @PostMapping("/cadastrar-professor")
      public ModelAndView cadastroProfBD(Professor prof, RedirectAttributes attributes) {

        boolean verificaCpf = vcar.existsById(prof.getCpf());

        ModelAndView mv = new ModelAndView("redirect:/interna-adm");

        if (verificaCpf) {
            pr.save(prof);
            String mensagem = "Cadastro Realizado com sucesso";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "verde");
        } else {
            String mensagem = "Cadastro Não Realizado";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/cadastrar-professor");
        }

        return mv;
    }

    @PostMapping("acesso-prof")
    public ModelAndView acessoProfLogin(@RequestParam String cpf,
            @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-prof");// página interna de acesso
        try {
            boolean acessoCPF = pr.existsById(cpf);
            boolean acessoSenha = senha.equals(pr.findByCpf(cpf).getSenha());

            if (acessoCPF && acessoSenha) {
                String mensagem = "Login Realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoProf = true;
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "verde");
            } else {
                String mensagem = "Login Não Efetuado";
                System.out.println(mensagem);
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-professor");
            }
            
        } catch (Exception e) {
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-professor");
        }
        return mv;
    }

    @GetMapping("/interna-prof")
    public ModelAndView acessoPageInternaProf(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna/interna-prof");
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


    //  @GetMapping("/listar-alunos")
    // public String listarAlunos(Model model, @RequestParam("cpf") String cpfProfessor) {
    //     Professor professor = pr.findByCpf(cpfProfessor);
    //     if (professor == null) {
    //         // Tratar o caso em que o professor não foi encontrado
    //         return "error"; // ou uma página de erro específica
    //     }

    //     List<Usuario> alunos = ur.findByDisciplina1OrDisciplina2(professor.getDisciplina(), professor.getDisciplina());
    //     model.addAttribute("alunos", alunos);
    //     return "listar-alunos";
    // }

    @PostMapping("logout-prof")
    public ModelAndView logoutProf(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/login-professor");
        attributes.addFlashAttribute("msg", "Logout Efetuado");
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoProf = false;
        return mv;
    }

}


