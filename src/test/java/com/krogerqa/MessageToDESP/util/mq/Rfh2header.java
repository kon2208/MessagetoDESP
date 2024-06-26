package com.krogerqa.MessageToDESP.util.mq;

import com.ibm.mq.*;
import com.ibm.mq.headers.MQRFH2;
import static com.ibm.mq.constants.CMQC.*;

import java.io.IOException;

public class Rfh2header
{
    public void putDataOnMQ() throws IOException, MQException {

        MQMessage msg = new MQMessage();
        MQRFH2 rfh2 = new MQRFH2();

        rfh2.setEncoding(MQENC_NATIVE);
        rfh2.setCodedCharSetId(MQCCSI_INHERIT);
        rfh2.setFormat(MQFMT_STRING);
        rfh2.setNameValueCCSID(1208);
        rfh2.setFieldValue("usr", "messageType", "signature");

        try {
            rfh2.write(msg);
        } catch (
                IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
//        msg.writeString(requestFileContent);
        msg.persistence = MQPER_PERSISTENT;
        msg.format = MQFMT_RF_HEADER_2;

        String queueName = "QL.FMJ.ECOMM.EVENTS.JEWELRYSIG";

        MQPutMessageOptions pmo = new MQPutMessageOptions();
//        MQQueue queue = mq.accessQueue(queueName, MQOO_FAIL_IF_QUIESCING + MQOO_OUTPUT);
//        queue.put(msg, pmo);
    }

    }


