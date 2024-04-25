package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.escola_completo.Model.Administrador;
import webapp.escola_completo.Model.Aluno;
import webapp.escola_completo.Model.Professor;
import webapp.escola_completo.Repository.AdministradorRepository;
import webapp.escola_completo.Repository.AlunoRepository;
import webapp.escola_completo.Repository.ProfessorRepository;
import webapp.escola_completo.Repository.VerificaCadastroAdmRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdministradorControlle {
    boolean acessoInternoAdm = false;

    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private VerificaCadastroAdmRepository vcar;

    @Autowired
    private ProfessorRepository pr;

    @Autowired
    private AlunoRepository alr;



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
        } else {
            String mensagem = "Cadastro não realizado!";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("acesso-adm")
    public ModelAndView acessoAdmLogin(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-adm");// pagina interna de acesso

        try {
            // boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
            boolean acessoCPF = ar.existsById(cpf);
            boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());

            if (acessoSenha && acessoCPF) {
                String mensagem = "Login realizado com sucesso!";
                System.out.println(mensagem);
                acessoInternoAdm = true;
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "verde");

            } else {
                String mensagem = "Login não efetuado!";
                System.out.println(mensagem);
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-adm");

            }
        } catch (Exception e) {
            String mensagem = "Ocorreu um erro durante o login: ";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-adm");
        }

        return mv;
    }

    @PostMapping("logout-adm")
    public ModelAndView AdmLogout(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/login-adm");
        String mensagem = "Logout Efetuado com sucesso!";
        System.out.println(mensagem);
        attributes.addFlashAttribute("msg", mensagem);
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoAdm = false;
        return mv;
    }

    @GetMapping("/interna-adm")
    public ModelAndView acessoPageInternaAdm(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna-adm/interna");
        if (acessoInternoAdm) {
            System.out.println("Acesso Permitido");
        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-adm");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("cadastrar-aluno")
    public ModelAndView acessoPageInternaAdmAluno(Aluno aluno, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna-adm/interna");
        if (acessoInternoAdm) {
            alr.save(aluno);
            String mensagem = "Cadastro realizado com sucesso!";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "Falha no cadastro";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("cadastrar-professor")
    public ModelAndView acessoPageInternaAdmProfessor(Professor prof, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna-adm/interna");
        if (acessoInternoAdm) {
            pr.save(prof);
            String mensagem = "Cadastro realizado com sucesso!";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "verde");
        } else {
            String mensagem = "Falha no cadastro";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }


    @PostMapping("listar-alunos")
public ModelAndView listarAlunos(RedirectAttributes attributes) {
    ModelAndView mv = new ModelAndView("interna-adm/lista-aluno");
    Iterable<Aluno> alunos = alr.findAll(); // Busca todos os alunos do banco de dados
    mv.addObject("alunos", alunos); // Adiciona a lista de alunos ao modelo
    return mv;
}

@PostMapping("listar-professores")
public ModelAndView listarProfessores(RedirectAttributes attributes) {
    ModelAndView mv = new ModelAndView("interna-adm/lista-professor");
    Iterable<Professor> professores = pr.findAll(); // Busca todos os alunos do banco de dados
    mv.addObject("professores", professores); // Adiciona a lista de professores ao modelo
    return mv;
}
    
}
