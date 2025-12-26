package br.com.daniel.vinylcatalog.domain.repository;

import br.com.daniel.vinylcatalog.domain.model.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {

    List<Vinyl> findAllByOwnerUsernameOrderByIdDesc(String username);

    Optional<Vinyl> findByIdAndOwnerUsername(Long id, String username);
}
