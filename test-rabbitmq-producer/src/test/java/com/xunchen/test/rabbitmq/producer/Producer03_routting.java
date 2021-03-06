package com.xunchen.test.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer03_routting {

    private static final String QUEUE_INFORM_EMAIL = "queue_info_email";
    private static final String QUEUE_INFORM_SMS = "queue_info_sms";
    private static final String EXCHANGE_ROUTTING_INFORM = "exchange_info-routting";
    private static final String ROUTTING_KEY_EMAIL = "routting_email";
    private static final String ROUTTING_KEY_SMS = "routting_sms";


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
            //声明两个队列

            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
            //声明交换机
            /**
             * 参数明细
             * 1、交换机名称
             * 2、交换机类型，fanout、topic、direct、headers
             */
            channel.exchangeDeclare(EXCHANGE_ROUTTING_INFORM, BuiltinExchangeType.DIRECT);
            //交換机和队列绑定
            /**
             * 参数明细
             * 1、队列名称
             * 2、交换机名称
             * 3、路由key
             */
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTTING_INFORM, ROUTTING_KEY_SMS);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTTING_INFORM, "test");
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTTING_INFORM, ROUTTING_KEY_EMAIL);
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTTING_INFORM, "test");
            /**
             * 消息发布方法
             * param1：Exchange的名称，如果没有指定，则使用Default Exchange
             * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
             * param3:消息包含的属性
             * param4：消息体
             */

           /* //发送给sms
            for (int i = 0; i < 5; i++) {
                String message = "send sms message is :" + i;
                channel.basicPublish(EXCHANGE_ROUTTING_INFORM, ROUTTING_KEY_SMS, null, message.getBytes());
                System.out.println("Producer发布了消息" + message);
            }
            //发送给email
            for (int i = 0; i < 5; i++) {
                String message = "send email message is :" + i;
                channel.basicPublish(EXCHANGE_ROUTTING_INFORM, ROUTTING_KEY_EMAIL, null, message.getBytes());
                System.out.println("Producer发布了消息" + message);
            }*/
            //发送公共的消息
            for (int i = 0; i < 5; i++) {
                String message = "send inform message is :" + i;
                channel.basicPublish(EXCHANGE_ROUTTING_INFORM, "test", null, message.getBytes());
                System.out.println("Producer发布了消息" + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            //先关里面的channel
            if (channel != null) {
                channel.close();
            }
            //再关外面的connection
            if (connection != null) {
                connection.close();
            }

        }

    }
}

