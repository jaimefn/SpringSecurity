package br.com.nextmove.condonext.repository.userlogin;

import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findByLogin(String login);
}
