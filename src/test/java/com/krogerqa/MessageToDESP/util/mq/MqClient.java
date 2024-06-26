package com.krogerqa.MessageToDESP.util.mq;

import com.ibm.mq.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ibm.mq.MQEnvironment.properties;
import static com.ibm.mq.constants.CMQC.*;

public class MqClient {

    public MqClient() {
    }

    //Get Current date
    public String getCurrentDate(String dateFormat) {
        //getting current date and time using Date class
        DateFormat dateFormatObject = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        return dateFormatObject.format(date);
    }
//
    public MQQueueManager mqConnect(String mqManagerName, String hostName, String channel, int port, String user, String password) {
        MQQueueManager qManager = null;
        //Set MQ connection credentials to MQ Envorinment.
        MQEnvironment.hostname = hostName;
        MQEnvironment.channel = channel;
        MQEnvironment.port = port;
        if (!user.equalsIgnoreCase("")) {
            MQEnvironment.userID = user;
        }
        if (!password.equalsIgnoreCase("")) {
            MQEnvironment.password = password;
        }

        //set transport properties.
        properties.put(TRANSPORT_PROPERTY, TRANSPORT_MQSERIES_CLIENT);

        try {
            //initialize MQ manager.
            qManager = new MQQueueManager(mqManagerName);
        } catch (MQException e) {
            System.out.println(e.toString());
        }
        return qManager;
    }

