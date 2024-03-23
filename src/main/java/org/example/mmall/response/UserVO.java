package org.example.mmall.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserVO implements Serializable {
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "手机号")
    @NotBlank
    private String phone;
}
