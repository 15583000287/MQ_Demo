package routingkey;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import factory.MqConnectionUtils;

/**
 * 路由模式（RoutingKey） 生产者
 * 交换机类型：扇形交换机 direct
 */
public class Producer {
    //交换机名称
    private static final  String Direct_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception {
        System.out.println("路由模式生产者启动...");
        //1. 建立MQ链接
        Connection connection = MqConnectionUtils.newConnection();
        //2. 创建通道
        Channel channel = connection.createChannel();
        //3. 生产者绑定交换机 args1：交换机名称  args2: 交换机类型
        channel.exchangeDeclare(Direct_NAME,"direct");
        //路由键
        String routingKey = "sms";
        //4. 创建要发送的消息
        String msg = "my_fanout_destination_msg!!!  "+routingKey;
        //5. 发送消息
        channel.basicPublish(Direct_NAME,routingKey,null,msg.getBytes());
        System.out.println("发送消息："+msg);
        //6. 关闭通道和连接
        channel.close();
        connection.close();

        //注意：如果消费者没有绑定交换机和队列，则消息会丢失
    }
}
