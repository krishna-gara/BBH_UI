package controllers;

import java.util.UUID;

/**
 * Created by smutyala on 5/5/2017.
 */
public class BBH540Message {

  private Long SWIFT_MESSAGE_DATE;
  private UUID SWIFT_MESSAGE_ID;
  private String INCOMING_MSG;
  private String SENDER_ID;
  private String SENDER_NAME;
  private String RECIEVER_ID;
  private String RECIEVER_NAME;
  private String SWIFT_MESSAGE;

  private String BLOCK_1_IDENTIFIER;
  private String applicationId;
  private String serviceId;
  private String logicalTerminal;
  private String sessionNumber;
  private String sequenceNumber;
  private String BLOCK_1_HEADER_BLOCK;

  private String BLOCK_2_IDENTIFIER;
  private String INPUT_OUTPUT_IDENTIFIER;
  private String messageType;
  private String receiverAddress;
  private String messagePriority;
  private String deliveryMonitoring;
  private String obsolescencePeriod;
  private String BLOCK_2_APPLICATION_HEADER;

  private String BLOCK_3_IDENTIFIER;
  private String BANKING_PRIORITY;
  private String MESSAGE_USER_PREFERENCE;
  private String BLOCK_3_USER_HEADER;

  private String BLOCK_4_IDENTIFIER;
  private String GENL_16R;
  private String SEME_20C;
  private String NEWG_23G;
  private String GENL_16S;

  private String TRADDET_16R;
  private String TRAD_98A;
  private String SETT_98A;
  //---DateOfIssue98A
  private String ISIN_35B;
  private String INSTRUMENTTYPE_12A;// added
  private String DATEOFISSUE_98A;//added
  private String DATEOFMATURITY_98A;//added
  private String CURRENCY_11A;//added
  private String TRADDET_16S;

  private String FIAC_16R;
  private String FIAC_36B;
  private String FIAC_97A;
  private String FIAC_16S;

  private String SETDET_16R;
  private String SETR_22F;
  private String COLA_22F;
  private String SELLER_95R;//added
  private String SETTLEMENTAMOUNT_36B;//added

  private String SETPRTY_SELLER_16R;
  private String SELLER_95P;
  private String SETPRTY_SELLER_16S;

  private String SETPRTY_DELIVERY_AGENT_16R;
  private String DEAG_95P;
  private String SETPRTY_DELIVERY_AGENT_16S;

  private String SETPRTY_RECIEVING_AGENT_16R;
  private String REAG_95P;
  private String SETPRTY_RECIEVING_AGENT_16S;

  private String SETPRTY_RECIEVING_CUSTD_16R;
  private String RECU_95P;
  private String SETPRTY_RECIEVING_CUSTD_16S;

  private String SETPRTY_BUYR_16R;
  private String BUYR_95P;
  private String SETPRTY_BUYR_16S;

  private String SETPRTY_PSET_16R;
  private String PSET_95P;
  private String ETPRTY_PSET_16S;
  private String SETDET_16S;

  private String BLOCK_5_IDENTIFIER;
  private String SECOND_ELEMENT;
  private String THIRD_ELEMENT;
  private String SWIFT540_MESSAGE;
  private Long MESSAGE_DATETIME;
  private boolean VALIDATION_STATUS;
  private String SEND_RECIEVE_STATUS;
  private String CREATE_DB_DATETIME;
  private String CREATE_DB_USER;



  public String getINSTRUMENTTYPE_12A() {
    return INSTRUMENTTYPE_12A;
  }
  public String getSWIFT_MESSAGE() {
    return SWIFT_MESSAGE;
  }

  public String getINCOMING_MSG() {
    return INCOMING_MSG;
  }

  public String getDATEOFISSUE_98A() {
    return DATEOFISSUE_98A;
  }
  public String getDATEOFMATURITY_98A() {
    return DATEOFMATURITY_98A;
  }
  public String getCURRENCY_11A() {
    return CURRENCY_11A;
  }
  public String getSELLER_95R() {
    return SELLER_95R;
  }
  public String getSETTLEMENTAMOUNT_36B() {
    return SETTLEMENTAMOUNT_36B;
  }

  public void setINSTRUMENTTYPE_12A(String instrumenttype) {
    this.INSTRUMENTTYPE_12A = instrumenttype;
  }
  public void setSWIFT_MESSAGE(String SWIFT_MESSAGE) {
    this.SWIFT_MESSAGE = SWIFT_MESSAGE;
  }

