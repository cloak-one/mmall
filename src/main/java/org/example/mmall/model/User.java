package org.example.mmall.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Data
@Schema(name = "User", description = "用户")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "手机号")
    @NotBlank
    private String phone;

    @NotBlank
    @Schema(description = "登陆密码")
    private String password;

    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT)
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

//    @Schema(description = "用户地址")
//    private String address;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public User(Long id, String phone, String password) {
        this.id = id;
        this.phone = phone;
        this.password = password;
    }
}
