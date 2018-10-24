package pers.husen.web.controller.userinfo;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.Md5EncryptHelper;
import pers.husen.web.old_service.UserInfoSvc;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/userInfo.hms")
public class UserInfoController {

    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String userName,
                      @RequestParam(required = false) String password,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String formdata,
                      @RequestParam(required = false) String email) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        UserInfoSvc uSvc = new UserInfoSvc();

        String operationType = type;
        //密码加盐再加密
        password = Md5EncryptHelper.getMD5Code(password + "[" + userName + "]");

        //如果操作类型为null，直接返回
        if (operationType == null) {
            out.println(-1);
            return;
        }

        /** 如果请求为查询用户信息 */
        String queryUserInfo = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_USER_INFO;
        if (queryUserInfo.equals(operationType)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            UserInfoVo uVo = uSvc.queryUserInfoByName(userName);
            out.println(JSONObject.fromObject(uVo));

            return;
        }
        /** 如果请求为修改个人信息 */
        String modifyUserInfo = RequestConstants.REQUEST_TYPE_MODIFY + RequestConstants.MODE_USER_INFO;
        if (modifyUserInfo.equals(operationType)) {
            String formData = formdata;
            JSONObject jsonObject = JSONObject.fromObject(formData);
            UserInfoVo uVo = (UserInfoVo) JSONObject.toBean(jsonObject, UserInfoVo.class);

            int updateResult = uSvc.updateUserInfoById(uVo);
            out.println(updateResult);

            return;
        }
        /** 如果为请求修改密码 */
        String modifyPwd = RequestConstants.REQUEST_TYPE_MODIFY + RequestConstants.MODE_PASSWORD;
        if (modifyPwd.equals(operationType)) {
            UserInfoVo uVo = new UserInfoVo();
            uVo.setUserName(userName);
            uVo.setUserPassword(password);

            int modifyResult = uSvc.updateUserPwdByName(uVo);
            out.println(modifyResult);

            return;
        }
        /** 如果是注册新用户,插入用户信息到数据库 */
        String createUserInfo = RequestConstants.REQUEST_TYPE_CREATE + RequestConstants.MODE_USER_INFO;
        if (createUserInfo.equals(operationType)) {
            UserInfoVo uVo = new UserInfoVo();
            uVo.setUserName(userName);
            uVo.setUserPassword(password);
            uVo.setUserEmail(email);

            out.println(uSvc.insertUserInfo(uVo));

            return;
        }
        /** 如果为验证密码,登录或者修改密码验证 */
        if (operationType.indexOf(RequestConstants.REQUEST_TYPE_AUTH) != -1) {
            String queryPwd = uSvc.queryPasswordByUserName(userName);
            if (password.equals(queryPwd)) {
                // 如果为登录，设置cookie
                if (operationType.indexOf(RequestConstants.MODE_LOGIN) != -1) {
                    Cookie cookie = new Cookie("username", userName);
                    cookie.setPath("/");
                    // cookie.setMaxAge(60*60); 不设置，关闭浏览器即失效
                    response.addCookie(cookie);

                    out.println(1);

                    return;
                }
                // 如果是修改密码验证
                if (operationType.indexOf(RequestConstants.MODE_PASSWORD) != -1) {
                    out.println(1);

                    return;
                }

            } else {
                out.println(0);
            }

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String userName,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) String formdata,
                       @RequestParam(required = false) String email)
            throws ServletException, IOException {
        doGet(request, response, userName, password, type, formdata, email);
    }
}
