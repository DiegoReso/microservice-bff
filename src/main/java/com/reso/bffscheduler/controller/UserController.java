package com.reso.bffscheduler.controller;


import com.reso.bffscheduler.business.UserService;
import com.reso.bffscheduler.business.dto.in.AddressDTORequest;
import com.reso.bffscheduler.business.dto.in.LoginDTORequest;
import com.reso.bffscheduler.business.dto.in.PhoneDTORequest;
import com.reso.bffscheduler.business.dto.in.UserDTORequest;
import com.reso.bffscheduler.business.dto.out.AddressDTOResponse;
import com.reso.bffscheduler.business.dto.out.PhoneDTOResponse;
import com.reso.bffscheduler.business.dto.out.UserDTOResponse;
import com.reso.bffscheduler.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User Management API")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Insert user", description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid user data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "409", description = "Conflict, user already exists"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTORequest userDTORequest) {
        UserDTOResponse user = userService.insertUser(userDTORequest);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("login")
    @Operation(summary = "User Login", description = "Authenticate user and return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, token returned"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String login(@RequestBody LoginDTORequest loginDTORequest) {
        return userService.userLogin(loginDTORequest);
    }

    @GetMapping()
    @Operation(summary = "Get User by Email", description = "Retrieve user details by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDTOResponse> getUser(@RequestParam("email") String email,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        UserDTOResponse userByEmail = userService.findUserByEmail(email, token);
        return ResponseEntity.ok(userByEmail);
    }


    @DeleteMapping("/{email}")
    @Operation(summary = "Delete User by Email", description = "Delete user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable String email,
                                           @RequestHeader(name = "Authorization", required = false) String token) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @Operation(summary = "Update User Data", description = "Update user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDTOResponse> updateUser(@RequestBody UserDTORequest userDTORequest,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateDataUser(userDTORequest, token));
    }

    @PutMapping("/address")
    @Operation(summary = "Update User Address", description = "Update user's address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest addressDTORequest,
                                                            @RequestParam("id") Long id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTORequest, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "Update User Phone", description = "Update user's phone by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone updated successfully"),
            @ApiResponse(responseCode = "404", description = "Phone not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PhoneDTOResponse> updatePhone(@RequestBody PhoneDTORequest phoneDTORequest,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updatePhone(id, phoneDTORequest, token));
    }


    @PostMapping("/address")
    @Operation(summary = "Insert User Address", description = "Insert a new address for the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address inserted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDTOResponse> insertAddress(@RequestBody AddressDTORequest addressDTORequest,
                                                         @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.insertAddress(addressDTORequest, token));
    }

    @PostMapping("/phone")
    @Operation(summary = "Insert User Phone", description = "Insert a new phone for the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone inserted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDTOResponse> insertPhone(@RequestBody PhoneDTORequest phoneDTORequest,
                                                       @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.insertPhone(phoneDTORequest, token));
    }

}

