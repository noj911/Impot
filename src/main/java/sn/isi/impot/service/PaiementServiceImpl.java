package sn.isi.impot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.isi.impot.entities.Paiement;
import sn.isi.impot.repository.PaiementRepository;

import java.util.List;

@Service
public class PaiementServiceImpl implements PaiementService{

    @Autowired
    private PaiementRepository paiementRepository;
    @Override
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
}
