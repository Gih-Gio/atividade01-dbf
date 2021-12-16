package dbf.gio.demo.web.api;

import dbf.gio.demo.domain.Jogador;
import dbf.gio.demo.service.JogadorService;
import org.apache.tomcat.Jar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jogadores")
public class JogadorResource {
    private final Logger log = LoggerFactory.getLogger(JogadorResource.class);

    private final JogadorService jogadorService;

    public JogadorResource(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    /**
     * {@code GET  /jogadores/:id} : get the "id" jogador.
     *
     * @param id o id do jogador que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o jogador, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogador(@PathVariable Long id) {
        log.debug("REST request to get Pessoa : {}", id);
        Optional<Jogador>  Jogador = jogadorService.findOne(id);
        if(Jogador.isPresent()) {
            return ResponseEntity.ok().body(Jogador.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Jogador>> getJogadores(){
        List<Jogador> lista = jogadorService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /jogadores} : Atualiza um pessoa existenteUpdate.
     *
     * @param jogador o jogador a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o pessoa atualizado,
     * ou com status {@code 400 (Bad Request)} se o jogador não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o jogador não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Jogador> updateJogador(@RequestBody Jogador jogador) throws URISyntaxException {
        log.debug("REST request to update Jogador : {}", jogador);
        if (jogador.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Jogador id null");
        }
        Jogador result = jogadorService.save(jogador);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /jogadores} : Create a new jogador.
     *
     * @param jogador the jogador to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jogador, or with status {@code 400 (Bad Request)} if the pessoa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) throws URISyntaxException {
        log.debug("REST request to save Jogador : {}", jogador);
        if (jogador.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo pessoa não pode terum ID");
        }
        Jogador result = jogadorService.save(jogador);
        return ResponseEntity.created(new URI("/api/jogadores/" + result.getId()))
                .body(result);
    }

    @PostMapping(value = "/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Jogador> upload(@RequestPart("data") MultipartFile csv) throws IOException {
        List<Jogador> savedNotes = new ArrayList<>();
        List<Jogador> notes = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(csv).getInputStream(), StandardCharsets.UTF_8)).lines()
                .map(Jogador::parseNote).collect(Collectors.toList());
        jogadorService.saveAll(notes).forEach(savedNotes::add);
        return savedNotes;
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" jogador.
     *
     * @param id o id do jogadores que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJogador(@PathVariable Long id) {
        log.debug("REST request to delete Pessoa : {}", id);

        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
