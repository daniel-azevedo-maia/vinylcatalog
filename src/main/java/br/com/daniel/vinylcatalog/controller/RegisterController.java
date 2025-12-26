package br.com.daniel.vinylcatalog.controller;

import br.com.daniel.vinylcatalog.domain.model.User;
import br.com.daniel.vinylcatalog.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam String password
    ) {

        // Se o usuário já existir, volta para o cadastro com erro
        if (userRepository.findByUsername(username).isPresent()) {
            return "redirect:/register?error";
        }

        // Cria o usuário com senha criptografada
        User user = new User(
                username,
                passwordEncoder.encode(password),
                firstName,
                lastName
        );

        userRepository.save(user);

        // Redireciona para o login com mensagem de sucesso
        return "redirect:/login?registered=true";
    }
}
