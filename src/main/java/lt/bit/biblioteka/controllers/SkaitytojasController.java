package lt.bit.biblioteka.controllers;

import lt.bit.biblioteka.dao.SkaitytojasDAO;
import lt.bit.biblioteka.data.Skaitytojas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("skaitytojas")
public class SkaitytojasController {

    @Autowired
    private SkaitytojasDAO skaitytojasDAO;

    @GetMapping
    public ModelAndView sarasas() {
        ModelAndView mav = new ModelAndView("skaitytojai");
        mav.addObject("list", skaitytojasDAO.findAll());
        return mav;
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav;
        if (id == null) {
            mav = new ModelAndView("skaitytojas");
        } else {
            Optional<Skaitytojas> oSkaitytojas = skaitytojasDAO.findById(id);
            if (oSkaitytojas.isPresent()) {
                mav = new ModelAndView("skaitytojas");
                mav.addObject("value", oSkaitytojas.get());
            } else {
                mav = sarasas();
            }
        }
        return mav;
    }

    @PostMapping("save")
    @Transactional
    public ModelAndView save(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("age") Integer age
    ) {
        if (id == null) {
            Skaitytojas sk = new Skaitytojas();
            sk.setName(name);
            sk.setAge(age);
            skaitytojasDAO.save(sk);
        } else {
            Optional<Skaitytojas> oSkaitytojas = skaitytojasDAO.findById(id);
            if (oSkaitytojas.isPresent()) {
                Skaitytojas sk = new Skaitytojas();
                sk.setId(id);
                sk.setName(name);
                sk.setAge(age);
                skaitytojasDAO.save(sk);
            }
        }
        return sarasas();
    }

    @GetMapping("delete")
    @Transactional
    public ModelAndView delete(@RequestParam("id") Integer id) {
        skaitytojasDAO.deleteById(id);
        return sarasas();
    }
}
