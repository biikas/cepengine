package com.esper.cepengine;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Configure Kafka properties
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Create AdminClient
        try (AdminClient adminClient = AdminClient.create(properties)) {
            // Create a new topic
            NewTopic newTopic = new NewTopic("my-topic", 3, (short) 1); // Topic name, partition count, replication factor

            // Add any additional configurations if needed
            // newTopic.configs(Collections.singletonMap("cleanup.policy", "compact"));

            // Create the topic
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
            System.out.println("Topic created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
