package lt.bit.biblioteka.controllers;

import lt.bit.biblioteka.dao.KnygaDAO;
import lt.bit.biblioteka.data.Knyga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
@RequestMapping("knyga")
public class KnygaController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private KnygaDAO knygaDAO;

    @GetMapping
    public ModelAndView sarasas() {
        ModelAndView mav = new ModelAndView("knygos");
        mav.addObject("list", knygaDAO.findAll());
        return mav;
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav;
        if (id == null) {
            mav = new ModelAndView("knyga");
        } else {
            Optional<Knyga> oKnyga = knygaDAO.findById(id);
            if (oKnyga.isPresent()) {
                mav = new ModelAndView("knyga");
                mav.addObject("value", oKnyga.get());
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
            @RequestParam("author") String author,
            @RequestParam("year") String year,
            @RequestParam("type") String type
    ) {
        if (id == null) {
            Knyga k = new Knyga();
            k.setName(name);
            k.setAuthor(author);
            try {
                k.setYear(sdf.parse(year));
            } catch (ParseException ex) {
                k.setYear(null);
            }
            k.setType(type);
            System.out.println(k);
            knygaDAO.save(k);
        } else {
            Optional<Knyga> oKnyga = knygaDAO.findById(id);
            if (oKnyga.isPresent()) {
                Knyga k = new Knyga();
                k.setId(id);
                k.setName(name);
                k.setAuthor(author);
                try {
                    k.setYear(sdf.parse(year));
                } catch (ParseException ex) {
                    k.setYear(null);
                }
                k.setType(type);
                knygaDAO.save(k);
            }
        }
        return sarasas();
    }

    @GetMapping("delete")
    @Transactional
    public ModelAndView delete(@RequestParam("id") Integer id) {
        knygaDAO.deleteById(id);
        return sarasas();
    }
}
