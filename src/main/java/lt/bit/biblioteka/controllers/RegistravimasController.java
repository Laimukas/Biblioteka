package lt.bit.biblioteka.controllers;

import lt.bit.biblioteka.dao.KnygaDAO;
import lt.bit.biblioteka.dao.RegistravimasDAO;
import lt.bit.biblioteka.dao.SkaitytojasDAO;
import lt.bit.biblioteka.data.DataClass;
import lt.bit.biblioteka.data.Registravimas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
@RequestMapping("registravimas")
public class RegistravimasController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private KnygaDAO knygaDAO;
    @Autowired
    private SkaitytojasDAO skaitytojasDAO;
    @Autowired
    private RegistravimasDAO registravimasDAO;

    @GetMapping
    public ModelAndView registravimas() {
        ModelAndView mav = new ModelAndView("registravimas");
        mav.addObject("knygos", knygaDAO.findAll());
        mav.addObject("skaitytojai", skaitytojasDAO.findAll());
        return mav;
    }

    @GetMapping("all")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("registracijos");
        mav.addObject("list", registravimasDAO.findAll());
        return mav;
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav;
        if (id == null) {
            mav = new ModelAndView("registravimasEdit");
        } else {
            Optional<Registravimas> oRegistravimas = registravimasDAO.findById(id);
            if (oRegistravimas.isPresent()) {
                mav = new ModelAndView("registravimasEdit");
                mav.addObject("value", oRegistravimas.get());
            } else {
                mav = list();
            }
        }
        return mav;
    }

    @PostMapping("save")
    @Transactional
    public ModelAndView save(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("skaitytojas_id") Integer skId,
            @RequestParam("knyga_id") Integer knId
//            @RequestParam("start") String start,
//            @RequestParam(value = "finish", required = false) String finish,
//            @RequestParam(value = "returned", required = false) Integer returned
    )throws IOException {
        DataClass data = new DataClass();
        if (id == null) {
            Registravimas r = new Registravimas();
            r.setKnygId(knId);
            r.setSkaitId(skId);

            try {
                r.setStart(sdf.parse(data.getDataNow()));
            } catch (ParseException ex) {
                r.setStart(null);
            }
            r.setReturned(0);
//            try {
//                r.setFinish(sdf.parse(finish));
//            } catch (ParseException ex) {
//                r.setFinish(null);
//            }
//            try {
//                r.setReturned(returned);
//            } catch (NullPointerException ex) {
//                r.setReturned(0);
//            }
            System.out.println(r);
            registravimasDAO.save(r);
        } else {
            Optional<Registravimas> oRegistravimas = registravimasDAO.findById(id);
            if (oRegistravimas.isPresent()) {
                Registravimas r = new Registravimas();
                r.setKnygId(knId);
                r.setSkaitId(skId);
                try {
                    r.setStart(sdf.parse(data.getDataNow()));
                } catch (ParseException ex) {
                    r.setStart(null);
                }
                r.setReturned(0);
//                try {
//                    r.setFinish(sdf.parse(finish));
//                } catch (ParseException ex) {
//                    r.setFinish(null);
//                }
//                try {
//                    r.setReturned(returned);
//                } catch (NullPointerException ex) {
//                    r.setReturned(0);
//                }
                registravimasDAO.save(r);
            }
        }
        return list();
    }


}
