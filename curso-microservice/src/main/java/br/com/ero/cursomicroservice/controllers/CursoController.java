package br.com.ero.cursomicroservice.controllers;

import br.com.ero.cursomicroservice.apis.CursoAPIRest;
import br.com.ero.cursomicroservice.entities.Curso;
import br.com.ero.cursomicroservice.entities.ServidorPublico;
import br.com.ero.cursomicroservice.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("curso")
public class CursoController implements CursoAPIRest {


    @Autowired
    private CursoService cursoService;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getReason());
        body.put("status", ex.getStatusCode());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // API Curso //
    @Override
    @GetMapping("/listarCursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listAll();
        return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
    }

    //    @RequestMapping(method = RequestMethod.GET, value = "/listarCurso/{idcurso}")
    @Override
    @GetMapping("/listarCurso/{idcurso}")
    public ResponseEntity<Curso> listarCurso(Long idcurso) {
        Optional<Curso> cursoEncontrado = cursoService.listByIdCurso(idcurso);
        if (cursoEncontrado.isPresent()) {
            return new ResponseEntity<Curso>(cursoEncontrado.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não Encontrado");
        }
    }

    @Override
    @DeleteMapping("/excluirCurso/{idcurso}")
    public void excluirCurso(Long idcurso) {
        Optional<Curso> cursoEncontrado = cursoService.listByIdCurso(idcurso);
        if (cursoEncontrado.isPresent()) {
            cursoService.delete(idcurso);
            new ResponseStatusException(HttpStatus.OK, "Curso Excluído com Sucesso.");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso Não Encontrado");
        }
    }

    @Override
    @PutMapping("/editarCurso/{idcurso}")
    public ResponseEntity<String> editarCurso(@PathVariable("idcurso") Long idcurso, Curso curso) {
        Optional<Curso> cursoEncontrado = cursoService.listByIdCurso(idcurso);
        if (cursoEncontrado.isPresent()) {
            cursoService.save(curso);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/cadastrarCurso")
    public ResponseEntity<String> cadastrarCurso(@RequestBody Curso novoCurso) {
        cursoService.save(novoCurso);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/listarServidores/{idcurso}")
    public ResponseEntity<List<ServidorPublico>> listarServidoresCurso(@PathVariable Long idcurso) {
        List<ServidorPublico> servidores = cursoService.listarServidoresCurso(idcurso);
        if (!servidores.isEmpty()) {
            return new ResponseEntity<List<ServidorPublico>>(servidores, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
