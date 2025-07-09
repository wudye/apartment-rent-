package com.mwu.common.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginUser
{

    private Long userId;
    private String username;
}
