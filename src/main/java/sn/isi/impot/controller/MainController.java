package sn.isi.impot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    //Access the declarant homePage
    @GetMapping("/declarants")
    public String declarants() {
        return "declarant/list"; // Nom de la page pour la liste des déclarants
    }

    //Access the declaration home page
    @GetMapping("/declarations")
    public String declarations() {
        return "declaration/list"; // Nom de la page pour la liste des déclarations
    }

    //Access the paiement home page
    @GetMapping("/paiements")
    public String paiements() {
        return "paiement/list"; // Nom de la page pour la liste des paiements
    }
}
