package br.com.ero.cursomicroservice.clients;

import br.com.ero.cursomicroservice.entities.ServidorPublico;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servidorpublico", url = "DESKTOP-S801CA9.mshome.net:8090")
public interface ServidorPublicoServiceClient {

    @GetMapping("/servidorpublico/listarServidores/{idcurso}")
    public List<ServidorPublico> listarServidoresCurso(@PathVariable Long idcurso);
}