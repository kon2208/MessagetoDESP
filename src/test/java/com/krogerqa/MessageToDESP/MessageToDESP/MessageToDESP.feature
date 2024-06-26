Feature: Functional DESP Kafka Test

  Background:
    * configure ssl = true
    * def KafkaConsumerClient = Java.type('com.krogerqa.MessageToDESP.util.kafka.KafkaConsumerClient')
    * def KafkaProducerClient = Java.type('com.krogerqa.MessageToDESP.util.kafka.KafkaProducerClient')

    ## Read Kafka consumer client
    * def KafkaConsumerClient = Java.type('com.krogerqa.kafka.KafkaConsumerClient')

    * def Random = Java.type('java.util.Random')
    * def RandomStringUtils = Java.type('org.apache.commons.lang3.RandomStringUtils')

    * def authentication = karate.properties['authValue']
    * call read('classpath:com/kroger/ec/platform/utils/Helpers.feature')
    * def correlationID = correlationId("sub_domain_POST_" + karate.properties['karate.env'])

  @debug
  Scenario: Random Text
    * def randomV = function(){ return java.util.UUID.randomUUID() + '' }
    * print randomV()
    * print new Random().nextInt(10000)
    * string generatedString = RandomStringUtils.random(10, true, true);
    * print generatedString

  @debug
  Scenario Outline: Post message to DESP Positive | <TestCase>
    * string topic = "mfg-customer-orders";
    * string propertiesFilename = "src/test/resources/plain.properties";
    * def consumer = new KafkaConsumerClient(topic, propertiesFilename);

#    * string generatedString = RandomStringUtils.random(10, true, true);
#    * string notes = "Karate-Kafka-1123"+generatedString
#    * print 'Notes: '+notes

    ## Reading Message based on the csv file
    * def f1 = read('classpath:src/test/resources/data/Input/Data_Sheet/<InputRequestBody>')
    * string  reqString = f1
    * print 'Request body :- ' + reqString

    * def producer = new KafkaProducerClient(topic, propertiesFilename);
#    * producer.sendMessage("<key>", "{<payload>}");
    * producer.sendMessage("<key>", reqString);
    * producer.close();
    * def messages = consumer.getMessagesContainingValue(notes);
    * consumer.close();
    * print 'Message: '+messages
    * json out = messages
    * print 'json out: '+out

      # Hitting Groups POST API with all required details
    Given url baseURL
    And path groupsGetEndpoint
    And request updatedPostBody
    And headers reqHeader
    When method post
    Then status <ExpectedStatusCode>

     # Reading Test Data from CSV file for Data Driven Testing
    Examples:
      | read('classpath:src/test/resources/data/Input/Data_Sheet/'+ 'PostingMsgToDespPositive.csv').slice(0,1)

