package pers.husen.web.controller.download;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.common.handler.FileUploadHandler;
import pers.husen.web.old_service.FileDownloadSvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/fileUpload.hms")
public class FileUploadController {

    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileUploadHandler fileUploadHandler = new FileUploadHandler();
        String fileName = fileUploadHandler.fileUploadHandler(request);

        //不为null则上传成功
        if(fileName != null) {
            FileDownloadVo fVo = new FileDownloadVo();
            fVo.setFileName(fileName);
            fVo.setFileUrl(fileName);
            fVo.setFileUploadDate(new Date());
            fVo.setFileDownloadCount(0);

            FileDownloadSvc fSvc = new FileDownloadSvc();
            fSvc.insertFileDownload(fVo);
        }

        response.sendRedirect("/personal_center/mycenter.jsp");
		/*PrintWriter out = response.getWriter();
		out.println(insertResult);*/
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
