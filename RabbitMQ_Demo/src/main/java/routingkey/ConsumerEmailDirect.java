package routingkey;

import com.rabbitmq.client.*;
import factory.MqConnectionUtils;

import java.io.IOException;

/**
 *邮件消费者
 */
public class ConsumerEmailDirect {
    //交换机名称
    private static final  String Direct_NAME = "direct_exchange";
    //队列名称
    private static final  String EMAIL_QUEUE = "email_queue";


    public static void main(String[] args) throws Exception {
        System.out.println("邮件消费者启动...");
        //1. 建立MQ链接
        Connection connection = MqConnectionUtils.newConnection();
        //2. 创建通道
        Channel channel = connection.createChannel();
        //3. 消费者声明队列
        channel.queueDeclare(EMAIL_QUEUE,false,false,false,null);
        //4. 消费者队列 路由键绑定交换机(可以绑定多个路由键)
        channel.queueBind(EMAIL_QUEUE,Direct_NAME,"email");
        channel.queueBind(EMAIL_QUEUE,Direct_NAME,"sms");
        //5. 消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("邮件消费者获取生产消息："+msg);
            }
        };
        //true 应答
        channel.basicConsume(EMAIL_QUEUE,true,defaultConsumer);
    }
}
