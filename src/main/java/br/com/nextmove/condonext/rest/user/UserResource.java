package br.com.nextmove.condonext.rest.user;

import br.com.nextmove.condonext.dto.user.CreateUserDTO;
import br.com.nextmove.condonext.dto.user.UpdateUserDTO;
import br.com.nextmove.condonext.dto.userlogin.UserLoginDTO;
import br.com.nextmove.condonext.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO userDTO){
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO userDTO){
        return new ResponseEntity<>(userService.update(id,userDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}/change-password",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> changePassword(@PathVariable Long id, @RequestBody UserLoginDTO userLoginDTO){
        userService.changePassword(id,userLoginDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/forget-password",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> forgetPassword(@RequestBody UserLoginDTO dto){
        userService.forgetPassword(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
}
