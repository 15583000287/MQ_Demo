package simplequque_p2p;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import factory.MqConnectionUtils;


/**
 * 简单队列模式(点对点)生产者
 */
public class Producer {
    public static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception{
        //1.获取连接
        Connection connection = MqConnectionUtils.newConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
       for (int i = 1;i <= 10; i++){
           String msg = "今天是个好日子"+i;
           System.out.println("生产者发送msg："+msg);
           //4.发送消息
           channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
       }
        //5.释放资源
        channel.close();
        connection.close();
    }
}
