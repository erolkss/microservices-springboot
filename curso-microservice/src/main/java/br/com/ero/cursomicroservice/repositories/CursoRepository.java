package br.com.ero.cursomicroservice.repositories;

import br.com.ero.cursomicroservice.entities.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
}
