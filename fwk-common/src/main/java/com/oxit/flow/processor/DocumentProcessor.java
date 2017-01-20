package com.oxit.flow.processor;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;

import com.oxit.flow.FlowConstant;

/**
 * Processor used to set the queries values in the Camel Header message
 *
 */
public class DocumentProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		//Get the Query
		String queryString = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
		
		//Build a MultivaluedMap with the CXFRS API
		MultivaluedMap<String, String> queryMap = JAXRSUtils.getStructuredParams(queryString, FlowConstant.STRING_AND, false, false);

		//Set a header with each first element of the entry
		for (Map.Entry<String, List<String>> eachQueryParam : queryMap.entrySet()) {
			exchange.getIn().setHeader(eachQueryParam.getKey(), getFirstEntrySafelyFromList(eachQueryParam.getValue()));
		}
	}

	/**
	 * Get the first element of a list
	 * 
	 * @param list
	 * @return String - the first element of the list
	 */
	private String getFirstEntrySafelyFromList(List<String> list) {

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
