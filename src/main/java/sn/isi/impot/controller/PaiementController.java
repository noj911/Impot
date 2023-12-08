package sn.isi.impot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.isi.impot.entities.Declaration;
import sn.isi.impot.entities.Paiement;
import sn.isi.impot.service.DeclarationService;
import sn.isi.impot.service.PaiementService;

@Controller
@RequestMapping("/paiements")
public class PaiementController {


    private  final PaiementService paiementService;

    private final DeclarationService declarationService;

    public PaiementController(PaiementService paiementService, DeclarationService declarationService) {
        this.paiementService = paiementService;
        this.declarationService = declarationService;
    }


    @GetMapping("/list")
    public String listPaiements(Model model)
    {
        model.addAttribute("listPaiements", paiementService.getAllPaiements());
        return "paiement/list";
    }

    @GetMapping("/payDeclaration")
    public String payDeclaration(@RequestParam Long idDeclaration, Model model) {
        Declaration declaration = declarationService.getDeclarationById(idDeclaration);

        Paiement paiement = new Paiement();
        paiement.setDeclaration(declaration);

        model.addAttribute("paiement", paiement);
        model.addAttribute("idDeclaration", idDeclaration); // Ajoutez ceci à la méthode
        return "paiement/new_paiement";
    }



    @GetMapping("/addNewPaiement")
    public String addNewPaiement(Model model)
    {
        Paiement paiement = new Paiement();

        // Charger les données nécessaires pour le formulaire (liste de déclarations, etc.)
        model.addAttribute("paiement", paiement);
        model.addAttribute("listDeclarations", declarationService.getAllDeclarations());

        return "paiement/new_paiement"; // Nom de la vue du formulaire d'ajout de paiement
    }




    @PostMapping("/savePaiement")
    public String savePaiement(@ModelAttribute("paiement") Paiement paiement, Model model)
    {

        // Obtenir le montant de la déclaration associée
        Declaration declaration = paiement.getDeclaration();
        double montantDeclaration = declaration.getMontantDeclaration();
        System.out.println(montantDeclaration);
        System.out.println(paiement.getMontantPaiement());
        // Vérifier si le montant du paiement est inférieur ou égal au montant de la déclaration
        if (paiement.getMontantPaiement() == montantDeclaration) {
            model.addAttribute("errorMessage", "Le montant du paiement est supérieur à la déclaration.");
            model.addAttribute("declaration", declaration);
            return "paiement/add_paiement";
        }else {
            paiementService.addPaiement(paiement);
            model.addAttribute("successMessage", "Le paiement a été fait avec succes.");

            return "redirect:/paiements/list";
        }

        //save declarant to database

    }


}
