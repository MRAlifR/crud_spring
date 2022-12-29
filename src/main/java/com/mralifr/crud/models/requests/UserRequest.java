package com.mralifr.crud.models.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    Long userid;
    String namalengkap;
    String username;
    String password;
    Character status;
}
