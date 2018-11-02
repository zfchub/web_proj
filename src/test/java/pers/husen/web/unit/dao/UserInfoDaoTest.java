package pers.husen.web.unit.dao;


import org.springframework.beans.factory.annotation.Autowired;
import pers.husen.web.dao.UserInfoMapper;
import pers.husen.web.unit.TestBase;

public class UserInfoDaoTest extends TestBase {

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;



}