    public  void messagePut(MQQueueManager qManager, String queueName, String msg) {

        int openOptions = MQOO_INPUT_AS_Q_DEF | MQOO_OUTPUT;
        try {
            MQQueue defaultLocalQueue;
            defaultLocalQueue = qManager.accessQueue(queueName, openOptions);

            MQMessage putMessage = new MQMessage();
            putMessage.format = MQFMT_STRING;
            //putMessage.writeUTF(msg);
            putMessage.writeString(msg);

            //specify the message options...
            MQPutMessageOptions pmo = new MQPutMessageOptions();

            // put the message on the queue
            defaultLocalQueue.put(putMessage, pmo);


        } catch (MQException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void clearQueue(MQQueueManager qManager, String queueName) {

        int openOptions = MQOO_FAIL_IF_QUIESCING | MQOO_INPUT_SHARED | MQOO_BROWSE;

        int counter = 0;
        MQQueue defaultLocalQueue = null;
        try {

            defaultLocalQueue = qManager.accessQueue(queueName, openOptions);
            while (true) {
                MQMessage getMessages = new MQMessage();
                MQGetMessageOptions gmo = new MQGetMessageOptions();
                defaultLocalQueue.get(getMessages, gmo);
                counter++;
            }
        } catch (MQException e) {
            //e.printStackTrace();
            if (e.reasonCode == MQRC_NO_MSG_AVAILABLE) {
                System.out.println("Cleared " + counter + " messages from " + queueName);
            }
        } finally {
            try {
                //System.out.println("Closing");
                if (defaultLocalQueue != null) {
                    defaultLocalQueue.close();
                }
            } catch (MQException e) {
                e.printStackTrace();
            }
        }

    }

    public  String messageGet(MQQueueManager qManager, String queueName) {

        int openOptions = MQOO_INPUT_AS_Q_DEF | MQOO_OUTPUT;
        String returnedMsg = "";
        try {
            MQQueue defaultLocalQueue;
            defaultLocalQueue = qManager.accessQueue(queueName, openOptions);

            //get message from MQ.
            MQMessage getMessages = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            defaultLocalQueue.get(getMessages, gmo);
            byte[] b = new byte[getMessages.getMessageLength()];
            getMessages.readFully(b);
            returnedMsg = new String(b);
        } catch (MQException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedMsg;
    }
//
//
//    /**
//     * Method to Put message into MQTopic
//     *
//     * @param mqManager - MQQueueManager parameter to mention MQ queueManager     *
//     * @param topicName - String parameter to mention the topic name
//     * @param msg       - String - Message to be posted
//     */
//    public  void messagePutIntoTopic(MQQueueManager mqManager, String topicName, String msg)
//    {
//        int openOutputOptions = MQOO_OUTPUT + CMQC.MQOO_FAIL_IF_QUIESCING;//;MQOO_INPUT_AS_Q_DEF ;//
//        MQPutMessageOptions pmo = new MQPutMessageOptions();
//        MQTopic publisher;
//        MQMessage mqMsg;
//        String messageToPost = msg;
//        try {
//
//            publisher = mqManager.accessTopic(topicName, "",
//                    CMQC.MQTOPIC_OPEN_AS_PUBLICATION,
//                    openOutputOptions);
//            mqMsg = new MQMessage();
//            mqMsg.messageId = CMQC.MQMI_NONE;
//            mqMsg.correlationId = CMQC.MQCI_NONE;
//            mqMsg.writeString(messageToPost);
//            publisher.put(mqMsg, pmo);
//            //System.out.println("Message published in the topic " + messageToPost);
//
//        } catch (MQException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public  MQTopic getSubscriber(MQQueueManager mq, String subscribeTopic) {
//        int openOptionsForGet = CMQC.MQSO_CREATE | CMQC.MQSO_FAIL_IF_QUIESCING | CMQC.MQSO_MANAGED | CMQC.MQSO_NON_DURABLE;
//        MQTopic subscriber = null;
//
//        try {
//            MQQueueManager qMgrForTopic = mq;
//            subscriber = qMgrForTopic.accessTopic(subscribeTopic,
//                    null,
//                    CMQC.MQTOPIC_OPEN_AS_SUBSCRIPTION,
//                    openOptionsForGet);
//        } catch (MQException e) {
//            e.printStackTrace();
//
//        }
//        return subscriber;
//    }
//
//    public  List<String> getMessageFromMQTopic(MQTopic subscriber) {
//        MQGetMessageOptions gmo = new MQGetMessageOptions();
//        gmo.options = CMQC.MQGMO_WAIT + CMQC.MQGMO_FAIL_IF_QUIESCING;
//        gmo.waitInterval = 2000;  // wait up to 2 seconds
//        MQMessage mqMsg;
//        String msgText;
//        List<String> msgList = new ArrayList<>();
//        boolean more = true;
//
//
//        while (more) {
//            try {
//                mqMsg = new MQMessage();
//                mqMsg.messageId = CMQC.MQMI_NONE;
//                mqMsg.correlationId = CMQC.MQCI_NONE;
//                subscriber.get(mqMsg, gmo);
//
//                msgText = mqMsg.readStringOfByteLength(mqMsg.getMessageLength());
//                msgList.add(msgText);
//            } catch (MQException e) {
//                more = false;
//            } catch (IOException e) {
//                more = false;
//            }
//        }
//        return msgList;
//    }
//
//
//    /**
//     * subscribeTopic - Closing MQTopic connection created previously
//     */
//    public  void closeMQTopicSubscription(MQTopic subscribeTopic) {
//        try {
//            if (subscribeTopic != null) {
//                subscribeTopic.close();
//                //System.out.println("Subscription topic connection is closed");
//            }
//        } catch (MQException e) {
//
//            e.printStackTrace();
//        }
//
//
//    }
//
//    /**
//     * @param mqQueueManager -Parameter MQQueueManager to disconnect and close mq queue manager connection created earlier
//     */
//    public  void disconnectMQManager(MQQueueManager mqQueueManager) {
//        try {
//            if (mqQueueManager != null) {
//                mqQueueManager.disconnect();
//                mqQueueManager.close();
//                //System.out.println("MQQueueManager connection is closed");
//            }
//        } catch (MQException e) {
//
//            e.printStackTrace();
//        }
//    }
//
//

//
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        System.out.println("Processing Main...");
//
//        //initialize MQ.
////        MQQueueManager mq = mqConnect("QM.ECIIB.FUNCT", "u060eciibt42.kroger.com","CLNT.ECIIB.FUNCT",1414,"","");
////        clearQueue(mq,"KRG.DSW.MOST.ORDERS.SOATest");
////        messagePut(mq, "KRG.DSW.MOST.ORDERS.SOATest", "testing");
////        String returnMsg = messageGet(mq, "KRG.DSW.MOST.ORDERS.SOATest");
//
////        MQQueueManager mq = mqConnect("QM.ECIIB.DEV", "u060eciibd81.kroger.com","CLNT.ECIIB.DEV",1414,"","");
////        clearQueue(mq,"KRG.DSW.MOST.ORDERS.SOATest");
////        messagePut(mq, "KRG.DSW.MOST.ORDERS.SOATest", "testing");
//        //String returnMsg = messageGet(mq, "KRG.DSW.MOST.ORDERS.SOATest");
//
//        MQQueueManager mq = mqConnect("QM.ECIIB.DEV", "u060eciibd81.kroger.com", "CLNT.ECIIB.DEV", 1414, "", "");
//        clearQueue(mq, "KRG.DSW.OMS.ERR");
//        messagePut(mq, "KRG.OMS.ORDER.SYNC.PUBLISH.OUT", "{\"test\": \"This is a test message designed to error out.\"}");
//        Thread.sleep(1000);
//        String returnMsg = messageGet(mq, "KRG.DSW.OMS.ERR");
//        System.out.println("Done! " + returnMsg);
//    }


}