  public void setINCOMING_MSG(String incomingMsg) {
    this.INCOMING_MSG = incomingMsg;
  }

  public void setDATEOFISSUE_98A(String dateofissue) {
    this.DATEOFISSUE_98A = dateofissue;
  }
  public void getDATEOFMATURITY_98A(String dateofmaturity) {
    this.DATEOFMATURITY_98A = dateofmaturity;
  }
  public void setCURRENCY_11A(String currency) {
    this.CURRENCY_11A = currency;
  }
  public void setSELLER_95R(String seller) {
    this.SELLER_95R = seller;
  }
  public void setSETTLEMENTAMOUNT_36B(String settlementamount) {
    this.SETTLEMENTAMOUNT_36B = settlementamount;
  }


  public Long getSWIFT_MESSAGE_DATE() {
    return SWIFT_MESSAGE_DATE;
  }

  public void setSWIFT_MESSAGE_DATE(Long SWIFT_MESSAGE_DATE) {
    this.SWIFT_MESSAGE_DATE = SWIFT_MESSAGE_DATE;
  }

  public UUID getSWIFT_MESSAGE_ID() {
    return SWIFT_MESSAGE_ID;
  }

  public void setSWIFT_MESSAGE_ID(UUID SWIFT_MESSAGE_ID) {
    this.SWIFT_MESSAGE_ID = SWIFT_MESSAGE_ID;
  }

  public String getSENDER_ID() {
    return SENDER_ID;
  }

  public void setSENDER_ID(String SENDER_ID) {
    this.SENDER_ID = SENDER_ID;
  }

  public String getSENDER_NAME() {
    return SENDER_NAME;
  }

  public void setSENDER_NAME(String SENDER_NAME) {
    this.SENDER_NAME = SENDER_NAME;
  }

  public String getRECIEVER_ID() {
    return RECIEVER_ID;
  }

  public void setRECIEVER_ID(String RECIEVER_ID) {
    this.RECIEVER_ID = RECIEVER_ID;
  }

  public String getRECIEVER_NAME() {
    return RECIEVER_NAME;
  }

  public void setRECIEVER_NAME(String RECIEVER_NAME) {
    this.RECIEVER_NAME = RECIEVER_NAME;
  }

  public String getBLOCK_1_IDENTIFIER() {
    return BLOCK_1_IDENTIFIER;
  }

  public void setBLOCK_1_IDENTIFIER(String BLOCK_1_IDENTIFIER) {
    this.BLOCK_1_IDENTIFIER = BLOCK_1_IDENTIFIER;
  }

  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getLogicalTerminal() {
    return logicalTerminal;
  }

  public void setLogicalTerminal(String logicalTerminal) {
    this.logicalTerminal = logicalTerminal;
  }

  public String getSessionNumber() {
    return sessionNumber;
  }

  public void setSessionNumber(String sessionNumber) {
    this.sessionNumber = sessionNumber;
  }

