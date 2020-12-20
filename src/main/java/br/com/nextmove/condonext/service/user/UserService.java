package br.com.nextmove.condonext.service.user;

import br.com.nextmove.condonext.domain.user.User;
import br.com.nextmove.condonext.domain.userlogin.UserLogin;
import br.com.nextmove.condonext.dto.user.CreateUserDTO;
import br.com.nextmove.condonext.dto.user.ResponseUserDTO;
import br.com.nextmove.condonext.dto.user.UpdateUserDTO;
import br.com.nextmove.condonext.dto.userlogin.UserLoginDTO;
import br.com.nextmove.condonext.repository.user.UserRepository;
import br.com.nextmove.condonext.repository.userlogin.UserLoginRepository;
import br.com.nextmove.condonext.service.exceptions.LoginOrPasswordInvalidException;
import br.com.nextmove.condonext.service.exceptions.UserNotFoundException;
import br.com.nextmove.condonext.utils.AbstractUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractUtils {

    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserLoginRepository userLoginRepository){
        this.userRepository = userRepository;
        this.userLoginRepository = userLoginRepository;
    }

    @Transactional
    public ResponseUserDTO create(CreateUserDTO dto){
        User user = new User(dto);
        checkIfUserLoginAndPasswordIsValid(user.getUserLogin().getLogin(), user.getUserLogin().getPassword());
        return mapper.map(userRepository.save(user), ResponseUserDTO.class);
    }


    @Transactional
    public ResponseUserDTO update(Long id, UpdateUserDTO dto){
        User user = userRepository.findFirstByIdAndDeletedFalse(id);
        checkIfObjectExist(user);
        user.convert(dto);
        return mapper.map(userRepository.save(user), ResponseUserDTO.class);
    }

    @Transactional
    public void changePassword(Long userId, UserLoginDTO dto){
        UserLogin userLogin = userLoginRepository.findFirstByLoginAndUserIdAndUserDeletedFalse(dto.getLogin(),userId);
        checkIfObjectExist(userLogin);
        checkIfUserPasswordIsValid(dto.getPassword());
        userLogin.setPassword(dto.getPassword());
        userLoginRepository.save(userLogin);
    }

    @Transactional
    public Page<ResponseUserDTO> getAll(Pageable pageable){
        Page<User> usersPage = userRepository.findAllByDeletedFalse(pageable);
        List<ResponseUserDTO> respUsersList = usersPage.getContent().stream().map(u->mapper.map(u,ResponseUserDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(respUsersList,pageable,usersPage.getTotalElements());
    }

    @Transactional
    public ResponseUserDTO getById(Long id){
        User user = userRepository.findFirstByIdAndDeletedFalse(id);
        checkIfObjectExist(user);
        return mapper.map(user,ResponseUserDTO.class);
    }

    @Transactional
    public void delete(Long id){
        User user = userRepository.findFirstByIdAndDeletedFalse(id);
        checkIfObjectExist(user);
        user.setDeleted(true);
    }

    @Transactional
    public void forgetPassword(UserLoginDTO dto) {
        throw new NotImplementedException();
    }

    private void checkIfUserLoginAndPasswordIsValid(String login, String password) {
        if(Strings.isNullOrEmpty(login) || userLoginRepository.findFirstByLoginAndUserDeletedFalse(login).isPresent()) throw new LoginOrPasswordInvalidException();
        checkIfUserPasswordIsValid(password);
    }
    private void checkIfUserPasswordIsValid(String password) {
        if(Strings.isNullOrEmpty(password)) throw new LoginOrPasswordInvalidException();
    }
    private void checkIfObjectExist(Object obj) {
        if(obj == null) throw new UserNotFoundException();
    }
}
