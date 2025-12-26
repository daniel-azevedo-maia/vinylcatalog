package br.com.daniel.vinylcatalog.controller;

import br.com.daniel.vinylcatalog.domain.model.User;
import br.com.daniel.vinylcatalog.domain.repository.VinylRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final VinylRepository vinylRepository;

    @GetMapping("/")
    public String home(Model model, Authentication auth) {

        if (auth != null && auth.getPrincipal() instanceof User user) {
            model.addAttribute("ownerName", user.getFullName());
            model.addAttribute("vinyls", vinylRepository.findAllByOwnerUsernameOrderByIdDesc(user.getUsername()));
        } else {
            model.addAttribute("ownerName", "Colecionador");
            model.addAttribute("vinyls", java.util.Collections.emptyList());
        }

        return "home";
    }
}
