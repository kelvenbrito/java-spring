package webapp.hello_world.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** @author Rolfi Luz - Senai * */
@Controller
public class indexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");

        String mensagem = "Seja bem-vinda(o)!";
        mv.addObject("msg", mensagem);

        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView abrirIndex(@RequestParam("nome") String nome,@RequestParam("email") String email,@RequestParam("tel") String tel) {
        ModelAndView mv = new ModelAndView("index");

        String mensagem = "Seja bem-vinda(o)! !";
        mv.addObject("msg", mensagem);
        mv.addObject("nome", nome);
        mv.addObject("email", email);
        mv.addObject("tel", tel);


        return mv;
    }
}
