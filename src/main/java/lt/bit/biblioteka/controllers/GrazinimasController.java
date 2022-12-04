package lt.bit.biblioteka.controllers;

import lt.bit.biblioteka.dao.KnygaDAO;
import lt.bit.biblioteka.dao.RegistravimasDAO;
import lt.bit.biblioteka.dao.SkaitytojasDAO;
import lt.bit.biblioteka.data.DataClass;
import lt.bit.biblioteka.data.Knyga;
import lt.bit.biblioteka.data.Registravimas;
import lt.bit.biblioteka.data.Skaitytojas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.PathParam;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("grazinimas")
public class GrazinimasController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private KnygaDAO knygaDAO;
    @Autowired
    private SkaitytojasDAO skaitytojasDAO;
    @Autowired
    private RegistravimasDAO registravimasDAO;

    @GetMapping
    public ModelAndView grazinimas() {
        ModelAndView mav = new ModelAndView("grazinimas");
        mav.addObject("skaitytojai", skaitytojasDAO.findAll());
        return mav;
    }
    @GetMapping("sarasas")
    public ModelAndView list(
            @RequestParam("skaitytojas_id") Integer skId
    ) {

        ModelAndView mav = new ModelAndView("skaitytojoknygos");
        ArrayList<Skaitytojas> sk = (ArrayList<Skaitytojas>) skaitytojasDAO.pagalId(skId);
        System.out.println("skaitytojo id: "+skId+" ;jis vardu: "+sk.get(0).getName());
        ArrayList<Registravimas> list = (ArrayList<Registravimas>) registravimasDAO.pagalSkaitytoja(skId); //neperskaitytos kn pagal skaitytoja
        ArrayList<Registravimas> list2 = (ArrayList<Registravimas>) registravimasDAO.pagalSkIdPerskaitytosKn(skId); //perskaitytos kn pagal skaitytoja
        ArrayList<Knyga> kn = new ArrayList<>();
        ArrayList<Knyga> perskKn = new ArrayList<>();

        for (int i=0;i< list.size();i++){
            kn.add(knygaDAO.pagalId(list.get(i).getKnygId())) ; //turedamas knygu id,isgaunu ju pavadinimus kuriuos sukeliu i "kn" lista
        }

        for (int i=0;i< list2.size();i++){
            perskKn.add(knygaDAO.pagalId(list2.get(i).getKnygId())) ; //turedamas knygu id,isgaunu ju pavadinimus kuriuos sukeliu i "perskKn" lista
        }
        mav.addObject("skaitytojas", skaitytojasDAO.pagalId(skId));// sukurtas objektas,bet lyg ir nepanaudoju jo ateiti, mhhh
        mav.addObject("list", kn); //dar neperskaitytu knygu sarasas
        mav.addObject("list2", perskKn); //perskaitytu knygu sarasas
        mav.addObject("sk_id", skId); //skaitytojo id siunciu kad butu paprasciau ateiti persiusti info
        mav.addObject("sk_name",sk.get(0).getName()); //taip paprasciau isgaunu skaitytojo name kuri panaudoju aprasant puslapi

        return mav;
    }

    @GetMapping("grazina/{knid}/{skid}")
    @Transactional
    public ModelAndView list2(
            @PathVariable("knid") Integer knId,
            @PathVariable("skid") Integer skId
    ) throws IOException {
        System.out.println("kn_id:"+knId+" ir sk_id:"+skId);
        ModelAndView mav = new ModelAndView("skaitytojoknygos");

        if (skId == null || knId == null) {
            System.out.println("neturim duomenu kad surasti skaitytojo grazinama knyga");
        } else {
            ArrayList<Registravimas> grazKn = (ArrayList<Registravimas>) registravimasDAO.pagalSkIdIrKnId(skId,knId);
            Optional<Registravimas> oRegistravimas = registravimasDAO.findById(grazKn.get(0).getId());
            DataClass data = new DataClass();
            if (oRegistravimas.isPresent()) {
                Registravimas r = new Registravimas();
                r.setKnygId(knId);
                r.setSkaitId(skId);
                r.setStart(grazKn.get(0).getStart());
                try {
                    r.setFinish(sdf.parse(data.getDataNow()));
                } catch (ParseException ex) {
                    r.setFinish(null);
                }
                try {
                    r.setReturned(1);
                } catch (NullPointerException ex) {
                    r.setReturned(0);
                }
                System.out.println("bandom koreguoti registruotus duomenis: "+r);
                registravimasDAO.save(r);
            }
            delete(grazKn.get(0).getId(),skId);
        }

        ArrayList<Skaitytojas> sk = (ArrayList<Skaitytojas>) skaitytojasDAO.pagalId(skId);
        ArrayList<Registravimas> list = (ArrayList<Registravimas>) registravimasDAO.pagalSkaitytoja(skId); //neperskaitytos kn pagal skaitytoja
        ArrayList<Registravimas> list2 = (ArrayList<Registravimas>) registravimasDAO.pagalSkIdPerskaitytosKn(skId); //perskaitytos kn pagal skaitytoja
        ArrayList<Knyga> kn = new ArrayList<>();
        ArrayList<Knyga> perskKn = new ArrayList<>();
        if (list.isEmpty()){
            System.out.println("Kolkas skaitytojas knygu neskaito");
        }else {
            for (int i=0;i< list.size();i++){
                kn.add(knygaDAO.pagalId(list.get(i).getKnygId())) ; //turedamas knygu id,isgaunu ju pavadinimus kuriuos sukeliu i "kn" lista
            }
        }
        if (list2.isEmpty()){
            System.out.println("Perskaitytu knygu skaitytojas neturi");
        }else{
            for (int i=0;i< list2.size();i++){
                perskKn.add(knygaDAO.pagalId(list2.get(i).getKnygId())) ; //turedamas knygu id,isgaunu ju pavadinimus kuriuos sukeliu i "perskKn" lista
            }
        }

        mav.addObject("skaitytojas", skaitytojasDAO.pagalId(skId));// sukurtas objektas,bet lyg ir nepanaudoju jo ateiti, mhhh
        mav.addObject("list", kn); //dar neperskaitytu knygu sarasas
        mav.addObject("list2", perskKn); //perskaitytu knygu sarasas
        mav.addObject("sk_id", skId); //skaitytojo id siunciu kad butu paprasciau ateiti persiusti info
        mav.addObject("sk_name",sk.get(0).getName()); //taip paprasciau isgaunu skaitytojo name kuri panaudoju aprasant puslapi

        return mav;
    }

    @GetMapping("delete")
    @Transactional
    public ModelAndView delete(
            @RequestParam("id") Integer id,
            @RequestParam("skid") Integer skId
            ) {
        registravimasDAO.deleteById(id);
        return list(skId);
    }
}
