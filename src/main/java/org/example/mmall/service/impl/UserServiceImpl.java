package org.example.mmall.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.response.api.ResultCode;
import org.example.mmall.model.User;
import org.example.mmall.mapper.UserMapper;
import org.example.mmall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mmall.util.JwtTokenUtil;
import org.example.mmall.response.UserVO;
import org.example.mmall.request.LoginParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private JwtTokenUtil jwtToken;

    @Value("${salt}")
    private String salt;
    @Override
    public CommonResult login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法
         * 2. 根据手机号和密码查询是否存在
         * 3. 不存在创建
         * 4. 存在就返回token
         */
        String phone = loginParam.getPhone();
        String password = loginParam.getPassword();
        if (phone.length() != 11 && !StringUtils.isNumeric(phone))
            return CommonResult.failed(ResultCode.PHONE_NUMBER_ERROR);
        password = DigestUtil.md5Hex( password + salt);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone",phone);
        User one = this.getOne(wrapper);
        if (one == null) {
            User newUser = new User(phone,password);
            boolean save = this.save(newUser);
            if (!save) return CommonResult.failed(ResultCode.FAILED,"注册失败");
            //插入数据返回
            one = this.getOne(wrapper);
            String token = jwtToken.getToken(one);
            return CommonResult.success(token);
        } else if (!password.equals(one.getPassword())) {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
        String token = jwtToken.getToken(one);
        return CommonResult.success(token);
    }

    @Override
    public CommonResult findUserByToken(String token) {
//        if (StringUtils.isBlank(token)){
//            return CommonResult.failed(ResultCode.TOKEN_ERROR);
//        }
        UserVO userVO = jwtToken.decodeUser(token);
        User byId = this.getById(userVO.getId());
        if (userVO == null || byId == null || !jwtToken.validateToken(token,byId))
            return CommonResult.failed(ResultCode.TOKEN_ERROR);
        return CommonResult.success(userVO);
    }
}
