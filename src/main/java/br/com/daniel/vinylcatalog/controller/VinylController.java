package br.com.daniel.vinylcatalog.controller;

import br.com.daniel.vinylcatalog.domain.model.Vinyl;
import br.com.daniel.vinylcatalog.domain.repository.VinylRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VinylController {

    private final VinylRepository vinylRepository;

    @PostMapping("/vinyls")
    public String addVinyl(
            @RequestParam String album,
            @RequestParam String artista,
            @RequestParam(required = false) Integer ano,
            @RequestParam String genero,
            @RequestParam String ondeComprei,
            @RequestParam String estado) {

        Vinyl vinyl = new Vinyl(album, artista, ano, genero, ondeComprei, estado);
        vinylRepository.save(vinyl);
        return "redirect:/";
    }
}