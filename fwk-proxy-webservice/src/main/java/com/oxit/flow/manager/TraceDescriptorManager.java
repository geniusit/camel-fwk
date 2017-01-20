package com.oxit.flow.manager;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.TimeZone;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.util.InetAddressUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oxit.flow.FlowConstant;
import com.oxit.flow.beans.FunctionalLocation;
import com.oxit.flow.beans.IntegrationServer;
import com.oxit.flow.beans.Metadata;
import com.oxit.flow.beans.TraceDescriptor;
import com.oxit.flow.beans.Runtime;

/**
 * Class that manages all the information of the exchange. Objects were reproduced identically than webMethods documents already used by PlugNGo Team.
 * The purpose of the manager is the build an object graph that holds exchange's information so it's easier to set the trace. 
 * Trace manager should always get information from the TraceDescritpor bean.
 * This TraceDescritor bean is injected and set here with the appropriate values.
 *
 */
public class TraceDescriptorManager {
	
	private Log log = LogFactory.getLog(TraceDescriptorManager.class);
	
	@BeanInject
	private TraceDescriptor traceDescriptor;
	
	/**
	 * Set Trace Descriptor from header values
	 * 
	 * @param processTypeName
	 * @param documentTypeName
	 * @param senderID
	 * @param businessTags
	 * @param documentID
	 * @param exchange
	 */
	public void setTraceDescriptor(final String processTypeName, final String documentTypeName, final String senderID, final String businessTags, final String documentID, final Exchange exchange) throws Exception{
		
		traceDescriptor.setMetadata(getMetadata(processTypeName, documentTypeName, senderID, businessTags, documentID, exchange));
		traceDescriptor.setRuntime(getRuntime(exchange));
	}
	
	private Runtime getRuntime(final Exchange exchange) throws Exception{
		Runtime runtime = new Runtime();
		runtime.setTraceTimestamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS", TimeZone.getTimeZone("GMT")));
		runtime.setIntegrationServer(getIntegrationServer(exchange));
		return runtime;
	}
	
	private Metadata getMetadata(final String processTypeName, final String documentTypeName, final String senderID, final String businessTags, final String documentID, final Exchange exchange){
		Metadata metadata = new Metadata();
		metadata.setFunctionalLocation(getFunctionalLocation(processTypeName, documentTypeName, senderID, businessTags, documentID, exchange));
		return metadata;
	}
	
	private FunctionalLocation getFunctionalLocation(final String processTypeName, final String documentTypeName, final String senderID, final String businessTags, final String documentID, final Exchange exchange){
		FunctionalLocation functionalLocation = new FunctionalLocation();
		functionalLocation.setProcessTypeName(processTypeName);
		functionalLocation.setDocumentTypeName(documentTypeName);
		functionalLocation.setSenderID(senderID);
		functionalLocation.setDocumentID(documentID);
		functionalLocation.setBusinessTags(businessTags);
		functionalLocation.setPayloadSize(getPayloadSize(exchange));
		functionalLocation.setBusinessTags(businessTags);
		functionalLocation.setServiceName(exchange.getContext().getName()+":"+exchange.getUnitOfWork().getRouteContext().getRoute().getId());
		
		return functionalLocation;
	}
	
	private IntegrationServer getIntegrationServer(final Exchange exchange) throws Exception{
		IntegrationServer integrationServer = new IntegrationServer();
		integrationServer.setPort(exchange.getContext().resolvePropertyPlaceholders("{{http.port}}"));
		integrationServer.setServerName(getServerName());
		
		return integrationServer;
	}
	
	
	/**
	 * Compute the payload size of the exchange
	 * 
	 * @param exchange
	 * @return Object payloadSize
	 */
	private String getPayloadSize(Exchange exchange){
		
		String payloadSize = null;
		//For ws2jms exchanges, a Content-Length header is available
		if(exchange.getIn().getHeader(Exchange.CONTENT_LENGTH) != null && !((String)exchange.getIn().getHeader(Exchange.CONTENT_LENGTH)).equals(FlowConstant.STRING_ZERO)){
			payloadSize = (String)exchange.getIn().getHeader(Exchange.CONTENT_LENGTH);
		}
		//For jms2ws exchanges, compute the payload size by hand
		else {
			payloadSize = String.valueOf(exchange.getIn().getBody(byte[].class).length);
		}
		return payloadSize;
	}
	
	/**
	 * Get the server name
	 * 
	 * @return String serverName
	 */
	private String getServerName(){
		String serverName;
		try {
			serverName = InetAddressUtil.getLocalHostName();
		} catch (UnknownHostException e) {
			serverName = StringUtils.EMPTY;
		}
		return serverName;
	}
}
