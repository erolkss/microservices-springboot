package br.com.ero.servidorpublicomicroservice.apis;


import br.com.ero.servidorpublicomicroservice.entities.ServidorPublico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ServidorPublicoAPIRest {

    @GetMapping("/listarServidores")
    public ResponseEntity<List<ServidorPublico>> listarServidores();

    @GetMapping("/listarServidor/{matricula}")
    public ResponseEntity<ServidorPublico> listarServidor(@PathVariable long matricula);

    @DeleteMapping("/excluirServidor/{matricula}")
    public void excluirServidor(@PathVariable long matricula);

    @PutMapping("/editarServidor/{matricula}")
    public ResponseEntity<String> editarServidor(@PathVariable long matricula, @RequestBody ServidorPublico servidorPublico);

    @PostMapping("/cadastrarServidor")
    public ResponseEntity<String> cadastrarServidor(@RequestBody ServidorPublico novoServidor);

    @GetMapping("/listarServidores/{idcurso}")
    public List<ServidorPublico> listarServidoresCurso(@PathVariable long idcurso);




}
