package sn.isi.impot.service;

import sn.isi.impot.entities.Paiement;

import java.util.List;

public interface PaiementService {
    Paiement addPaiement(Paiement paiement);
    List<Paiement> getAllPaiements();
}
