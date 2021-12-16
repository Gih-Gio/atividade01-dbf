package dbf.gio.demo.web.api;

import dbf.gio.demo.domain.Jogador;
import dbf.gio.demo.service.JogadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class JogadorTesteResource {
    private final Logger log = LoggerFactory.getLogger(JogadorTesteResource.class);

    private final JogadorService jogadorService;

    public JogadorTesteResource(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping(path = "/jogadores/criar/{name}")
    public String helloApp(@PathVariable String name) {
        Jogador j = new Jogador();
        j.setNome(name);
        jogadorService.save(j);
        return  "criou";
    }

    @GetMapping(path = "/jogadores/listar/{id}")
    public Jogador helloApp(@PathVariable Long id) {
        return  jogadorService.findOne(id).get();
    }


}
