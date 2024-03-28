package br.com.ero.servidorpublicomicroservice.services;


import br.com.ero.servidorpublicomicroservice.entities.ServidorPublico;

import java.util.List;
import java.util.Optional;

public interface ServidorPublicoService {

	List<ServidorPublico> listAll();

	Optional<ServidorPublico> listByMatricula(long matricula);
	void save(ServidorPublico servidorPublico);
	void update(ServidorPublico servidorPublico);
	void delete(long matricula);
	List<ServidorPublico> findAllByIdcurso(Long idcurso);

}
