package br.com.ero.servidorpublicomicroservice.repositories;


import br.com.ero.servidorpublicomicroservice.entities.ServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorPublicoRepository extends JpaRepository<ServidorPublico, Long> {
    List<ServidorPublico> findAllByIdcurso(Long idcurso);
}
