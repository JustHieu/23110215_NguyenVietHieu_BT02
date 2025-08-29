package vn.hieunguyen.utils;

import java.io.InputStream;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtil {
    private static final Properties props = new Properties();
    private static final String user;
    private static final String pass;

    static {
        try (InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties")) {
            props.load(in);
        } catch (Exception e) { e.printStackTrace(); }
        user = props.getProperty("mail.user");
        pass = props.getProperty("mail.password");
    }

    public static void send(String to, String subject, String content) throws MessagingException {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(user));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setContent(content, "text/html; charset=UTF-8");

        Transport.send(msg);
    }
}
