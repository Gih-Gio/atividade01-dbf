package dbf.gio.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

import java.sql.Date;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 64)
    private String nome;
    @Column(name = "nivel")
    private int nivel;
    @Column(name = "dataNascimento")
    private Date dataNascimento;
    @Column(name = "pontuacao")
    private int pontuacao;
    @Column(name = "email", length = 64)
    private String email;
    @Column(name = "senha", length = 15)
    private String senha;
    @Column(name = "telefone", length = 14)
    private String telefone;
    @Column(name = "paisorigem", length = 64)
    private String paisorigem;

   

    public static Jogador parseNote(String line) {
        String[] text = line.split(",");
        Jogador note = new Jogador();
        note.setId(Long.parseLong(text[0]));
        note.setNome(text[1]);
        return note;
    }
}
