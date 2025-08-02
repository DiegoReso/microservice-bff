package com.reso.bffscheduler.infrastructure.client;


import com.reso.bffscheduler.business.dto.userDTO.AddressDTO;
import com.reso.bffscheduler.business.dto.userDTO.PhoneDTO;
import com.reso.bffscheduler.business.dto.userDTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users", url = "${user.url}")
public interface UserClient {

    @GetMapping()
    UserDTO findUserByEmail(@RequestParam("email") String email,
                            @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PostMapping("login")
    String login(@RequestBody UserDTO userDTO);

    @GetMapping()
    UserDTO getUser(@RequestParam("email") String email,
                    @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deleteUser(@PathVariable String email,
                    @RequestHeader("Authorization") String token);

    @PutMapping()
    UserDTO updateUser(@RequestBody UserDTO userDTO,
                       @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTO updateAddress(@RequestBody AddressDTO addressDTO,
                             @RequestParam("id") Long id,
                             @RequestHeader("Authorization") String token);

    @PutMapping("/phone")
    PhoneDTO updatePhone(@RequestBody PhoneDTO phoneDTO,
                         @RequestParam("id") Long id,
                         @RequestHeader("Authorization") String token);


    @PostMapping("/address")
    UserDTO insertAddress(@RequestBody AddressDTO addressDTO,
                          @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    UserDTO insertPhone(@RequestBody PhoneDTO phoneDTO,
                        @RequestHeader("Authorization") String token);
}
