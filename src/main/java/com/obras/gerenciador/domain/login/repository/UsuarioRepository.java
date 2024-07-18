package com.obras.gerenciador.domain.login.repository;

import com.obras.gerenciador.domain.login.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByName(String name);
}
