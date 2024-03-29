package br.com.ero.cursomicroservice.apis;

import br.com.ero.cursomicroservice.entities.Curso;
import br.com.ero.cursomicroservice.entities.ServidorPublico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CursoAPIRest {


    @GetMapping("/listarCursos")
    public ResponseEntity<List<Curso>> listarCursos();

    //    @GetMapping("/listarCurso/{idcurso}")
    @GetMapping("/listarCurso/{idcurso}")
    public ResponseEntity<Curso> listarCurso(@PathVariable("idcurso") Long idcurso);

    @DeleteMapping("/excluirCurso/{idcurso}")
    public void excluirCurso(@PathVariable("idcurso") Long idcurso);

    @PutMapping("/editarCurso/{idcurso}")
    public ResponseEntity<String> editarCurso(@PathVariable("idcurso") Long idcurso, @RequestBody Curso curso);

    @PostMapping("/cadastrarCurso")
    public ResponseEntity<String> cadastrarCurso(@RequestBody Curso novoCurso);

    @GetMapping("/listarServidores/{idcurso}")
    public ResponseEntity<List<ServidorPublico>> listarServidoresCurso(@PathVariable Long idcurso);

}
