package sn.isi.impot.service;

import sn.isi.impot.entities.Declaration;

import java.util.List;

public interface DeclarationService {
    void addDeclaration(Declaration declaration);
    List<Declaration> getAllDeclarations();

    Declaration getDeclarationById(Long id);
}
