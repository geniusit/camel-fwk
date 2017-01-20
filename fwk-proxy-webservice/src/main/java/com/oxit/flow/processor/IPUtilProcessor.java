package com.oxit.flow.processor;

import java.net.InetSocketAddress;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oxit.flow.FlowConstant;

/**
 * Processor used to get the IP address from the sender in case of no senderID headers has been explicitly sent.
 * These values are used to set the senderID header value.
 *
 */
public class IPUtilProcessor implements Processor{
	
	private Log log = LogFactory.getLog(IPUtilProcessor.class);
	
    @Override
    public void process(Exchange exchange) {
    	
    	//Get the remote address by using special Netty header. 
    	//If you use jetty you should instead cast the message to HttpServletRequest and call the appropriate methods to get the remote ip and port 
    	InetSocketAddress remoteAddress = (InetSocketAddress)exchange.getIn().getHeader("CamelNettyRemoteAddress");
    	
        if (remoteAddress != null) {
            //Manually set the senderID header with remote ip
            exchange.getIn().setHeader(FlowConstant.SENDER_ID, remoteAddress.toString());
        }
    }
}