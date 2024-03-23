package org.example.mmall.service;

import org.example.mmall.response.api.CommonResult;
import org.example.mmall.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mmall.request.LoginParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
public interface UserService extends IService<User> {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    CommonResult login(LoginParam loginParam);

    CommonResult findUserByToken(String token);
}
