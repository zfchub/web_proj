package pers.husen.web.controller.download;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.handler.FileDownloadHandler;
import pers.husen.web.old_service.FileDownloadSvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/file/download.hms")
public class FileDownloadController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String filename,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) Integer pageSize,
                      @RequestParam(required = false) Integer pageNo) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // 如果是请求下载文件
        if (filename != null) {
            FileDownloadHandler fileDownloadHandler = new FileDownloadHandler();
            fileDownloadHandler.fileDownloadHandler(request, response);

            return;
        }
        PrintWriter out = response.getWriter();
        String requestType = type;
        //如果是查询所有文件的数量
        String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
        if(queryTotalCount.equals(requestType)) {
            FileDownloadSvc fSvc = new FileDownloadSvc();
            int count = fSvc.queryFileTotalCount();

            out.println(count);

            return;
        }
        //如果是查询某一页的文件
        String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
        if(queryOnePage.equals(requestType)) {
            FileDownloadSvc fSvc = new FileDownloadSvc();
            ArrayList<FileDownloadVo> fVos = fSvc.queryFileDownlaodPerPage(pageSize, pageNo);

            String json = JSONArray.fromObject(fVos).toString();
            out.println(json);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String filename,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) Integer pageSize,
                       @RequestParam(required = false) Integer pageNo)
            throws ServletException, IOException {
        doGet(request, response, filename, type, pageSize, pageNo);
    }
}
