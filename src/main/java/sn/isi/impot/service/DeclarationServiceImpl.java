package sn.isi.impot.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sn.isi.impot.entities.Declaration;
import sn.isi.impot.repository.DeclarationRepository;

import java.util.List;

@Service
public class DeclarationServiceImpl implements DeclarationService{

    @Autowired
    public DeclarationRepository declarationRepository;


    @Override
    public Declaration addDeclaration(Declaration declaration) {
        return declarationRepository.save(declaration);
    }

    @Override
    public List<Declaration> getAllDeclarations() {
        return declarationRepository.findAll();
    }

    @Override
    public Declaration getDeclarationById(Long id) {
        return declarationRepository.findById(id).orElse(null);
    }
}
