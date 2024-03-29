package br.com.ero.cursomicroservice.services;

import br.com.ero.cursomicroservice.clients.ServidorPublicoServiceClient;
import br.com.ero.cursomicroservice.entities.Curso;
import br.com.ero.cursomicroservice.entities.ServidorPublico;
import br.com.ero.cursomicroservice.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements  CursoService{

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ServidorPublicoServiceClient servidorPublicoServiceClient;

    @Override
    public List<Curso> listAll() {
        List<Curso> cursos = new ArrayList<>();
        cursoRepository.findAll().forEach(cursos::add);
        return cursos;
    }

    @Override
    public Optional<Curso> listByIdCurso(Long idcurso) {
        return cursoRepository.findById(idcurso);
    }

    @Override
    public void save(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public void update(Curso curso) {
        Optional<Curso> cursoEncontrado = cursoRepository.findById(curso.getIdcurso());
        cursoEncontrado.ifPresent(
                cursoFind -> {
                    cursoRepository.save(curso);
                }
        );
    }

    @Override
    public void delete(Long idcurso) {
        Optional<Curso> cursoEncontrado = cursoRepository.findById(idcurso);
        cursoEncontrado.ifPresent(
                cursoFind -> {
                    cursoRepository.delete(cursoEncontrado.get());
                }
        );
    }

    @Override
    public List<ServidorPublico> listarServidoresCurso(Long idcurso) {
        Optional<Curso> cursoEncontrado = cursoRepository.findById(idcurso);
        if (cursoEncontrado.isPresent()) {
            List<ServidorPublico> servidorPublicos = servidorPublicoServiceClient.listarServidoresCurso(idcurso);
            return servidorPublicos;
        }else {
            return null;
        }
    }
}
