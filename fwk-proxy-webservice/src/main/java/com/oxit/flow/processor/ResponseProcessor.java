package com.oxit.flow.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oxit.flow.FlowConstant;

/**
 * Processor uses to send the response to the REST client
 *
 */
public class ResponseProcessor implements Processor {
	
	private Log log = LogFactory.getLog(ResponseProcessor.class);

	/**
	 * Send OK to the client
	 */
	@Override
	public void process(final Exchange exchange) throws Exception {

		exchange.getIn().setBody(FlowConstant.OK_RESPONSE);
	}
}