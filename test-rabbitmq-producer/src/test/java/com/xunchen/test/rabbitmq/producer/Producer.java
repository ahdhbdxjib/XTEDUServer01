package com.xunchen.test.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String QUEUE = "helloworld";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            //使用默认的虚拟机，虚拟机相当于一个独立的mq服务器
            connectionFactory.setVirtualHost("/");
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        /**
         * 声明队列，如果Rabbit中没有此队列将自动创建
         * param1:队列名称
         * param2:是否持久化
         * param3:队列是否独占此连接
         * param4:队列不再使用时是否自动删除此队列
         * param5:队列参数
         */
            channel.queueDeclare(QUEUE, true, false, false, null);
            String message = "helloworld小明"+System.currentTimeMillis();
            /**
             * 消息发布方法
             * param1：Exchange的名称，如果没有指定，则使用Default Exchange
             * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
             * param3:消息包含的属性
             * param4：消息体
             */

            channel.basicPublish("",QUEUE,null,message.getBytes());
            System.out.println("Producer发布了消息"+message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(channel != null)
            {
                channel.close();
            }
            if(connection != null)
            {
                connection.close();
            }

        }

    }
}
