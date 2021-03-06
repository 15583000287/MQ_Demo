package pubsub;

import com.rabbitmq.client.*;
import factory.MqConnectionUtils;

import java.io.IOException;

/**
 *短信消费者
 */
public class ConsumerSmsFanout {
    //交换机名称
    private static final  String DESTINATION_NAME = "my_fanout_destination";
    //队列名称
    private static final  String SMS_QUEUE = "sms_queue";


    public static void main(String[] args) throws Exception {
        System.out.println("短息消费者启动...");
        //1. 建立MQ链接
        Connection connection = MqConnectionUtils.newConnection();
        //2. 创建通道
        Channel channel = connection.createChannel();
        //3. 消费者声明队列
        channel.queueDeclare(SMS_QUEUE,false,false,false,null);
        //4. 消费者队列绑定交换机
        channel.queueBind(SMS_QUEUE,DESTINATION_NAME,"");
        //5. 消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("短信消费者获取生产消息："+msg);
            }
        };
        channel.basicConsume(SMS_QUEUE,defaultConsumer);
    }
}
