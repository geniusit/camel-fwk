package com.oxit.flow.manager;

import java.util.Arrays;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Iterables;
import com.oxit.flow.FlowConstant;

/**
 * EnrichHeaderManager. This bean allow to use extra properties in the header of the message in order to correctly route it.
 *
 */
public class EnrichHeaderManager {
	
	private Log log = LogFactory.getLog(EnrichHeaderManager.class);

	/**
	 * Generate a unique DocumentID
	 * 
	 * @return String documentID
	 */
	public String generateDocumentID(){
		
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Get the processTypeName from the URL
	 * 
	 * @param exchange
	 * @return String processTypeName
	 */
	public String setProcessTypeName(final Exchange exchange){
		
		 return Iterables.getLast(Arrays.asList(exchange.getIn().getHeader(Exchange.HTTP_URI).toString().split(FlowConstant.STRING_SLASH)));
	}
}