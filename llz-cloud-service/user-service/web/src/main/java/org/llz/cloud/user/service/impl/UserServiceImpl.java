package org.llz.cloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.llz.cloud.user.pojo.User;
import org.llz.cloud.user.service.UserService;
import org.llz.cloud.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【llz_user】的数据库操作Service实现
* @createDate 2022-05-20 10:39:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




