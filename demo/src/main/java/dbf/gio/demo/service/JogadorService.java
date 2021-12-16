package dbf.gio.demo.service;


import dbf.gio.demo.repository.JogadorRepository;
import dbf.gio.demo.domain.Jogador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    private final Logger log = LoggerFactory.getLogger(JogadorService.class);

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public List<Jogador> findAllList(){
        log.debug("Request to get All Pessoa");
        return jogadorRepository.findAll();
    }

    public Optional<Jogador> findOne(Long id) {
        log.debug("Request to get Jogador : {}", id);
        return jogadorRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Pessoa : {}", id);
        jogadorRepository.deleteById(id);
    }

    public Jogador save(Jogador jogador) {
        log.debug("Request to save Jogador : {}", jogador);
        jogador = jogadorRepository.save(jogador);
        return jogador;
    }

    public List<Jogador> saveAll(List<Jogador> jogadores) {
        log.debug("Request to save Jogador: {}", jogadores);
        jogadores = jogadorRepository.saveAll(jogadores);
        return jogadores;
    }
}
