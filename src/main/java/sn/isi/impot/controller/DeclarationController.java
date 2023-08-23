package sn.isi.impot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.isi.impot.entities.Declarant;
import sn.isi.impot.entities.Declaration;
import sn.isi.impot.service.DeclarantService;
import sn.isi.impot.service.DeclarationService;

import java.util.List;

@Controller
@RequestMapping("/declarations")
public class DeclarationController {
    @Autowired
    private DeclarationService declarationService;
    @Autowired
    private DeclarantService declarantService;

    //list of declaration
    @GetMapping("/list")
    public String listDeclaration(Model model)
    {
        model.addAttribute("listDeclaration", declarationService.getAllDeclarations());
        return "declaration/list";
    }

    @GetMapping("/addNewDeclaration")
    public String addNewDeclaration(Model model)
    {
        //Retrieve list of declare in declaration
        List<Declarant> listDeclarants = declarantService.getAllDeclarants();
        model.addAttribute("listDeclarants", listDeclarants);

        Declaration declaration = new Declaration();
        model.addAttribute("declaration", declaration);
        return "declaration/add_declaration";
    }

    @PostMapping("/saveDeclaration")
    public String saveDeclaration(@ModelAttribute("declaration") Declaration declaration)
    {
        //save declarant to database
        declarationService.addDeclaration(declaration);
        return "redirect:/declarations/list";
    }
}
