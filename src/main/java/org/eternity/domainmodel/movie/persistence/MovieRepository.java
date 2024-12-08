package org.eternity.domainmodel.movie.persistence;

import jakarta.persistence.LockModeType;
import org.eternity.domainmodel.movie.domain.Movie;
import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Override
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @EntityGraph(value = "Movie.policy")
    Optional<Movie> findById(Long id);
}
