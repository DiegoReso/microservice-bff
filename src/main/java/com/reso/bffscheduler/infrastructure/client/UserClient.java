package com.reso.bffscheduler.infrastructure.client;


import com.reso.bffscheduler.business.dto.in.AddressDTORequest;
import com.reso.bffscheduler.business.dto.in.LoginDTORequest;
import com.reso.bffscheduler.business.dto.in.PhoneDTORequest;
import com.reso.bffscheduler.business.dto.in.UserDTORequest;
import com.reso.bffscheduler.business.dto.out.AddressDTOResponse;
import com.reso.bffscheduler.business.dto.out.PhoneDTOResponse;
import com.reso.bffscheduler.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users", url = "${user.url}")
public interface UserClient {

    @GetMapping()
    UserDTOResponse findUserByEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse createUser(@RequestBody UserDTORequest dtoRequest);

    @PostMapping("login")
    String login(@RequestBody LoginDTORequest loginDTORequest);

    @GetMapping()
    UserDTOResponse getUser(@RequestParam("email") String email,
                            @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deleteUser(@PathVariable String email,
                    @RequestHeader("Authorization") String token);

    @PutMapping()
    UserDTOResponse updateUser(@RequestBody UserDTORequest dtoRequest,
                               @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest addressDTORequest,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/phone")
    PhoneDTOResponse updatePhone(@RequestBody PhoneDTORequest phoneDTORequest,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);


    @PostMapping("/address")
    UserDTOResponse insertAddress(@RequestBody AddressDTORequest addressDTORequest,
                                  @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    UserDTOResponse insertPhone(@RequestBody PhoneDTORequest phoneDTORequest,
                                @RequestHeader("Authorization") String token);
}
