package com.reso.bffscheduler.business;

import com.reso.bffscheduler.business.dto.userDTO.AddressDTO;
import com.reso.bffscheduler.business.dto.userDTO.PhoneDTO;
import com.reso.bffscheduler.business.dto.userDTO.UserDTO;
import com.reso.bffscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

   private final UserClient client;

    public UserDTO insertUser(UserDTO userDTO){
        return client.createUser(userDTO);
    }

    public String userLogin(UserDTO userDTO){
        return client.login(userDTO);
    }

    public UserDTO findUserByEmail(String email, String token){
        return client.findUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token){
        client.deleteUser(email,token);
    }

    public UserDTO updateDataUser(UserDTO userDTO, String token){
        return client.updateUser(userDTO,token);
    }

    public AddressDTO updateAddress(Long idAddress, AddressDTO addressDTO, String token){
       return client.updateAddress(addressDTO, idAddress, token);
    }

    public PhoneDTO updatePhone(Long idPhone, PhoneDTO phoneDTO, String  token ){
        return client.updatePhone(phoneDTO, idPhone, token);
    }

    public UserDTO insertAddress(AddressDTO addressDTO, String token){
        return client.insertAddress(addressDTO, token);
    }

    public UserDTO insertPhone(PhoneDTO phoneDTO, String token){
       return client.insertPhone(phoneDTO, token);
    }
}
