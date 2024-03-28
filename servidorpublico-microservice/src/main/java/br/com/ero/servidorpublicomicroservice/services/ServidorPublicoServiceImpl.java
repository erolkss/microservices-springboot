package br.com.ero.servidorpublicomicroservice.services;


import br.com.ero.servidorpublicomicroservice.entities.ServidorPublico;
import br.com.ero.servidorpublicomicroservice.repositories.ServidorPublicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ServidorPublicoServiceImpl implements ServidorPublicoService {

    @Autowired
    private ServidorPublicoRepository servidorPublicoRepository;


    @Override
    public List<ServidorPublico> listAll() {
        List<ServidorPublico> servidoresPublicos = new ArrayList<>();
        servidorPublicoRepository.findAll().forEach(servidoresPublicos::add);
        return servidoresPublicos;
    }

    @Override
    public Optional<ServidorPublico> listByMatricula(long matricula) {
        return servidorPublicoRepository.findById(matricula);
    }

    @Override
    public void save(ServidorPublico servidorPublico) {
        servidorPublicoRepository.save(servidorPublico);
    }

    @Override
    public void update(ServidorPublico servidorPublico) {
        Optional<ServidorPublico> servidorEncontrado = servidorPublicoRepository.findById(servidorPublico.getMatricula());

        servidorEncontrado.ifPresent(
                servidor -> {
                    servidorPublicoRepository.save(servidorPublico);
                }
        );
    }

    @Override
    public void delete(long matricula) {
        Optional<ServidorPublico> servidorEncontrado = servidorPublicoRepository.findById(matricula);

        servidorEncontrado.ifPresent(
                servidor -> {
                    servidorPublicoRepository.delete(servidorEncontrado.get());
                }
        );

    }

    @Override
    public List<ServidorPublico> findAllByIdcurso(Long idcurso) {
        return servidorPublicoRepository.findAllByIdcurso(idcurso);
    }

}
