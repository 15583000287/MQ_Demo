package workqueue;

import com.rabbitmq.client.*;
import factory.MqConnectionUtils;

import java.io.IOException;


/**
 * 工作队列模式消费者
 */
public class Customer2 {
    public static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception{
        System.out.println("消费者2启动....");
        //1.获取连接
        Connection connection = MqConnectionUtils.newConnection();
        //2.获取通道
        final Channel channel = connection.createChannel();
        //// 保证一次只分发一次 限制发送给同一个消费者 不得超过一条消息
        channel.basicQos(1);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //3.监听队列
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgResult = new String(body,"utf-8");
                System.out.println("消费者获取到msg: "+msgResult);
                try {
                    //阻塞500ms  模拟效率低
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //手动应答
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };

        //4.设置应答模式  true表示自动应答模式   false表示为手动应答
//        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer); //手动应答
    }
}
