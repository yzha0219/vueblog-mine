package com.markerhub.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "username can't be empty!")
    private String username;

    @NotBlank(message = "password can't be empty!")
    private String password;

}
