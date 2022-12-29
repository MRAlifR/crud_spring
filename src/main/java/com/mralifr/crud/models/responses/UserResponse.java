package com.mralifr.crud.models.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {
    Long userid;
    String namalengkap;
    String username;
    String password;
    Character status;
}
