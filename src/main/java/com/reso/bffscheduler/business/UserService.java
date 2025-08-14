package com.reso.bffscheduler.business;

import com.reso.bffscheduler.business.dto.in.AddressDTORequest;
import com.reso.bffscheduler.business.dto.in.LoginDTORequest;
import com.reso.bffscheduler.business.dto.in.PhoneDTORequest;
import com.reso.bffscheduler.business.dto.in.UserDTORequest;
import com.reso.bffscheduler.business.dto.out.AddressDTOResponse;
import com.reso.bffscheduler.business.dto.out.PhoneDTOResponse;
import com.reso.bffscheduler.business.dto.out.UserDTOResponse;
import com.reso.bffscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

   private final UserClient client;

    public UserDTOResponse insertUser(UserDTORequest dtoRequest){
        return client.createUser(dtoRequest);
    }

    public String userLogin(LoginDTORequest loginDTORequest){
        return client.login(loginDTORequest);
    }

    public UserDTOResponse findUserByEmail(String email, String token){
        return client.findUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token){
        client.deleteUser(email,token);
    }

    public UserDTOResponse updateDataUser(UserDTORequest dtoRequest, String token){
        return client.updateUser(dtoRequest,token);
    }

    public AddressDTOResponse updateAddress(Long idAddress, AddressDTORequest addressDTORequest, String token){
       return client.updateAddress(addressDTORequest, idAddress, token);
    }

    public PhoneDTOResponse updatePhone(Long idPhone, PhoneDTORequest phoneDTORequest, String  token ){
        return client.updatePhone(phoneDTORequest, idPhone, token);
    }

    public UserDTOResponse insertAddress(AddressDTORequest addressDTORequest, String token){
        return client.insertAddress(addressDTORequest, token);
    }

    public UserDTOResponse insertPhone(PhoneDTORequest phoneDTORequest, String token){
       return client.insertPhone(phoneDTORequest, token);
    }
}
