package lt.bit.biblioteka.controllers;

import lt.bit.biblioteka.dao.KnygaDAO;
import lt.bit.biblioteka.data.Knyga;
import lt.bit.biblioteka.data.Registravimas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import javax.ws.rs.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
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
        ArrayList<Knyga> pilnasSarasas = (ArrayList<Knyga>)knygaDAO.findAll();
        HashSet autoriai = new HashSet<>();
        HashSet tipai = new HashSet<>();
        for (int i=0;i< pilnasSarasas.size();i++){
            autoriai.add(pilnasSarasas.get(i).getAuthor().toUpperCase());
            tipai.add(pilnasSarasas.get(i).getType().toLowerCase());
        }
        mav.addObject("autoriai", autoriai);
        mav.addObject("tipai", tipai);
        mav.addObject("list", pilnasSarasas);
        return mav;
    }

    @GetMapping("rikiavimasA")
    public ModelAndView sarasoRikiavimasA() {
        ModelAndView mav = new ModelAndView("knygos");
        ArrayList<Knyga> pilnasSarasas = (ArrayList<Knyga>)knygaDAO.findAll();
        HashSet autoriai = new HashSet<>();
        HashSet tipai = new HashSet<>();
        for (int i=0;i< pilnasSarasas.size();i++){
            autoriai.add(pilnasSarasas.get(i).getAuthor().toUpperCase());
            tipai.add(pilnasSarasas.get(i).getType().toLowerCase());
        }
        mav.addObject("autoriai", autoriai);
        mav.addObject("tipai", tipai);

        SortOrder sortOrder = SortOrder.ASCENDING;
        System.out.println("Rikiavimas pagal pavadinimą "+ sortOrder.name()+" tvarka.");
        pilnasSarasas.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        mav.addObject("list", pilnasSarasas);
        return mav;
    }

    @GetMapping("rikiavimasZ")
    public ModelAndView sarasoRikiavimasZ() {
        ModelAndView mav = new ModelAndView("knygos");
        ArrayList<Knyga> pilnasSarasas = (ArrayList<Knyga>)knygaDAO.findAll();
        HashSet autoriai = new HashSet<>();
        HashSet tipai = new HashSet<>();
        for (int i=0;i< pilnasSarasas.size();i++){
            autoriai.add(pilnasSarasas.get(i).getAuthor().toUpperCase());
            tipai.add(pilnasSarasas.get(i).getType().toLowerCase());
        }
        mav.addObject("autoriai", autoriai);
        mav.addObject("tipai", tipai);

        SortOrder sortOrder = SortOrder.DESCENDING;
        System.out.println("Rikiavimas pagal pavadinimą "+ sortOrder.name()+" tvarka.");
        pilnasSarasas.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
        mav.addObject("list", pilnasSarasas);
        return mav;
    }

    @GetMapping("pagalAutoriu")
    public ModelAndView pagalAutoriu(
            @RequestParam("autorius_name") String autorius
    ) {
        ModelAndView mav = new ModelAndView("knygos");
        ArrayList<Knyga> pilnasSarasas = (ArrayList<Knyga>)knygaDAO.findAll();
        ArrayList<Knyga> knygosPagalAutoriu = new ArrayList<>();
        String autoriusUpperCase = autorius.toUpperCase();
        HashSet autoriai = new HashSet<>();
        HashSet tipai = new HashSet<>();
        for (int i=0;i< pilnasSarasas.size();i++){
            autoriai.add(pilnasSarasas.get(i).getAuthor().toUpperCase());
            tipai.add(pilnasSarasas.get(i).getType().toLowerCase());
            if (autoriusUpperCase.equals(pilnasSarasas.get(i).getAuthor().toUpperCase())){
                knygosPagalAutoriu.add(pilnasSarasas.get(i));
            }
        }
        mav.addObject("autoriai", autoriai);
        mav.addObject("tipai", tipai);
        mav.addObject("list", knygosPagalAutoriu);
        return mav;
    }

    @GetMapping("pagalTipa")
    public ModelAndView pagalTipa(
            @RequestParam("tipas_name") String tipas
    ) {
        ModelAndView mav = new ModelAndView("knygos");
        ArrayList<Knyga> pilnasSarasas = (ArrayList<Knyga>)knygaDAO.findAll();
        ArrayList<Knyga> knygosPagalTipa = new ArrayList<>();
        String tipasUpperCase = tipas.toUpperCase();
        HashSet autoriai = new HashSet<>();
        HashSet tipai = new HashSet<>();
        for (int i=0;i< pilnasSarasas.size();i++){
            autoriai.add(pilnasSarasas.get(i).getAuthor().toUpperCase());
            tipai.add(pilnasSarasas.get(i).getType().toLowerCase());
            if (tipasUpperCase.equals(pilnasSarasas.get(i).getType().toUpperCase())){
                knygosPagalTipa.add(pilnasSarasas.get(i));
            }
        }
        mav.addObject("autoriai", autoriai);
        mav.addObject("tipai", tipai);
        mav.addObject("list", knygosPagalTipa);
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
