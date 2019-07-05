package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.constants.Constant;
import vn.com.rfim_api.persistences.entities.User;
import vn.com.rfim_api.persistences.repositories.UserRepository;
import vn.com.rfim_api.services.dtos.UserDTO;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository context;
    @Autowired
    private ModelMapper mapper;

    //login
    public ResponseEntity login(String username, String password) {
        ResponseMesasge response = new ResponseMesasge();
        User user = context.getByUsername(username);
        if (user != null) {
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            if (userDTO.getUsername().equals(username) && userDTO.getPassword().equals(password)) {
                return new ResponseEntity(userDTO, HttpStatus.OK);
            } else {
                response.setMessage(Constant.LOGIN_FAIL);
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            response.setMessage(Constant.LOGIN_FAIL);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
