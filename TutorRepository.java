package br.com.fiap.petcall.repositories93509;

import br.com.fiap.petcall.model93509.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long> {
}
