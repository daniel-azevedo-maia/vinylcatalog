package br.com.daniel.vinylcatalog.controller;

import br.com.daniel.vinylcatalog.domain.model.User;
import br.com.daniel.vinylcatalog.domain.model.Vinyl;
import br.com.daniel.vinylcatalog.domain.repository.VinylRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam String estado,
            Authentication auth
    ) {

        User owner = (User) auth.getPrincipal();

        Vinyl vinyl = new Vinyl(
                album,
                artista,
                ano,
                genero,
                ondeComprei,
                estado,
                owner
        );

        vinylRepository.save(vinyl);
        return "redirect:/";
    }

    @PostMapping("/vinyls/delete/{id}")
    public String deleteVinyl(@PathVariable Long id, Authentication auth) {

        User owner = (User) auth.getPrincipal();

        vinylRepository.findByIdAndOwnerUsername(id, owner.getUsername())
                .ifPresent(vinylRepository::delete);

        return "redirect:/";
    }

    @GetMapping("/vinyls/edit/{id}")
    public String editForm(@PathVariable Long id, Authentication auth, Model model) {

        User owner = (User) auth.getPrincipal();

        Vinyl vinyl = vinylRepository
                .findByIdAndOwnerUsername(id, owner.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Disco não encontrado"));

        model.addAttribute("vinyl", vinyl);
        return "edit-vinyl";
    }

    @PostMapping("/vinyls/update/{id}")
    public String updateVinyl(
            @PathVariable Long id,
            Vinyl vinylDetails,
            Authentication auth
    ) {

        User owner = (User) auth.getPrincipal();

        Vinyl vinyl = vinylRepository
                .findByIdAndOwnerUsername(id, owner.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Disco não encontrado"));

        vinyl.setAlbum(vinylDetails.getAlbum());
        vinyl.setArtista(vinylDetails.getArtista());
        vinyl.setAno(vinylDetails.getAno());
        vinyl.setGenero(vinylDetails.getGenero());
        vinyl.setOndeComprei(vinylDetails.getOndeComprei());
        vinyl.setEstado(vinylDetails.getEstado());

        vinylRepository.save(vinyl);
        return "redirect:/";
    }
}
