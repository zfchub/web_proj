package pers.husen.web.controller.image;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.husen.web.bean.po.ImageUploadPo;
import pers.husen.web.bean.vo.ImageUploadVo;
import pers.husen.web.common.handler.ImageUploadHandler;
import pers.husen.web.common.helper.DateFormatHelper;
import pers.husen.web.old_service.ImageUploadSvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@RequestMapping("/imageUpload.hms")
public class ImageUploadController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        ImageUploadHandler imageUploadHandler = new ImageUploadHandler();
        String imageDatePath = DateFormatHelper.dateNumberFormat();
        String fileName = imageUploadHandler.imageUploadHandler(request, imageDatePath);

        //上传结果
        ImageUploadPo iPo = new ImageUploadPo();

        // 不为null则上传成功
        if (fileName != null) {
            //上传成功为1
            iPo.setSuccess(1);
            //图片带网址完整链接
            StringBuffer resquestUrl = request.getRequestURL();
            int serverPathStart = resquestUrl.lastIndexOf("/");
            String imageFullLink =  resquestUrl.substring(0, serverPathStart) + "/imageDownload.hms?imageUrl=" + imageDatePath + "/" + fileName;

            ImageUploadVo iVo = new ImageUploadVo();
            iVo.setImageName(imageDatePath + fileName);
            iVo.setImageUrl(imageFullLink);
            iVo.setImageUploadDate(new Date());
            iVo.setImageType(1);
            iVo.setImageDownloadCount(0);

            ImageUploadSvc iSvc = new ImageUploadSvc();
            iSvc.insertImageUpload(iVo);

            iPo.setUrl(imageFullLink);
            iPo.setMessage("上传成功");
        }else {
            iPo.setSuccess(0);
            iPo.setMessage("上传失败");
        }

        JSONObject jsonObject = JSONObject.fromObject(iPo);
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
