package br.com.nextmove.condonext.repository.user;

import br.com.nextmove.condonext.domain.user.User;
import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByIdAndDeletedFalse(Long id);
    Page<User> findAllByDeletedFalse(Pageable pageable);
}
