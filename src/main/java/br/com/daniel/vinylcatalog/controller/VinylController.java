package br.com.daniel.vinylcatalog.controller;

import br.com.daniel.vinylcatalog.domain.model.Vinyl;
import br.com.daniel.vinylcatalog.domain.repository.VinylRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importante para passar dados para a tela
import org.springframework.web.bind.annotation.GetMapping; // Adicionado
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VinylController {

    private final VinylRepository vinylRepository;

    // LISTAR E ADICIONAR (Já existia, mantido)
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

    // REMOVER (Já existia, mantido)
    @PostMapping("/vinyls/delete/{id}")
    public String deleteVinyl(@PathVariable Long id) {
        vinylRepository.deleteById(id);
        return "redirect:/";
    }

    // --- NOVOS MÉTODOS PARA EDIÇÃO ---

    // 1. Abrir a tela de edição
    @GetMapping("/vinyls/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Vinyl vinyl = vinylRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disco inválido:" + id));
        model.addAttribute("vinyl", vinyl);
        return "edit-vinyl"; // Nome do novo arquivo HTML que você precisará criar
    }

    // 2. Salvar a alteração
    @PostMapping("/vinyls/update/{id}")
    public String updateVinyl(@PathVariable Long id, Vinyl vinylDetails) {
        Vinyl vinyl = vinylRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disco inválido:" + id));

        // Atualiza os campos (Isso só funciona se você adicionou @Setter no Vinyl.java)
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