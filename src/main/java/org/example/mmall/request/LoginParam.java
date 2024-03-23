package org.example.mmall.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户数据响应对象")
public class LoginParam implements Serializable {

    @Schema(description = "手机号")
    @NotBlank
    private String phone;

    @NotBlank
    @Schema(description = "登陆密码")
    private String password;
}
