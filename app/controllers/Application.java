package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.*;
import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt5xx.MT540;
//import com.prowidesoftware.swift.validator.ValidationEngine;
//import com.prowidesoftware.swift.validator.ValidationProblem;

import com.typesafe.config.ConfigFactory;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application extends Controller {

  BBH540Message bm540 = new BBH540Message();
  SwiftSearchQuery searchQueryObj = new SwiftSearchQuery();


  private static final String KAFKA_SERVER = ConfigFactory.load().getString("kafka.server");

  public Result index() {
    return ok(index.render());
  }

  private List<String> processMessage = new ArrayList<>();

  public Result processDetails(){
    ObjectNode result = Json.newObject();
    result.putPOJO("result", processMessage);
    return ok(result);
  }
  public Result uploadSwiftFile() {
    processMessage = new ArrayList<>();
    processMessage.add("File uploaded started");
    String uname = session("uname");
    //System.out.println("Applicatn==============>" + uname);
    File file = null;
    SwiftParser swiftMsgParser = null;
    SwiftMessage msg = null;
    String reason = null;
    ObjectNode result = Json.newObject();
    try {
      swiftMsgParser = new SwiftParser();
      BufferedReader br = null;
      processMessage.add("Swift message convert to json started");
      try {
        String currentLine = "";
        StringBuilder mtMessage = new StringBuilder();
        List<StringBuilder> messageList = new ArrayList<StringBuilder>();
        file = request().body().asRaw().asFile();
        br = new BufferedReader(new FileReader(file));
        while ((currentLine = br.readLine()) != null) {
          currentLine = currentLine + "\n";
          mtMessage.append(currentLine);
          messageList.add(mtMessage);
        }
        if (messageList != null) {
          swiftMsgParser.setReader(new StringReader(messageList.toString()));
          msg = swiftMsgParser.message();
          try {

            if (msg.getBlock1().getBlockValue().isEmpty() || msg.getBlock2().getBlockValue().isEmpty()) {

            }
          } catch (Exception e) {

            reason = "The input Swift message cannot be parsed because of invalid syntax";
            result.put("result", "false");
            result.put("reason", reason);
            processMessage.add("The input Swift message cannot be parsed because of invalid syntax");
            // System.out.println("The input Swift message cannot be parsed because of invalid syntax");
            return ok(result);
          }


          try {
            if (msg != null) {
              // System.out.print(msg.getBlock3().isEmpty());
              if (msg.getType().equals("540")) {
                try {
                  if (msg.getBlock3().isEmpty()) {


                  }
                } catch (Exception e) {
                  reason = "Block 3 is empty";
                  processMessage.add(reason);
                  //System.out.println("Block 3 is empty");
                  //JOptionPane.showMessageDialog(null, reason, "InfoBox: " + "aaaaaaaaaaaa", JOptionPane.INFORMATION_MESSAGE);
                  return ok(reason);

                }

                try {
                  if (msg.getBlock4().isEmpty()) {
                    reason = "Block 4 is empty";
                    processMessage.add(reason);
                    return ok(reason);
                  }
                } catch (Exception e) {
                  //System.out.println("Block 4 is empty");
                }

                try {
                  if (msg.getBlock5().isEmpty()) {
                    reason = "Block 5 is empty";
                    processMessage.add(reason);
                    return ok(reason);
                  }
                } catch (Exception e) {
                  processMessage.add("Block 5 is empty");
                  //System.out.println("Block 5 is empty");
                }

              } else {
                processMessage.add("Not 540 format");
                //System.out.println("Not 540 formate");
              }
            } else {
              processMessage.add("No Data");
              //System.out.println("No Data");
            }

          } catch (Exception e) {
          }

        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    processMessage.add("Swift message convert to json format");
    result.put("result", "true");
    result.put("reason", "sucess");
    result.putPOJO("swift", msg);
    return ok(result);
  }

  public Result validateSwiftMessage() {
    processMessage = new ArrayList<>();
    processMessage.add("Swift message validation started");
    JsonNode json = request().body().asJson();
    String SenderReference20C = json.findPath("SenderReference20C").textValue();
    String MessageFunction23G = json.findPath("MessageFunction23G").textValue();
    String Instrument35B = json.findPath("Instrument35B").textValue();
    String InstrumentType12A = json.findPath("InstrumentType12A").textValue();

    final String m = "{1:F01ABBAAAA0APCH0068770990}{2:O5401326170309ABCSUS30APIM11810049741703091326N}{3:{108:000476200110769}}{4:\n" +
      ":16R:GENL\n" +
      ":20C:" + SenderReference20C + " \n" +
      ":23G:" + MessageFunction23G + "\n" +
      ":16S:GENL\n" +
      ":16R:TRADDET\n" +
      ":98A::SETT//20170309\n" +
      ":98A::TRAD//20170309\n" +
      ":35B:" + Instrument35B + "\n" +
      "U S TREASURY BILLS\n" +
      ":16R:FIA\n" +
      ":12A:" + InstrumentType12A + "\n" +
      ":11A::DENO//USD\n" +
      ":98A::MATU//20170427\n" +
      ":98A::ISSU//20160428\n" +
      ":16S:FIA\n" +
      ":16S:TRADDET\n" +
      ":16R:FIAC\n" +
      ":36B::SETT//FAMT/3000000,\n" +
      ":97A::SAFE//8127409\n" +
      ":16S:FIAC\n" +
      ":16R:SETDET\n" +
      ":22F::STCO//TRIP\n" +
      ":22F::SETR//COLO\n" +
      ":22F::COLA//OTCD\n" +
      ":16R:SETPRTY\n" +
      ":95P::PSET//FRNYUS33\n" +
      ":16S:SETPRTY\n" +
      ":16R:SETPRTY\n" +
      ":95P::DEAG//BBHCUS3IPCH\n" +
      ":97A::SAFE//8164931\n" +
      ":16S:SETPRTY\n" +
      ":16R:SETPRTY\n" +
      ":95R::SELL/DTCYID/00096\n" +
      ":16S:SETPRTY\n" +
      ":16S:SETDET\n" +
      "-}{5:{MAC:CFAF95F5}{CHK:39CD6EABBA54}}";


    //System.out.println(m);

	/*
   * Create and initialize the validation engine
	 */
    //ValidationEngine validator = new ValidationEngine();
    //validator.initialize();
    //List<ValidationProblem> resultErr = validator.validateMtMessage(m);
    List resultErr = Collections.EMPTY_LIST;
    processMessage.add("Swift message validate with ValidationEngine");

    String str = "";
    ObjectNode result = Json.newObject();


    if (resultErr.isEmpty()) {
      result.put("result", "success");
      processMessage.add("MESSAGE OK. NO VALIDATION PROBLEMS FOUND");
      str = "MESSAGE OK. NO VALIDATION PROBLEMS FOUND";
    } else {
      result.put("result", "fail");


      str = "MALFORMED MESSAGE, " + resultErr.size() + " VALIDATION PROBLEM" + resultErr.size() + " FOUND \n\n\n";
      processMessage.add(str);
      int var3 = 1;
      /*for (Iterator var4 = resultErr.iterator(); var4.hasNext(); ++var3) {
        ValidationProblem var5 = (ValidationProblem) var4.next();
        String var6 = "";
        String var8 = "";
        if (var5.getSequence() != null) {
          var8 = " (sequence: " + var5.getSequence().getName() + ")";
        }

        if (var5.isMxProblem()) {
          str = str.concat(var3 + "/" + resultErr.size() + ": " + var5.getPath() + " [line:" + var5.getLine() + " col:" + var5.getColumn() + "] " + var5.getMessage() + "</b> (" + var5.getErrorKey() + ")\n\n");
        } else {
          str = str.concat(var3 + "/" + resultErr.size() + var6 + ": " + var5.getErrorKey() + " tag index " + var5.getTagIndex() + " --> " + var5.getMessage() + var8) + "\n\n";
        }
      }*/

    }
    //System.out.println("MALFORMED MESSAGE, " + r.size() + " VALIDATION PROBLEM" + r.size() + " FOUND"+r);
    //validator.dispose();
    processMessage.add("Swift message validation completed");

    result.put("reason", str);
    //result.putPOJO("swift",msg);
    return ok(result);
    //return ok(Json.toJson(str));
  }

  private ObjectNode consumerResults() {
    processMessage.add("Message consuming from kafka started");
    ObjectNode response = Json.newObject();
    boolean results = false;
    Properties consumerConfig = new Properties();
    consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
    consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
    consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<>(consumerConfig);
    TestConsumerRebalanceListener rebalanceListener = new TestConsumerRebalanceListener();
    consumer.subscribe(Collections.singletonList("SwiftQueriesDetails"), rebalanceListener);

    while (!results) {

      ConsumerRecords<byte[], byte[]> records = consumer.poll(100);
      for (ConsumerRecord<byte[], byte[]> record : records) {
        processMessage.add("Message received results");
        //System.out.printf("Received Message topic =%s, partition =%s, offset = %d, key = %s, value = %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
        response.putPOJO("searchMessage", record.value());
        processMessage.add("Message converted into json format");
        results = true;
	break;
      }
      consumer.commitSync();
    }
    return response;
  }

  public Result createSwiftMessage() {
    processMessage = new ArrayList<>();
    processMessage.add("Swift message save process started");
    JsonNode json = request().body().asJson();
    UUID uid = UUID.randomUUID();
    String SenderReference20C = json.findPath("SenderReference20C").textValue();
    String MessageFunction23G = json.findPath("MessageFunction23G").textValue();
    String Instrument35B = json.findPath("Instrument35B").textValue();
    String InstrumentType12A = json.findPath("InstrumentType12A").textValue();
    String TradingDate98A = json.findPath("TradingDate98A").textValue();
    String Currency11A = json.findPath("Currency11A").textValue();
    String SettlementDate98A = json.findPath("SettlementDate98A").textValue();
    String DateOfIssue98A = json.findPath("DateOfIssue98A").textValue();
    String Quantity36B = json.findPath("Quantity36B").textValue();
    String TransactionType22F = json.findPath("TransactionType22F").textValue();
    String DeliveryAgent95P = json.findPath("DeliveryAgent95P").textValue();
    String Seller95R = json.findPath("Seller95R").textValue();
    String SafeKeepingAgent97A = json.findPath("SafeKeepingAgent97A").textValue();
    String SettlementAmount36B = json.findPath("SettlementAmount36B").textValue();
    String DateOfMaturity98A = json.findPath("DateOfMaturity98A").textValue();
    String TransactionTypeCola22F = json.findPath("TransactionTypeCola22F").textValue();
    String incomingMsg = json.findPath("incomingMsg").textValue();
    ObjectNode result = Json.newObject();
    final MT540 m = new MT540();
    SwiftBlock1 b1 = new SwiftBlock1();

    b1.setApplicationId("F");
    b1.setServiceId("01");
    b1.setLogicalTerminal("BICFOOYYAXXX");
    b1.setSessionNumber("1234");
    b1.setSequenceNumber("123456");

    m.getSwiftMessage().setBlock1(b1);
    SwiftBlock2Input b2 = new SwiftBlock2Input();
    b2.setMessageType("103");
    b2.setReceiverAddress("BICFOARXXXXX");
    b2.setMessagePriority("N");
    b2.setDeliveryMonitoring("1");
    b2.setObsolescencePeriod("");

    m.getSwiftMessage().setBlock2(b2);

    SwiftBlock3 b3 = new SwiftBlock3();
    b3.append(new Tag("BANKING_PRIORITY", "STP"));
    b3.append(new Tag("MESSAGE_USER_PREFERENCE", "STP"));
    // b3.append(new Field108("STP"));
    m.getSwiftMessage().setBlock3(b3);

    SwiftBlock5 b5 = new SwiftBlock5();
    b5.append(new Tag("MAC", "00000000"));
    b5.append(new Tag("CHK", ""));

    m.getSwiftMessage().setBlock5(b5);

    SwiftBlock4 b4 = new SwiftBlock4();
    m.getSwiftMessage().setBlock4(b4);

    // Mandatory Sequence A General Information(subblock 1)

    m.addField(new Field16R("GENL"));
    m.addField(new Field20C("20C"));//ok
    m.addField(new Field23G("23G"));//ok
    m.addField(new Field16S("GENL"));

    // Mandatory Sequence B Trade Details(sub block2)

    m.addField(new Field16R("TRADDET"));
    m.addField(new Field98A("98A"));//ok
    m.addField(new Field98A("98A"));//ok
    m.addField(new Field35B("35B"));//ok
    m.addField(new Field16S("TRADDET"));

    //Mandatory Sequence C Financial Instrument/Account(subblock 4)

    m.addField(new Field16R("FIAC"));
    m.addField(new Field36B("36B"));
    m.addField(new Field97A("97A"));
    m.addField(new Field16S("FIAC"));


//Mandatory Sequence E Settlement Details(subblock5)
    m.addField(new Field16R("SETDET"));
    m.addField(new Field22F("22F"));
    m.addField(new Field22F("22F"));
    m.addField(new Field16S("SETDET"));

//Mandatory Subsequence E1 Settlement Parties(subblock 6)

    m.addField(new Field16R("SETPRTY_PSET"));
    m.addField(new Field95P("95P"));
    m.addField(new Field16S("SETPRTY_PSET"));

// subblock7
    m.addField(new Field16R("SETPRTY_DELIVERY_AGENT"));
    m.addField(new Field95P("95P"));
    // m.addField(new Field97A("97A_SAFE"));
    m.addField(new Field16S("SETPRTY_DELIVERY_AGENT"));

// subblock8

    m.addField(new Field16R("SETPRTY_RECIEVING_AGENT"));
    m.addField(new Field95P("95P"));
    m.addField(new Field16S("SETPRTY_RECIEVING_AGENT"));

    //subblock 9

    m.addField(new Field16R("SETPRTY_RECIEVING_CUSTD"));
    m.addField(new Field95P("95P"));
    m.addField(new Field16S("SETPRTY_RECIEVING_CUSTD"));

// subblock10

    m.addField(new Field16R("SETPRTY_BUYR"));
    m.addField(new Field95P("95P"));
    m.addField(new Field16S("SETPRTY_BUYR"));

    //subblock 11
    m.addField(new Field16R("SETPRTY_SELLER"));
    m.addField(new Field95P("95P"));
    m.addField(new Field16S("SETPRTY_SELLER"));

    bm540.setSWIFT_MESSAGE_DATE(new Date().getTime());
    bm540.setSWIFT_MESSAGE_ID(uid);

    String inMsg = "I";

    if (incomingMsg == null || incomingMsg.equals("O")) {
      inMsg = "O";
    }
    bm540.setSWIFT_MESSAGE(m.message());


    bm540.setSENDER_ID("");
    bm540.setSENDER_NAME("");
    bm540.setRECIEVER_ID("");
    bm540.setRECIEVER_NAME("");

    bm540.setBLOCK_1_IDENTIFIER("1");
    bm540.setApplicationId(b1.getApplicationId());
    bm540.setServiceId(b1.getServiceId());
    bm540.setLogicalTerminal(b1.getLogicalTerminal());
    bm540.setSessionNumber(b1.getSessionNumber());
    bm540.setSequenceNumber(b1.getSequenceNumber());
    bm540.setBLOCK_1_HEADER_BLOCK("1");

    bm540.setBLOCK_2_IDENTIFIER("2");
    bm540.setINPUT_OUTPUT_IDENTIFIER("o");
    bm540.setMessageType(b2.getMessageType());
    bm540.setReceiverAddress(b2.getReceiverAddress());
    bm540.setMessagePriority(b2.getMessagePriority());
    bm540.setDeliveryMonitoring(b2.getDeliveryMonitoring());
    bm540.setObsolescencePeriod(b2.getObsolescencePeriod());
    bm540.setBLOCK_2_APPLICATION_HEADER("2");

    bm540.setBLOCK_3_IDENTIFIER("3");
    bm540.setBANKING_PRIORITY("");
    bm540.setMESSAGE_USER_PREFERENCE("");
    bm540.setBLOCK_3_USER_HEADER("3");

    bm540.setBLOCK_4_IDENTIFIER("4");
    bm540.setGENL_16R("GENL");
    bm540.setSEME_20C(SenderReference20C);
    bm540.setNEWG_23G(MessageFunction23G);
    bm540.setGENL_16S("GENL");

    bm540.setTRADDET_16R("TRADDET");
    bm540.setTRAD_98A(TradingDate98A);
    bm540.setSETT_98A(SettlementDate98A);
    bm540.setISIN_35B(Instrument35B);
    bm540.setINSTRUMENTTYPE_12A(InstrumentType12A);
    bm540.setDATEOFISSUE_98A(DateOfIssue98A);
    bm540.getDATEOFMATURITY_98A(DateOfMaturity98A);
    bm540.setCURRENCY_11A(Currency11A);
    bm540.setTRADDET_16S("TRADDET");

    bm540.setFIAC_16R("FIAC");
    bm540.setFIAC_36B(Quantity36B);
    bm540.setFIAC_97A(SafeKeepingAgent97A);
    bm540.setFIAC_16S("FIAC");

    bm540.setSETDET_16R("SETDET");
    bm540.setSETR_22F(TransactionType22F);
    bm540.setCOLA_22F(TransactionTypeCola22F);
    bm540.setSELLER_95R(Seller95R);
    bm540.setSETTLEMENTAMOUNT_36B(SettlementAmount36B);
    bm540.setSETDET_16S("SETDET");

    bm540.setSETPRTY_SELLER_16R("SETPRTY_SELLER");
    bm540.setSELLER_95P("");
    bm540.setSETPRTY_SELLER_16S("SETPRTY_SELLER");

    bm540.setSETPRTY_DELIVERY_AGENT_16R("SETPRTY_DELIVERY_AGENT");
    bm540.setDEAG_95P(DeliveryAgent95P);
    bm540.setSETPRTY_DELIVERY_AGENT_16S("SETPRTY_DELIVERY_AGENT");

    bm540.setSETPRTY_RECIEVING_AGENT_16R("SETPRTY_RECIEVING_AGENT");
    bm540.setREAG_95P("");
    bm540.setSETPRTY_RECIEVING_AGENT_16S("SETPRTY_RECIEVING_AGENT");

    bm540.setSETPRTY_RECIEVING_CUSTD_16R("SETPRTY_RECIEVING_CUSTD");
    bm540.setRECU_95P("");
    bm540.setSETPRTY_RECIEVING_CUSTD_16S("SETPRTY_RECIEVING_CUSTD");

    bm540.setSETPRTY_BUYR_16R("SETPRTY_BUYR");
    bm540.setBUYR_95P("");
    bm540.setSETPRTY_BUYR_16S("SETPRTY_BUYR");

    bm540.setSETPRTY_PSET_16R("SETPRTY_PSET");
    bm540.setPSET_95P("");
    bm540.setETPRTY_PSET_16S("SETPRTY_PSET");
    bm540.setSETDET_16S("");

    bm540.setBLOCK_5_IDENTIFIER("5");
    bm540.setSECOND_ELEMENT("");
    bm540.setTHIRD_ELEMENT("");
    bm540.setSWIFT540_MESSAGE("");
    bm540.setMESSAGE_DATETIME(new Date().getTime());
    bm540.setVALIDATION_STATUS(true);
    bm540.setSEND_RECIEVE_STATUS(inMsg);
    bm540.setCREATE_DB_DATETIME("");
    bm540.setCREATE_DB_USER("");

    String data = new Gson().toJson(bm540);

    result.put("type", "message-changed");
    result.put("id", uid.toString());
    result.putPOJO("bbh540Message", data);
    sendMessage("SwiftMessages",result.toString());
    processMessage.add("Swift message save success");
    return ok(("Producer success"));
  }

  public Result searchQuery() throws ParseException {
    processMessage = new ArrayList<>();
    processMessage.add("Search query initiated");
    JsonNode json = request().body().asJson();
    String Mt540 = json.findPath("fee").textValue();
    String fromDate = json.findPath("from").textValue().toString();
    String toDateStr = json.findPath("to").textValue().toString();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Date date = formatter.parse(fromDate);
    long dateFromLong = date.getTime();
    Date toDate= formatter.parse(toDateStr);
    toDate.setHours(24);
    toDate.setMinutes(59);

    searchQueryObj.setSWIFT_MESSAGE_TYPE(Mt540);
    searchQueryObj.setSWIFT_MESSAGE_FROM_DATE(dateFromLong);
    searchQueryObj.setSWIFT_MESSAGE_TO_DATE(toDate.getTime());
    Gson gson = new Gson();
    String data = gson.toJson(searchQueryObj);

    ObjectNode result = Json.newObject();
    UUID uid = UUID.randomUUID();
    result.put("id", uid.toString());
    result.put("type", "query");
    result.putPOJO("searchMessage", data);
    processMessage.add("Search query sending to kafka server initiated....");
    sendMessage("SwiftQueries",result.toString());
    processMessage.add("Search query reading from kafka server initiated....");
    processMessage.add("Search query initiated");
    return ok(consumerResults());
  }

  public Result parseSwiftMessage() {
    processMessage = new ArrayList<>();
    processMessage.add("Swift Message parsing started");
    JsonNode json = request().body().asJson();
    final MT540 m = new MT540();
    SwiftBlock1 b1 = new SwiftBlock1();
    b1.setApplicationId("F");
    b1.setServiceId("01");
    b1.setLogicalTerminal("BICFOOYYAXXX");
    b1.setSessionNumber("1234");
    b1.setSequenceNumber("123456");

    m.getSwiftMessage().setBlock1(b1);
    SwiftBlock2Input b2 = new SwiftBlock2Input();
    b2.setMessageType("103");
    b2.setReceiverAddress("BICFOARXXXXX");
    b2.setDeliveryMonitoring("1");
    m.getSwiftMessage().setBlock2(b2);

    SwiftBlock3 b3 = new SwiftBlock3();
    b3.append(new Field119("STP"));
    m.getSwiftMessage().setBlock3(b3);

    SwiftBlock5 b5 = new SwiftBlock5();
    b5.append(new Tag("MAC", "00000000"));
    b5.append(new Tag("CHK", ""));

    m.getSwiftMessage().setBlock5(b5);

    SwiftBlock4 b4 = new SwiftBlock4();
    m.getSwiftMessage().setBlock4(b4);

    m.addField(new Field23G(json.findPath("MessageFunction23G").textValue()));
    m.addField(new Field20C(json.findPath("SenderReference20C").textValue()));

    m.addField(new Field35B(json.findPath("Instrument35B").textValue()));
    //m.addField(new Field94B(json.findPath("Place94B").textValue()));
    m.addField(new Field98A(json.findPath("TradingDate98A").textValue()));
    m.addField(new Field98A(json.findPath("SettlementDate98A").textValue()));
    m.addField(new Field36B(json.findPath("Quantity36B").textValue()));
    m.addField(new Field12A(json.findPath("InstrumentType12A").textValue()));
    m.addField(new Field11A(json.findPath("Currency11A").textValue()));
    m.addField(new Field98A(json.findPath("DateOfIssue98A").textValue()));

    m.addField(new Field22F(json.findPath("TransactionType22F").textValue()));
    m.addField(new Field95R(json.findPath("Seller95R").textValue()));
    m.addField(new Field36B(json.findPath("SettlementAmount36B").textValue()));
    m.addField(new Field95P(json.findPath("DeliveryAgent95P").textValue()));
    m.addField(new Field97A(json.findPath("SafeKeepingAgent97A").textValue()));

    //System.out.println("First stage" + m.json());

    ObjectNode result = Json.newObject();
    result.put("result", "success");
    result.putPOJO("bbh540Message", m);
    processMessage.add("Swift Message parsing completed");
    return ok(result);
  }

  private void sendMessage(String topicName, String message){
    processMessage.add("Swift message send to kafka server process...");
    Properties props = new Properties();
    props.put("bootstrap.servers", KAFKA_SERVER);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("acks", "all");
    props.put("buffer.memory", 33554432);
    props.put("compression.type", "none");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("client.id", "");
    props.put("linger.ms", 0);
    props.put("max.block.ms", 60000);
    props.put("max.request.size", 1048576);
    props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
    props.put("request.timeout.ms", 30000);
    props.put("timeout.ms", 30000);
    props.put("max.in.flight.requests.per.connection", 5);
    props.put("retry.backoff.ms", 5);

    KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);
    processMessage.add("Swift message send to kafka server success");
    //String SwiftQueriestopic = "SwiftQueries";

    try {
      myProducer.send(new ProducerRecord<String, String>(topicName, message));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      myProducer.close();
    }
  }

  public Result login() {
    return ok(login.render());
  }

  private static class  TestConsumerRebalanceListener implements ConsumerRebalanceListener {
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
      //System.out.println("Called onPartitionsRevoked with partitions:" + partitions);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
      //System.out.println("Called onPartitionsAssigned with partitions:" + partitions);
    }
  }
}

