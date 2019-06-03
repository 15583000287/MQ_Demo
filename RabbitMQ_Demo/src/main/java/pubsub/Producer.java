package pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import factory.MqConnectionUtils;

/**
 * pub/sub模式 生产者
 * 交换机类型：扇形交换机 fanout
 * 注意：交换机若没有绑定的队列，则消息会丢失(交换机不做缓存，只做转发)
 *      只要队列创建了，并与交换机绑定，队列就有缓存功能。
 */
public class Producer {
    //交换机名称
    private static final  String DESTINATION_NAME = "my_fanout_destination";

    public static void main(String[] args) throws Exception {
        System.out.println("生产者启动...");
        //1. 建立MQ链接
        Connection connection = MqConnectionUtils.newConnection();
        //2. 创建通道
        Channel channel = connection.createChannel();
        //3. 生产者绑定交换机 args1：交换机名称  args2: 交换机类型
        channel.exchangeDeclare(DESTINATION_NAME,"fanout");
        //4. 创建要发送的消息
        String msg = "my_fanout_destination_msg!!!";
        System.out.println("发送消息："+msg);
        //5. 发送消息
        channel.basicPublish(DESTINATION_NAME,"",null,msg.getBytes());
        //6. 关闭通道和连接
        channel.close();
        connection.close();
    }
}
