package com.dingding.purchase.service;

import com.dingding.purchase.pojo.bo.CenterUserBO;
import com.dingding.purchase.pojo.bo.UserBO;
import com.dingding.purchase.pojo.Users;

public interface UserService {

    public boolean isExistUserName(String userName);

    public Users createUser(UserBO userBO);

    public Users queryUserForLogin(String username, String password);

    public Users queryUserForCenter(String userId);

    public Users updateUserForCenter(String userId, CenterUserBO centerUserBO);

    public void updateUserFace(String userId,String faceUrl);
}