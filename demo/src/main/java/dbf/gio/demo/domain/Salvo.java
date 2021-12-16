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
@Entity(name = "table_Salvar")
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jogador_id")
    private Jogador jogador; 
    @Column(name = "recorde")
    private int recorde;
}
