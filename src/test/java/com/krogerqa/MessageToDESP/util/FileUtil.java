package com.krogerqa.MessageToDESP.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.Map;
import java.util.Properties;

public class FileUtil {
    private static final Logger log = LogManager.getLogger(FileUtil.class);
    static java.util.Properties properties = new java.util.Properties();

    public static void setProperty(String key, String value){
        properties.put(key, value);
    }

    public static void writePropertyFile(Properties properties, String existingFilePath) {
        String text = "";
        for (Map.Entry entry : properties.entrySet()) {
            text += entry.getKey() + "=" + entry.getValue() + "\n";
        }
        writeFile(text, existingFilePath);
    }

    public static void writeFile(String text, String existingFilePath) {
        try {
            FileWriter fWriter = new FileWriter(existingFilePath);
            fWriter.write(text);
            log.info("data has been written into " + existingFilePath);
            fWriter.close();
        } catch (IOException e) {
            log.info("file write operation failed.");
            log.warn(e.getMessage());
        }
    }

    public static Void writeCredentialsIntoPropertiesFile(){
        final String PROPERTIES_FILEPATH = "src/test/resources/plain.properties";
        setProperty("sasl.jaas.config", System.getProperty("KAFKA_CREDENTIALS"));
        String environment = System.getProperty("karate.env");

        if (environment.equalsIgnoreCase("stage_central") || environment.equalsIgnoreCase("stage_east")) {
            setProperty("bootstrap.servers", System.getProperty("STAGE_BOOTSTRAP_SERVERS"));
            setProperty("schema.registry.url", System.getProperty("STAGE_SCHEMA_REGISTRY_URL"));
        }
        if (System.getProperty("karate.env").equalsIgnoreCase("dev") || environment.equalsIgnoreCase("test")) {
            setProperty("bootstrap.servers", System.getProperty("TEST_BOOTSTRAP_SERVERS"));
            setProperty("schema.registry.url", System.getProperty("TEST_SCHEMA_REGISTRY_URL"));
        }
        setProperty("sasl.mechanism", "PLAIN");
        setProperty("security.protocol", "SASL_SSL");
        setProperty("ssl.truststore.location", "src/test/resources/kafka.client.truststore.jks");
        setProperty("ssl.truststore.password", "changeit");
        setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        setProperty("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        FileUtil.writePropertyFile(properties, PROPERTIES_FILEPATH);
        return null;
    }

}