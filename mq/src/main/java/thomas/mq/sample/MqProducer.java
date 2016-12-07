package thomas.mq.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by liubo on 16/7/22.
 */
public class MqProducer {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(MqConfig.HOST);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        StringBuilder message = new StringBuilder("Hello World ");
        for (int i = 0; i < 10; i++) {
            message.append(i);
            channel.basicPublish("",QUEUE_NAME,null,message.toString().getBytes());
            System.out.println("[x] send {"+message+"}");
        }


        channel.close();
        connection.close();
    }
}
