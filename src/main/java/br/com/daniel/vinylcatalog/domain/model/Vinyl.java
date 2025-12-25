package br.com.daniel.vinylcatalog.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    public Vinyl(String album, String artista, Integer ano, String genero, String ondeComprei, String estado) {
        this.album = album;
        this.artista = artista;
        this.ano = ano;
        this.genero = genero;
        this.ondeComprei = ondeComprei;
        this.estado = estado;
    }
}