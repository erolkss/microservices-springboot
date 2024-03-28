package br.com.ero.servidorpublicomicroservice.controllers;


import br.com.ero.servidorpublicomicroservice.apis.ServidorPublicoAPIRest;
import br.com.ero.servidorpublicomicroservice.entities.ServidorPublico;
import br.com.ero.servidorpublicomicroservice.services.ServidorPublicoService;
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
@RequestMapping("servidorpublico")
public class ServidorPublicoController implements ServidorPublicoAPIRest {

    @Autowired
    private ServidorPublicoService servidorPublicoService;


    // API Servidor Publico //

    @GetMapping("/listarServidores")
    public ResponseEntity<List<ServidorPublico>> listarServidores() {
        List<ServidorPublico> servidoresPublicos = servidorPublicoService.listAll();
        return new ResponseEntity<List<ServidorPublico>>(servidoresPublicos, HttpStatus.OK);
    }

    @GetMapping("/listarServidor/{matricula}")
    public ResponseEntity<ServidorPublico> listarServidor(long matricula) {
        Optional<ServidorPublico> servidorEncontrado = servidorPublicoService.listByMatricula(matricula);
        if (servidorEncontrado.isPresent()) {
            return new ResponseEntity<ServidorPublico>(servidorEncontrado.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Servidor Público Encontrado");
        }
    }

    @DeleteMapping("/excluirServidor/{matricula}")
    public void excluirServidor(long matricula) {
        Optional<ServidorPublico> servidorEncontrado = servidorPublicoService.listByMatricula(matricula);
        if (servidorEncontrado.isPresent()) {
            servidorPublicoService.delete(matricula);
            new ResponseStatusException(HttpStatus.OK, "Servidor Publico Excluído.");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Servidor Publico não encontrado");
        }

    }

    @PutMapping("/editarServidor/{matricula}")
    public ResponseEntity<String> editarServidor(long matricula, ServidorPublico servidorPublico) {
        Optional<ServidorPublico> servidorEncontrado = servidorPublicoService.listByMatricula(matricula);
        if (servidorEncontrado.isPresent()) {
            servidorPublicoService.save(servidorPublico);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cadastrarServidor")
    public ResponseEntity<String> cadastrarServidor(ServidorPublico servidorPublico) {
        Optional<ServidorPublico> servidorEncontrado = servidorPublicoService.listByMatricula(servidorPublico.getMatricula());
        if (!servidorEncontrado.isPresent()) {
            servidorPublicoService.save(servidorPublico);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Servidor Publico Já Cadastrado no Sistema.");
        }
    }

    @Override
    //  Resposta a chamada do Microservico
    public List<ServidorPublico> listarServidoresCurso(@PathVariable long idcurso) {
        List<ServidorPublico> servidores = servidorPublicoService.findAllByIdcurso(idcurso);
        return servidores;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getReason());
        body.put("status", ex.getStatusCode());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
