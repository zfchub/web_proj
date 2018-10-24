package pers.husen.web.controller.message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.old_service.MessageAreaSvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/message.hms")
public class MessageController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) Integer messageId,
                      @RequestParam(required = false) String newMessage) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String requestType = type;
        /** 如果是获取所有留言, 目前留言是查询所有，然后到前端分页  */
        String queryAllMsg = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
        if(queryAllMsg.equals(requestType)) {
            MessageAreaSvc mSvc = new MessageAreaSvc();
            ArrayList<MessageAreaVo> mVos = mSvc.queryAllMessageArea(messageId);

            String json = JSONArray.fromObject(mVos).toString();
            out.println(json);

            return;
        }
        /** 如果是请求上传新留言 */
        String createOneMsg = RequestConstants.REQUEST_TYPE_CREATE + RequestConstants.MODE_ONE;
        if(createOneMsg.equals(requestType)) {
            JSONObject jsonObject = JSONObject.fromObject(newMessage);
            MessageAreaVo mVo  = TypeConvertHelper.jsonObj2MessageBean(jsonObject);

            //插入数据
            MessageAreaSvc mSvc = new MessageAreaSvc();
            messageId = mSvc.insertMessageNew(mVo);

            JSONObject jsObject = new JSONObject();
            jsObject.put("result", 1);
            jsObject.put("messageId", messageId);

            out.println(jsObject);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) Integer messageId,
                       @RequestParam(required = false) String newMessage) throws ServletException, IOException {
        doGet(request, response, type, messageId, newMessage);
    }
}
