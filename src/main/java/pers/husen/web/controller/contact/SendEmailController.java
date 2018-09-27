package pers.husen.web.controller.contact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.common.helper.SendEmailHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/sendEmail.hms")
public class SendEmailController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String contactName,
                      @RequestParam(required = false) String contactEmail,
                      @RequestParam(required = false) String contactPhone,
                      @RequestParam(required = false) String contactContent) throws IOException {
        request.setCharacterEncoding("UTF-8");
        SendEmailHelper sendEmail = new SendEmailHelper();

        int result = sendEmail.sendEmail2Admin(contactName, contactEmail, contactPhone, contactContent);

        PrintWriter out = response.getWriter();
        out.println(result);
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String contactName,
                       @RequestParam(required = false) String contactEmail,
                       @RequestParam(required = false) String contactPhone,
                       @RequestParam(required = false) String contactContent) throws IOException {
        doGet(request, response, contactName, contactEmail, contactPhone, contactContent);
    }

}
