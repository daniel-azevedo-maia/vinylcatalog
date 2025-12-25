package br.com.daniel.vinylcatalog.domain.repository;

import br.com.daniel.vinylcatalog.domain.model.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {
}
