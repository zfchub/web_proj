package pers.husen.web.common.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailSendUtil {

    private static String MAIL_SMTP_AUTH = "true";

    private static String MAIL_SMTP_HOST = "smtp.sina.com";

    private static String MAIL_SMTP_PORT = "25";

    private static String SINA_USER_NAME = "zhoufangchaoyx@sina.com";

    private static String SINA_USER_PASSWORD = "zfc141309";

    public static Session initSession() {
        Properties properties = new Properties();

        // SMTP服务器是否需要用户认证，默认为false
        properties.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        // SMTP服务器地址
        properties.put("mail.smtp.host", MAIL_SMTP_HOST);
        // SMTP服务器端口号
        properties.put("mail.smtp.port", MAIL_SMTP_PORT);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SINA_USER_NAME, SINA_USER_PASSWORD);
            }
        });

        return session;
    }
}
