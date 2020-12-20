package br.com.nextmove.condonext.domain.userlogin;

import br.com.nextmove.condonext.domain.user.Profiles;
import br.com.nextmove.condonext.domain.user.User;
import com.google.common.base.Strings;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_login")
public class UserLogin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({ @JoinColumn(name = "user_id", referencedColumnName = "id", unique = false, nullable = false) })
    User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profiles> profiles;

    public UserLogin(){}

    public UserLogin(String login, String password) {
        this.id = null;
        this.login = login;
        this.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password =  (Strings.isNullOrEmpty(password))? null : new BCryptPasswordEncoder().encode(password);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
