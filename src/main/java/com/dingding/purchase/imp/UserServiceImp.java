package com.dingding.purchase.imp;

import com.dingding.purchase.Enum.SexEnum;
import com.dingding.purchase.mapper.UsersMapper;
import com.dingding.purchase.pojo.Users;
import com.dingding.purchase.pojo.bo.CenterUserBO;
import com.dingding.purchase.pojo.bo.UserBO;
import com.dingding.purchase.service.UserService;
import com.dingding.purchase.uitls.MD5Uitls;
import com.dingding.purchase.uitls.TimeUtils;
import lombok.SneakyThrows;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    private UsersMapper usersMapper;

    private static final String USER_FACE = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg";

    //判断是否存在用户名
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean isExistUserName(String userName) {
        Example example = new Example(Users.class);
        Example.Criteria userCriteria = example.createCriteria();
        userCriteria.andEqualTo("username", userName);
        Users result = usersMapper.selectOneByExample(example);
        return result != null;
    }

    //注册用户
    @Transactional(propagation = Propagation.SUPPORTS)
    @SneakyThrows
    @Override
    public Users createUser(UserBO userBO) {
        Users user = new Users();
        user.setUsername(userBO.getUsername());
        user.setPassword(MD5Uitls.genMD5Str(userBO.getPassword()));
        user.setnickname(userBO.getUsername());
        user.setFace(USER_FACE);
        user.setBirthday(TimeUtils.stirngToDate("1970-01-01"));
        user.setSex(SexEnum.SECRET.type);
        user.setId(Sid.nextShort());
        user.setCreatedTime(TimeUtils.unixTimestampToDate());
        user.setUpdatedTime(TimeUtils.unixTimestampToDate());
        usersMapper.insert(user);
        return user;
    }

    @Override
    public Users queryUserForLogin(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria userCriteria = example.createCriteria();
        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);
        return usersMapper.selectOneByExample(example);
    }

    @Override
    public Users queryUserForCenter(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;
    }

    @Override
    public Users updateUserForCenter(String userId, CenterUserBO centerUserBO) {
        Users users = new Users();
        BeanUtils.copyProperties(centerUserBO, users);
        users.setId(userId);
        users.setUpdatedTime(
                new Date());
        usersMapper.updateByPrimaryKeySelective(users);
        return queryUserForCenter(userId);
    }

    @Override
    public void updateUserFace(String userId, String faceUrl) {
        Users users = new Users();
        users.setId(userId);
        users.setFace(faceUrl);
        users.setUpdatedTime(
                new Date());
        usersMapper.updateByPrimaryKeySelective(users);
    }
}