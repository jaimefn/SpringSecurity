package br.com.nextmove.condonext.domain.user;

import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import br.com.nextmove.condonext.dto.user.CreateUserDTO;
import br.com.nextmove.condonext.dto.user.UpdateUserDTO;
import br.com.nextmove.condonext.dto.user.UserDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    private Boolean deleted = false;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    private String email;
    private String cpf;
    private String avatar;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserLogin userLogin;

    public User(){}

    public User(CreateUserDTO dto) {
        convert(dto);
        this.id = null;
        this.userLogin = new UserLogin(dto.getLogin(),dto.getPassword());
        this.userLogin.setUser(this);
    }

    public void convert(UserDTO dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.cpf = dto.getCpf();
        this.avatar = dto.getAvatar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
