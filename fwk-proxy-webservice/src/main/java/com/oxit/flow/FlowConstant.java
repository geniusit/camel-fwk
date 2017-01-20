package com.oxit.flow;


/**
 * Some public constants
 */
public class FlowConstant {
	/** Singleton */
	private FlowConstant(){}

	/**
	 * Traces properties
	 */
	public static final String PROCESS_TYPE_NAME = "processTypeName";
	public static final String BUSINESS_TAGS = "businessTags";
	public static final String DOCUMENT_ID = "documentID";
	public static final String DOCUMENT_TYPE_NAME = "documentTypeName";
	public static final String SENDER_ID = "senderID";
	public static final String ROUTING_TAGS = "routingTags";
	public static final String SERVICE_NAME = "serviceName";
	public static final String SERVER_NAME = "serverName";
	public static final String SERVER_PORT = "serverPort";
	public static final String TIMESTAMP = "timestamp";
	public static final String STATUS = "status";
	public static final String PAYLOAD_SIZE = "payloadSize";
	public static final String DURATION = "duration";
	
	public static final String STATUS_KO = "KO";
	
	public static final String STRING_SLASH = "/";
	public static final String STRING_UNDERSCORE = "_";
	public static final String STRING_ZERO = "0";
	
	/**
	 * Configuration keys
	 */
	public static final String MAPPING_ROUTE_NAME = "mappingRouteName";
	public static final String URL = "url";
	public static final String WS_INVOKER = "wsInvoker";
	
	public static final String KARAF_ENV = "karaf.env";
	
	/**
	 * HTTP Response
	 */
	public static final String OK_RESPONSE = "OK";
	
}
