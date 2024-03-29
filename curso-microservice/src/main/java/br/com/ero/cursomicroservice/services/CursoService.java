package br.com.ero.cursomicroservice.services;


import br.com.ero.cursomicroservice.entities.Curso;
import br.com.ero.cursomicroservice.entities.ServidorPublico;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listAll();
    Optional<Curso> listByIdCurso(Long idcurso);
    void save(Curso curso);
    void update(Curso curso);
    void delete(Long idcurso);
    public List<ServidorPublico> listarServidoresCurso(Long idcurso);

}