  public String getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(String sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public String getBLOCK_1_HEADER_BLOCK() {
    return BLOCK_1_HEADER_BLOCK;
  }

  public void setBLOCK_1_HEADER_BLOCK(String BLOCK_1_HEADER_BLOCK) {
    this.BLOCK_1_HEADER_BLOCK = BLOCK_1_HEADER_BLOCK;
  }

  public String getBLOCK_2_IDENTIFIER() {
    return BLOCK_2_IDENTIFIER;
  }

  public void setBLOCK_2_IDENTIFIER(String BLOCK_2_IDENTIFIER) {
    this.BLOCK_2_IDENTIFIER = BLOCK_2_IDENTIFIER;
  }

  public String getINPUT_OUTPUT_IDENTIFIER() {
    return INPUT_OUTPUT_IDENTIFIER;
  }

  public void setINPUT_OUTPUT_IDENTIFIER(String INPUT_OUTPUT_IDENTIFIER) {
    this.INPUT_OUTPUT_IDENTIFIER = INPUT_OUTPUT_IDENTIFIER;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public String getMessagePriority() {
    return messagePriority;
  }

  public void setMessagePriority(String messagePriority) {
    this.messagePriority = messagePriority;
  }

  public String getDeliveryMonitoring() {
    return deliveryMonitoring;
  }

  public void setDeliveryMonitoring(String deliveryMonitoring) {
    this.deliveryMonitoring = deliveryMonitoring;
  }

  public String getObsolescencePeriod() {
    return obsolescencePeriod;
  }

  public void setObsolescencePeriod(String obsolescencePeriod) {
    this.obsolescencePeriod = obsolescencePeriod;
  }

  public String getBLOCK_2_APPLICATION_HEADER() {
    return BLOCK_2_APPLICATION_HEADER;
  }

  public void setBLOCK_2_APPLICATION_HEADER(String BLOCK_2_APPLICATION_HEADER) {
    this.BLOCK_2_APPLICATION_HEADER = BLOCK_2_APPLICATION_HEADER;
  }

  public String getBLOCK_3_IDENTIFIER() {
    return BLOCK_3_IDENTIFIER;
  }

  public void setBLOCK_3_IDENTIFIER(String BLOCK_3_IDENTIFIER) {
    this.BLOCK_3_IDENTIFIER = BLOCK_3_IDENTIFIER;
  }

  public String getBANKING_PRIORITY() {
    return BANKING_PRIORITY;
  }

  public void setBANKING_PRIORITY(String BANKING_PRIORITY) {
    this.BANKING_PRIORITY = BANKING_PRIORITY;
  }

  public String getMESSAGE_USER_PREFERENCE() {
    return MESSAGE_USER_PREFERENCE;
  }

  public void setMESSAGE_USER_PREFERENCE(String MESSAGE_USER_PREFERENCE) {
    this.MESSAGE_USER_PREFERENCE = MESSAGE_USER_PREFERENCE;
  }

  public String getBLOCK_3_USER_HEADER() {
    return BLOCK_3_USER_HEADER;
  }

  public void setBLOCK_3_USER_HEADER(String BLOCK_3_USER_HEADER) {
    this.BLOCK_3_USER_HEADER = BLOCK_3_USER_HEADER;
  }

  public String getBLOCK_4_IDENTIFIER() {
    return BLOCK_4_IDENTIFIER;
  }

  public void setBLOCK_4_IDENTIFIER(String BLOCK_4_IDENTIFIER) {
    this.BLOCK_4_IDENTIFIER = BLOCK_4_IDENTIFIER;
  }

  public String getGENL_16R() {
    return GENL_16R;
  }

  public void setGENL_16R(String GENL_16R) {
    this.GENL_16R = GENL_16R;
  }

  public String getSEME_20C() {
    return SEME_20C;
  }

  public void setSEME_20C(String SEME_20C) {
    this.SEME_20C = SEME_20C;
  }

  public String getNEWG_23G() {
    return NEWG_23G;
  }

  public void setNEWG_23G(String NEWG_23G) {
    this.NEWG_23G = NEWG_23G;
  }

  public String getGENL_16S() {
    return GENL_16S;
  }

  public void setGENL_16S(String GENL_16S) {
    this.GENL_16S = GENL_16S;
  }

  public String getTRADDET_16R() {
    return TRADDET_16R;
  }

  public void setTRADDET_16R(String TRADDET_16R) {
    this.TRADDET_16R = TRADDET_16R;
  }

  public String getTRAD_98A() {
    return TRAD_98A;
  }

  public void setTRAD_98A(String TRAD_98A) {
    this.TRAD_98A = TRAD_98A;
  }

  public String getSETT_98A() {
    return SETT_98A;
  }

  public void setSETT_98A(String SETT_98A) {
    this.SETT_98A = SETT_98A;
  }

  public String getISIN_35B() {
    return ISIN_35B;
  }

  public void setISIN_35B(String ISIN_35B) {
    this.ISIN_35B = ISIN_35B;
  }

  public String getTRADDET_16S() {
    return TRADDET_16S;
  }

  public void setTRADDET_16S(String TRADDET_16S) {
    this.TRADDET_16S = TRADDET_16S;
  }

  public String getFIAC_16R() {
    return FIAC_16R;
  }

  public void setFIAC_16R(String FIAC_16R) {
    this.FIAC_16R = FIAC_16R;
  }

  public String getFIAC_36B() {
    return FIAC_36B;
  }

  public void setFIAC_36B(String FIAC_36B) {
    this.FIAC_36B = FIAC_36B;
  }

  public String getFIAC_97A() {
    return FIAC_97A;
  }

  public void setFIAC_97A(String FIAC_97A) {
    this.FIAC_97A = FIAC_97A;
  }

  public String getFIAC_16S() {
    return FIAC_16S;
  }

  public void setFIAC_16S(String FIAC_16S) {
    this.FIAC_16S = FIAC_16S;
  }

  public String getSETDET_16R() {
    return SETDET_16R;
  }

  public void setSETDET_16R(String SETDET_16R) {
    this.SETDET_16R = SETDET_16R;
  }

  public String getSETR_22F() {
    return SETR_22F;
  }
  public String getCOLA_22F() {   return COLA_22F;  }

  public void setSETR_22F(String SETR_22F) {
    this.SETR_22F = SETR_22F;
  }
  public void setCOLA_22F(String COLA_22F) {
    this.COLA_22F = COLA_22F;
  }

  public String getSETPRTY_SELLER_16R() {
    return SETPRTY_SELLER_16R;
  }

  public void setSETPRTY_SELLER_16R(String SETPRTY_SELLER_16R) {
    this.SETPRTY_SELLER_16R = SETPRTY_SELLER_16R;
  }

  public String getSELLER_95P() {
    return SELLER_95P;
  }

  public void setSELLER_95P(String SELLER_95P) {
    this.SELLER_95P = SELLER_95P;
  }

  public String getSETPRTY_SELLER_16S() {
    return SETPRTY_SELLER_16S;
  }

  public void setSETPRTY_SELLER_16S(String SETPRTY_SELLER_16S) {
    this.SETPRTY_SELLER_16S = SETPRTY_SELLER_16S;
  }

  public String getSETPRTY_DELIVERY_AGENT_16R() {
    return SETPRTY_DELIVERY_AGENT_16R;
  }

  public void setSETPRTY_DELIVERY_AGENT_16R(String SETPRTY_DELIVERY_AGENT_16R) {
    this.SETPRTY_DELIVERY_AGENT_16R = SETPRTY_DELIVERY_AGENT_16R;
  }

  public String getDEAG_95P() {
    return DEAG_95P;
  }

  public void setDEAG_95P(String DEAG_95P) {
    this.DEAG_95P = DEAG_95P;
  }

  public String getSETPRTY_DELIVERY_AGENT_16S() {
    return SETPRTY_DELIVERY_AGENT_16S;
  }

  public void setSETPRTY_DELIVERY_AGENT_16S(String SETPRTY_DELIVERY_AGENT_16S) {
    this.SETPRTY_DELIVERY_AGENT_16S = SETPRTY_DELIVERY_AGENT_16S;
  }

  public String getSETPRTY_RECIEVING_AGENT_16R() {
    return SETPRTY_RECIEVING_AGENT_16R;
  }

  public void setSETPRTY_RECIEVING_AGENT_16R(String SETPRTY_RECIEVING_AGENT_16R) {
    this.SETPRTY_RECIEVING_AGENT_16R = SETPRTY_RECIEVING_AGENT_16R;
  }

  public String getREAG_95P() {
    return REAG_95P;
  }

  public void setREAG_95P(String REAG_95P) {
    this.REAG_95P = REAG_95P;
  }

  public String getSETPRTY_RECIEVING_AGENT_16S() {
    return SETPRTY_RECIEVING_AGENT_16S;
  }

  public void setSETPRTY_RECIEVING_AGENT_16S(String SETPRTY_RECIEVING_AGENT_16S) {
    this.SETPRTY_RECIEVING_AGENT_16S = SETPRTY_RECIEVING_AGENT_16S;
  }

  public String getSETPRTY_RECIEVING_CUSTD_16R() {
    return SETPRTY_RECIEVING_CUSTD_16R;
  }

  public void setSETPRTY_RECIEVING_CUSTD_16R(String SETPRTY_RECIEVING_CUSTD_16R) {
    this.SETPRTY_RECIEVING_CUSTD_16R = SETPRTY_RECIEVING_CUSTD_16R;
  }

  public String getRECU_95P() {
    return RECU_95P;
  }

  public void setRECU_95P(String RECU_95P) {
    this.RECU_95P = RECU_95P;
  }

  public String getSETPRTY_RECIEVING_CUSTD_16S() {
    return SETPRTY_RECIEVING_CUSTD_16S;
  }

  public void setSETPRTY_RECIEVING_CUSTD_16S(String SETPRTY_RECIEVING_CUSTD_16S) {
    this.SETPRTY_RECIEVING_CUSTD_16S = SETPRTY_RECIEVING_CUSTD_16S;
  }

  public String getSETPRTY_BUYR_16R() {
    return SETPRTY_BUYR_16R;
  }

  public void setSETPRTY_BUYR_16R(String SETPRTY_BUYR_16R) {
    this.SETPRTY_BUYR_16R = SETPRTY_BUYR_16R;
  }

  public String getBUYR_95P() {
    return BUYR_95P;
  }

  public void setBUYR_95P(String BUYR_95P) {
    this.BUYR_95P = BUYR_95P;
  }

  public String getSETPRTY_BUYR_16S() {
    return SETPRTY_BUYR_16S;
  }

  public void setSETPRTY_BUYR_16S(String SETPRTY_BUYR_16S) {
    this.SETPRTY_BUYR_16S = SETPRTY_BUYR_16S;
  }

  public String getSETPRTY_PSET_16R() {
    return SETPRTY_PSET_16R;
  }

  public void setSETPRTY_PSET_16R(String SETPRTY_PSET_16R) {
    this.SETPRTY_PSET_16R = SETPRTY_PSET_16R;
  }

  public String getPSET_95P() {
    return PSET_95P;
  }

  public void setPSET_95P(String PSET_95P) {
    this.PSET_95P = PSET_95P;
  }

  public String getETPRTY_PSET_16S() {
    return ETPRTY_PSET_16S;
  }

  public void setETPRTY_PSET_16S(String ETPRTY_PSET_16S) {
    this.ETPRTY_PSET_16S = ETPRTY_PSET_16S;
  }

  public String getSETDET_16S() {
    return SETDET_16S;
  }

  public void setSETDET_16S(String SETDET_16S) {
    this.SETDET_16S = SETDET_16S;
  }

  public String getBLOCK_5_IDENTIFIER() {
    return BLOCK_5_IDENTIFIER;
  }

  public void setBLOCK_5_IDENTIFIER(String BLOCK_5_IDENTIFIER) {
    this.BLOCK_5_IDENTIFIER = BLOCK_5_IDENTIFIER;
  }

  public String getSECOND_ELEMENT() {
    return SECOND_ELEMENT;
  }

  public void setSECOND_ELEMENT(String SECOND_ELEMENT) {
    this.SECOND_ELEMENT = SECOND_ELEMENT;
  }

  public String getTHIRD_ELEMENT() {
    return THIRD_ELEMENT;
  }

  public void setTHIRD_ELEMENT(String THIRD_ELEMENT) {
    this.THIRD_ELEMENT = THIRD_ELEMENT;
  }

  public String getSWIFT540_MESSAGE() {
    return SWIFT540_MESSAGE;
  }

  public void setSWIFT540_MESSAGE(String SWIFT540_MESSAGE) {
    this.SWIFT540_MESSAGE = SWIFT540_MESSAGE;
  }

  public Long getMESSAGE_DATETIME() {
    return MESSAGE_DATETIME;
  }

  public void setMESSAGE_DATETIME(Long MESSAGE_DATETIME) {
    this.MESSAGE_DATETIME = MESSAGE_DATETIME;
  }

  public boolean isVALIDATION_STATUS() {
    return VALIDATION_STATUS;
  }

  public void setVALIDATION_STATUS(boolean VALIDATION_STATUS) {
    this.VALIDATION_STATUS = VALIDATION_STATUS;
  }

  public String getSEND_RECIEVE_STATUS() {
    return SEND_RECIEVE_STATUS;
  }

  public void setSEND_RECIEVE_STATUS(String SEND_RECIEVE_STATUS) {
    this.SEND_RECIEVE_STATUS = SEND_RECIEVE_STATUS;
  }

  public String getCREATE_DB_DATETIME() {
    return CREATE_DB_DATETIME;
  }

  public void setCREATE_DB_DATETIME(String CREATE_DB_DATETIME) {
    this.CREATE_DB_DATETIME = CREATE_DB_DATETIME;
  }

  public String getCREATE_DB_USER() {
    return CREATE_DB_USER;
  }

  public void setCREATE_DB_USER(String CREATE_DB_USER) {
    this.CREATE_DB_USER = CREATE_DB_USER;
  }
}
