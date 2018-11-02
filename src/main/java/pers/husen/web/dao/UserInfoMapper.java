package pers.husen.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.husen.web.bean.vo.UserInfoVo;

import java.util.List;

public interface UserInfoMapper {

    void addUserInfo(@Param("userInfoVo") UserInfoVo userInfoVo);

    void deleteUserInfo(@Param("userInfoVo") UserInfoVo userInfoVo);

    void updateUserInfo(@Param("userInfoVo") UserInfoVo userInfoVo);

    List<UserInfoVo> listAll();

    UserInfoVo getById(@Param("userId") Integer userId);
}
