package sn.isi.impot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.isi.impot.constances.Constantes;
import sn.isi.impot.entities.Declarant;
import sn.isi.impot.service.DeclarantService;

@Controller
@RequestMapping("/declarants")
public class DeclarantController {

    private final DeclarantService declarantService;

    public DeclarantController(DeclarantService declarantService) {
        this.declarantService = declarantService;
    }

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
            model.addAttribute(Constantes.ErrorMessage, "La raison sociale existe déjà.");
            return Constantes.DECLARANT;
        }
        if (!declarantService.isTelephoneUnique(declarant.getTelephone())) {
            model.addAttribute(Constantes.ErrorMessage, "Le numéro de téléphone existe déjà.");
            return Constantes.DECLARANT;

        }
        if (!declarantService.isEmailUnique(declarant.getEmail())) {
            model.addAttribute(Constantes.ErrorMessage, "L'e-mail existe déjà.");
            return Constantes.DECLARANT;
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
