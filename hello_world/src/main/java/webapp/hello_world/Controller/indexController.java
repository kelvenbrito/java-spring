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
    public ModelAndView nomeIndex(@RequestParam("nome") String nome,@RequestParam("end") String end,@RequestParam("date") String date) {
        ModelAndView mv = new ModelAndView("index");

        String mensagem = "Seja bem-vinda(o)! !";
        mv.addObject("msg", mensagem);
        mv.addObject("nome", nome);
        mv.addObject("end", end);
        mv.addObject("date", date);


        return mv;
    }
}
