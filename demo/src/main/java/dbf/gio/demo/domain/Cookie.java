package dbf.gio.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import dbf.gio.demo.domain.Jogador;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_Cookie")
public class Cookie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 64)
    private String nome;

    @Column(name = "pontuacao")
    private int vidas;

    @Column(name = "multiplicador")
    private double multiplicador; 

    @Column(name = "jogador_id")
    private Jogador jogador; 

}
