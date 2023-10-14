package com.myshop.site;

import com.myshop.site.setting.EmailSettingBag;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class Utility {
    public static String getSiteURL(HttpServletRequest servletRequest) {
        String url = servletRequest.getRequestURL().toString();
        return url.replace(servletRequest.getServletPath(),"");
    }

    public static JavaMailSenderImpl prepareMailSender(EmailSettingBag settingBag) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(settingBag.getHost());
        mailSender.setPort(settingBag.getPort());
        mailSender.setUsername(settingBag.getUsername());
        mailSender.setPassword(settingBag.getPassword());

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth",settingBag.getSmtpAuth());
        properties.setProperty("mail.smtp.starttls.enable", settingBag.getSmtpSecured());
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
