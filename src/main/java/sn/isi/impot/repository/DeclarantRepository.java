package sn.isi.impot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.impot.entities.Declarant;

@Repository
public interface DeclarantRepository extends JpaRepository<Declarant, Long> {


    Declarant findByRaisonSociale(String raisonSociale);

    Declarant findByTelephone(String telephone);

    Declarant findByEmail(String email);
}
