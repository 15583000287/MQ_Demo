package factory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ连接工具类
 */
public class MqConnectionUtils {

    public static Connection  newConnection() throws  Exception{
        //1. 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2. 设置服务器地址
        factory.setHost("139.196.94.253");
        //3. 设置协议端口号
        factory.setPort(5672);
        //4. 设置Virtual Host
        factory.setVirtualHost("/admin_vhost");
        //5. 设置用户名
        factory.setUsername("admin");
        //6. 设置密码
        factory.setPassword("admin");
        //7. 创建新的连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
