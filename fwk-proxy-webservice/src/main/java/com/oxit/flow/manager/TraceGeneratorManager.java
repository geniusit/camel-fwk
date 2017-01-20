package com.oxit.flow.manager;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.oxit.flow.FlowConstant;
import com.oxit.flow.beans.TraceDescriptor;


/**
 * Bean allowing to set a trace message in order to send it to the trace destination. This trace message is used to monitor the flow.
 * 
 */
public class TraceGeneratorManager {
	
	@BeanInject
	private TraceDescriptor traceDescriptor;

	/**
	 * Set the trace message. When an exchange is completed with no error then the trace message only contains headers values.
	 * When an error occurs in the transport layer then the trace message is set to status KO wit the stacktrace as body
	 * 
	 * @param processTypeName
	 * @param documentTypeName
	 * @param senderID
	 * @param businessTags
	 * @param exchange
	 * @throws Exception
	 */
	public void setTrace(final Exchange exchange, final Exception exception)throws Exception{
		
		//Use getOut() because we don't want to keep original values
		exchange.getOut().setHeader(FlowConstant.PROCESS_TYPE_NAME, traceDescriptor.getMetadata().getFunctionalLocation().getProcessTypeName());
		exchange.getOut().setHeader(FlowConstant.DOCUMENT_TYPE_NAME, traceDescriptor.getMetadata().getFunctionalLocation().getDocumentTypeName());
		exchange.getOut().setHeader(FlowConstant.SENDER_ID, traceDescriptor.getMetadata().getFunctionalLocation().getSenderID());
		exchange.getOut().setHeader(FlowConstant.BUSINESS_TAGS, traceDescriptor.getMetadata().getFunctionalLocation().getBusinessTags());
		exchange.getOut().setHeader(FlowConstant.SERVICE_NAME, traceDescriptor.getMetadata().getFunctionalLocation().getServiceName());
		exchange.getOut().setHeader(FlowConstant.DOCUMENT_ID, traceDescriptor.getMetadata().getFunctionalLocation().getDocumentID());
		exchange.getOut().setHeader(FlowConstant.PAYLOAD_SIZE, traceDescriptor.getMetadata().getFunctionalLocation().getPayloadSize());
		exchange.getOut().setHeader(FlowConstant.SERVER_NAME, traceDescriptor.getRuntime().getIntegrationServer().getServerName());
		exchange.getOut().setHeader(FlowConstant.SERVER_PORT, traceDescriptor.getRuntime().getIntegrationServer().getPort());
		exchange.getOut().setHeader(FlowConstant.TIMESTAMP, traceDescriptor.getRuntime().getTraceTimestamp());
		
		Timestamp s = Timestamp.valueOf(traceDescriptor.getRuntime().getTraceTimestamp());
		exchange.getOut().setHeader(FlowConstant.DURATION, (new Date().getTime()-s.getTime())/1000);
		
		if(exception != null){
			//The body is the Exception stacktrace
			exchange.getOut().setBody(ExceptionUtils.getStackTrace(exception));
			exchange.getOut().setHeader(FlowConstant.STATUS, FlowConstant.STATUS_KO);
		}
		else {
			exchange.getOut().setBody(StringUtils.EMPTY);
		}
	}
}
