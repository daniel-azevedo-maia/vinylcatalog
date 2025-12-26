package br.com.daniel.vinylcatalog.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vinyls")
public class Vinyl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String album;
    private String artista;
    private Integer ano;
    private String genero;
    private String ondeComprei;
    private String estado;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public Vinyl(String album, String artista, Integer ano, String genero, String ondeComprei, String estado, User owner) {
        this.album = album;
        this.artista = artista;
        this.ano = ano;
        this.genero = genero;
        this.ondeComprei = ondeComprei;
        this.estado = estado;
        this.owner = owner;
    }
}
