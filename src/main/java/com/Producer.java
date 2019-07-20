package com;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static final String exchangeName = "exchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchangeName, "fanout");
            String message = randomLine();
            channel.basicPublish(exchangeName, "", null, message.getBytes("UTF-8"));
            System.out.println("Sent '" + message + "'");
        }

    }

    public static String randomLine() {
        int random = (int) ((double) Math.random() * 10);
        String line = "abcdefghi";
        return line.substring(random);
    }
}
