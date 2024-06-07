package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webapp.crud_escola.Model.Usuario;
import webapp.crud_escola.Repository.UsuarioRepository;
import webapp.crud_escola.Repository.VerificaCadastroUsuarioRepository;

@Controller
public class UsuarioController {
    boolean acessoInternoUsuario = false;
    @Autowired
    UsuarioRepository ur;
    @Autowired
    private VerificaCadastroUsuarioRepository vcar;

    @PostMapping("/cadastrar-aluno")
    public ModelAndView cadastroAlunoBD(Usuario usuario, RedirectAttributes attributes) {

        boolean verificaCpf = vcar.existsById(usuario.getCpf());

        ModelAndView mv = new ModelAndView("redirect:/interna-adm");

        if (verificaCpf) {
            ur.save(usuario);
            String mensagem = "Cadastro Realizado com sucesso";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        } else {
            String mensagem = "Cadastro Não Realizado";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("acesso-usuario")
    public ModelAndView acessoUsuarioLogin(@RequestParam String cpf,
            @RequestParam String senha,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/interna-usuario");// página interna de acesso
        try {

            boolean acessoCPF = ur.existsById(cpf);
            boolean acessoSenha = senha.equals(ur.findByCpf(cpf).getSenha());

            if (acessoCPF && acessoSenha) {
                String mensagem = "Login Realizado com sucesso";
                System.out.println(mensagem);
                acessoInternoUsuario = true;
                mv.addObject("msg", mensagem);
                mv.addObject("classe", "verde");
            } else {
                String mensagem = "Login Não Efetuado";
                System.out.println(mensagem);
                attributes.addFlashAttribute("msg", mensagem);
                attributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-usuario");
            }

        } catch (Exception e) {
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-usuario");
        }
        return mv;
    }

    @GetMapping("/interna-usuario")
    public ModelAndView acessoPageInternaProf(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("interna/interna-usuario");
        if (acessoInternoUsuario) {
            System.out.println("Acesso Permitido");
        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-usuario");
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
        }

        return mv;
    }

    @PostMapping("logout-usuario")
    public ModelAndView logoutAdm(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/login-usuario");
        attributes.addFlashAttribute("msg", "Logout Efetuado");
        attributes.addFlashAttribute("classe", "verde");
        acessoInternoUsuario = false;
        return mv;
    }
}
