package sn.isi.impot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.isi.impot.entities.Declarant;
import sn.isi.impot.service.DeclarantService;

@Controller
@RequestMapping("/declarants")
public class DeclarantController {
    @Autowired
    private DeclarantService declarantService;

    //display list of declarant
    @GetMapping("/list")
    public String listDeclarants(Model model)
    {
        model.addAttribute("listDeclarants", declarantService.getAllDeclarants());
        return "declarant/list";
    }

    @GetMapping("/addNewDeclarant")
    public String addNewDeclarant(Model model)
    {
        //create model attribute to bind form data
        Declarant declarant = new Declarant();
        model.addAttribute("declarant", declarant);
        return "declarant/add_declarant";

    }

    @PostMapping("/saveDeclarant")
    public String saveDeclarant(@ModelAttribute("declarant") Declarant declarant, Model model) {
        if (!declarantService.isRaisonSocialeUnique(declarant.getRaisonSociale())) {
            model.addAttribute("errorMessage", "La raison sociale existe déjà.");
            return "declarant/add_declarant";
        }
        if (!declarantService.isTelephoneUnique(declarant.getTelephone())) {
            model.addAttribute("errorMessage", "Le numéro de téléphone existe déjà.");
            return "declarant/add_declarant";
        }
        if (!declarantService.isEmailUnique(declarant.getEmail())) {
            model.addAttribute("errorMessage", "L'e-mail existe déjà.");
            return "declarant/add_declarant";
        }

        try {
            declarantService.addDeclarant(declarant);
            model.addAttribute("successMessage", "Enregistrement effectué avec succès !");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de l'enregistrement.");
        }
        return "redirect:/declarants/list";
    }

}
