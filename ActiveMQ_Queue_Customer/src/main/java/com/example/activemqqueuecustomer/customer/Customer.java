package com.example.activemqqueuecustomer.customer;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") //发送方邮箱
    private String toEmail;


    @JmsListener(destination = "${queue}")     //监听的队列，配置文件中的queue属性
    public void receive(String msg){
        if(StringUtils.isEmpty(msg)){
            return;
        }
        System.out.println("收到生产者消息："+msg);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String userName = jsonObject.getString("userName");
        String eamil = jsonObject.getString("eamil");

        //发送邮件
        sendEmail(userName,toEmail);
    }

    /**
     * 发送简单邮件
     * @param userName  用户名，用户封装邮件内容
     * @param toEmail 通过哪个邮箱发送
     * @return
     */
    public String sendEmail(String userName,String toEmail){
        //发送消息对象
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom(toEmail);
        //接收者
        message.setTo(toEmail);
        //主题(标题)
        message.setSubject("尊敬的"+userName);
        //内容
        message.setText("欢迎使用您使用本公司的产品！");  //发送html，发送html格式源码
        javaMailSender.send(message);
        System.out.println("邮件发送完成："+ JSONObject.toJSONString(message));
        return  "send success!";
    }
}
