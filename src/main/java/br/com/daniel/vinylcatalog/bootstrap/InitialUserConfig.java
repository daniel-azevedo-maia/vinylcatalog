package br.com.daniel.vinylcatalog.bootstrap;

import br.com.daniel.vinylcatalog.domain.model.User;
import br.com.daniel.vinylcatalog.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitialUserConfig {

    @Bean
    CommandLineRunner createUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (userRepository.findByUsername("daniel").isEmpty()) {

                User user = new User(
                        "daniel",                         // username
                        passwordEncoder.encode("123"),    // password
                        "Daniel",                         // firstName
                        "Maia"                            // lastName
                );

                userRepository.save(user);
            }
        };
    }
}
