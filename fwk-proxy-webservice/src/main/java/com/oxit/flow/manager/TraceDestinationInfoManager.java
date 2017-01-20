package com.oxit.flow.manager;

import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.cm.ConfigurationAdmin;

import com.oxit.flow.beans.DestinationInfoBean;

public class TraceDestinationInfoManager {
	
	private Log log = LogFactory.getLog(TraceDestinationInfoManager.class);
	
	@BeanInject(value = "traceDestinationInfoBean")
	private DestinationInfoBean traceInfo;

	@BeanInject
	private ConfigurationAdmin configAdmin;
	
	public void setTraceInfo(final String processTypeName, final CamelContext context)throws Exception{
		
		final String configurationFileName = processTypeName;
		
		traceInfo.setProviderType(getJMSProvider(configurationFileName, context));
		traceInfo.setDestinationName(getDestinationName(configurationFileName, context));
		traceInfo.setDestinationType(getDestinationType(configurationFileName, context));
	}

	private String getJMSProvider(final String configurationFileName, final CamelContext context) throws Exception {
		
		String providerType = null;
		
		//Overriding default configuration
		if(configAdmin != null){
			if(configAdmin.getConfiguration(configurationFileName) != null){
				if(configAdmin.getConfiguration(configurationFileName).getProperties() != null){
					providerType = (String)configAdmin.getConfiguration(configurationFileName).getProperties().get("trace.jms.provider");
				}
			}
		}
		return providerType == null ? context.resolvePropertyPlaceholders("{{trace.jms.provider}}") : providerType;
	}

	private String getDestinationName(final String configurationFileName, final CamelContext context) throws Exception {

		String destinationName = null;
		
		//Overriding default configuration
		if(configAdmin != null){
			if(configAdmin.getConfiguration(configurationFileName) != null){
				if(configAdmin.getConfiguration(configurationFileName).getProperties() != null){
					destinationName = (String)configAdmin.getConfiguration(configurationFileName).getProperties().get("trace.destination.name");
				}
			}
		}
		return destinationName == null ? context.resolvePropertyPlaceholders("{{trace.destination.name}}") : destinationName;
	}

	private String getDestinationType(final String configurationFileName, final CamelContext context) throws Exception {

		String destinationType = null;
		
		//Overriding default configuration
		if(configAdmin != null){
			if(configAdmin.getConfiguration(configurationFileName) != null){
				if(configAdmin.getConfiguration(configurationFileName).getProperties() != null){
					destinationType = (String)configAdmin.getConfiguration(configurationFileName).getProperties().get("trace.destination.type");
				}
			}
		}
		
		return destinationType == null ? context.resolvePropertyPlaceholders("{{trace.destination.type}}") : destinationType;
	}
}
