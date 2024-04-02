package com.example.lrb.service.impl;

import com.example.lrb.common.exception.QuizGameException;
import com.example.lrb.common.utils.JwtUtils;
import com.example.lrb.common.utils.ResponseJsonUtils;
import com.example.lrb.dao.MerchantDao;
import com.example.lrb.dao.UserDao;
import com.example.lrb.enums.RoleEnums;
import com.example.lrb.pojo.Merchant;
import com.example.lrb.vo.JsonResult;
import com.example.lrb.pojo.User;
import com.example.lrb.service.UserService;
import com.example.lrb.vo.RegisterUserVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@Data
public class UserServiceImpl implements UserService {

    @Value("${server.port:#{8080}}")
    public Integer serverPort;


    private static List<String> avatarList;

    static {
        Resource[] resources;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("/static/avatar/*");
            avatarList = Arrays.stream(resources).map(v -> v.getFilename()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private MerchantDao merchantDao;

    private String getAvatarUrlPrefix() {
        return "http://localhost:" + serverPort + "/avatar/";
    }


    @Override
    public JsonResult<List<User>> list(User query) {
        return null;
    }

    @Override
    public JsonResult<User> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<User> update(User user) {
        return null;
    }

    @Override
    public JsonResult<User> insert(User user) {
        return null;
    }

    @Override
    public JsonResult<User> delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
        return JsonResult.ok("删除成功");
    }

    @Override
    public JsonResult<User> login(User params, HttpServletResponse response) {
        if (params == null
                || StringUtils.isBlank(params.getName())
                || StringUtils.isBlank(params.getPassword())
                || params.getRole() == null
        ) {
            return JsonResult.badRequest("登陆失败，缺少参数");
        }

        // 查找该用户
        User user = userDao.selectOne(User.builder().name(params.getName()).build());
        if (user == null) {
            // 新增用户、商户（管理员不可新增）
//            if (params.getRole().equals(RoleEnums.ADMIN)) {
//                return JsonResult.badRequest("登陆失败，暂无此管理员");
//            }
//
//            if (avatarList.isEmpty()) {
//                return JsonResult.badRequest("登陆失败，无法设置头像");
//            }
//
//            user = User.builder()
//                    .name(params.getName())
//                    .password(params.getPassword())
//                    .role(params.getRole())
//                    .avatar(getAvatarUrlPrefix() + avatarList.get((int) (Math.random() * avatarList.size())))
//                    .build();
//
//            int i = userDao.insertSelective(user);
//            if (i <= 0) {
//                throw new QuizGameException(JsonResult.badRequest("登陆失败，请重试"));
//            }
            return JsonResult.badRequest("用户不存在");
        } else if (!user.getPassword().equals(params.getPassword())) {
            return JsonResult.badRequest("登陆失败，密码错误");
        }

        // 生成token，并返回
        String token = JwtUtils.generateToken(user.getId().toString(), user);
        ResponseJsonUtils.setToken(response, token);
        return JsonResult.ok("登陆成功", user);
    }


    @Override
    public JsonResult<User> getUserInfo(Integer id) {
        return JsonResult.ok(userDao.selectByPrimaryKey(id));
    }

    @Override
    public JsonResult<User> updatePassword(Integer id, String oldPassword, String newPassword) {
        User user = userDao.selectByPrimaryKey(id);
        if (user == null) {
            return JsonResult.badRequest("修改失败");
        }
        if (!user.getPassword().equals(oldPassword)) {
            return JsonResult.badRequest("修改失败，旧密码不正确");
        }
        user.setPassword(newPassword);
        userDao.updateByPrimaryKeySelective(user);
        return JsonResult.ok("修改成功", user);
    }

    @Override
    public JsonResult<List<User>> getUserAll(String name) {
        List<User> userList = userDao.queryUserAll(name);
        return JsonResult.ok(userList);
    }

    @Override
    public JsonResult<Object> register(RegisterUserVo registerUser) {

        if (StringUtils.isBlank(registerUser.getName())) {
            return JsonResult.badRequest("请输入用户名");
        }
        if (StringUtils.isBlank(registerUser.getPassword())) {
            return JsonResult.badRequest("请输入密码");
        }
        if (StringUtils.isBlank(registerUser.getAge())) {
            return JsonResult.badRequest("请输入年龄");
        }
        if (StringUtils.isBlank(registerUser.getSex())) {
            return JsonResult.badRequest("请选择性别");
        }
        if (StringUtils.isBlank(registerUser.getMobile())) {
            return JsonResult.badRequest("请输入手机号");
        }
        if (StringUtils.isBlank(registerUser.getLocation())) {
            return JsonResult.badRequest("请选择当前位置");
        }
        if (StringUtils.isBlank(registerUser.getHometown())) {
            return JsonResult.badRequest("请选择家乡位置");
        }

        if (RoleEnums.MERCHANT.equals(registerUser.getRegisterType())) {
            if (StringUtils.isBlank(registerUser.getIdcard())) {
                return JsonResult.badRequest("请输入身份证号");
            }
            if (StringUtils.isBlank(registerUser.getAddress())) {
                return JsonResult.badRequest("请选择商铺地址");
            }
            if (StringUtils.isBlank(registerUser.getStoreName())) {
                return JsonResult.badRequest("请输入店铺名称");
            }
            if (StringUtils.isBlank(registerUser.getProducts())) {
                return JsonResult.badRequest("请输入经营产品内容");
            }

        }

        // 判断用户名是否已注册
        User user = userDao.selectOne(User.builder().name(registerUser.getName()).build());
        if (user != null) {
            return JsonResult.badRequest("用户名已存在");
        }

        // 添加用户
        user = new User();
        BeanUtils.copyProperties(registerUser, user);
        user.setRole(registerUser.getRegisterType());
        user.setAvatar(getAvatarUrlPrefix() + avatarList.get((int) (Math.random() * avatarList.size())));
        userDao.insertSelective(user);

        if (RoleEnums.MERCHANT.equals(registerUser.getRegisterType())) {
            // 添加商户
            Merchant merchant = new Merchant();
            merchant.setUserId(user.getId());
            BeanUtils.copyProperties(registerUser, merchant);
            merchantDao.insertSelective(merchant);
        }

        return JsonResult.ok("注册成功");
    }

}