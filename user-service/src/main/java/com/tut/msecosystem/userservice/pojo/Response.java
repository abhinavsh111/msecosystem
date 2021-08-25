package com.tut.msecosystem.userservice.pojo;

import com.tut.msecosystem.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private User user;
    private Department department;
}
