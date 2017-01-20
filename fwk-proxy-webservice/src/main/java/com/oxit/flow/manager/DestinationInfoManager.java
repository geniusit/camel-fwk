package com.oxit.flow.manager;

import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.cm.ConfigurationAdmin;

import com.oxit.flow.beans.DestinationInfoBean;

/**
 * Manager allowing to set the JMS information. The information will be retrieve from the .cfg file by using the PropertyPlaceHolder
 * If none value has been defined a default mechanism will be used.
 * 
 * If none destination name has been defined then the destination name will be the header processTypeName
 * If none destination type has been defined then the destination type will be retrieve from .cfg file with the key : destination.type
 * If none JMS provider has been defined the the jms provider will be retrieve from the .cfg file with the key : jms.provider
 *
 */
public class DestinationInfoManager {
	
	private Log log = LogFactory.getLog(DestinationInfoManager.class);
	
	@BeanInject(value = "destinationInfoBean")
	private DestinationInfoBean info;
	
	@BeanInject
	private ConfigurationAdmin configAdmin;
	
	/**
	 * Set the DestinationInfoBean with the appropriate value depending on the processTypeName header value
	 * 
	 * @param processTypeName
	 * @param context
	 * @throws Exception
	 */
	public void setDestinationInfo(final String processTypeName, final CamelContext context)throws Exception{
		
		final String configurationFileName = processTypeName;
		
		info.setProviderType(getJMSProvider(configurationFileName, context));
		info.setDestinationName(getDestinationName(processTypeName, configurationFileName, context));
		info.setDestinationType(getDestinationType(configurationFileName, context));
		
	}
	
	/**
	 * Get the JMS provider of the configuration file. If none JMS provider has been defined then get the default JMS provider
	 * 
	 * @param configurationFileName
	 * @param context
	 * @return The JMS Provider
	 * @throws Exception
	 */
	private String getJMSProvider(final String configurationFileName, final CamelContext context)throws Exception{
		
		String providerType = null;
		
		//Overriding default configuration
		if(configAdmin != null){
			if(configAdmin.getConfiguration(configurationFileName) != null){
				if(configAdmin.getConfiguration(configurationFileName).getProperties() != null){
					providerType = (String)configAdmin.getConfiguration(configurationFileName).getProperties().get("jms.provider");
				}
			}
		}
		return providerType == null ? context.resolvePropertyPlaceholders("{{jms.provider}}") : providerType; 
	}
	
	/**
	 * Get the destination name of the configuration file. If none Destination name has been defined then we set the processTypeName as destination name.
	 * 
	 * @param processTypeName
	 * @param configurationFileName
	 * @param context
	 * @return The Destination name
	 * @throws Exception
	 */
	private String getDestinationName(final String processTypeName, final String configurationFileName, final CamelContext context) throws Exception {

		String destinationName = null;
		
		//Overriding default configuration
		if(configAdmin != null){
			if(configAdmin.getConfiguration(configurationFileName) != null){
				if(configAdmin.getConfiguration(configurationFileName).getProperties() != null){
					destinationName = (String)configAdmin.getConfiguration(configurationFileName).getProperties().get("destination.name");
				}
			}
		}
		
		return destinationName == null ? processTypeName : destinationName;
	}

	/**
	 * Get the destination type of the configuration file. If none destination type has been defined then get the default default destination type
	 * 
	 * @param configurationFileName
	 * @param context
	 * @return The Destination type
	 * @throws Exception
	 */
	private String getDestinationType(final String configurationFileName, final CamelContext context) throws Exception {

		String destinationType = null;
		
		//Overriding default configuration
		if(configAdmin != null){
			if(configAdmin.getConfiguration(configurationFileName) != null){
				if(configAdmin.getConfiguration(configurationFileName).getProperties() != null){
					destinationType = (String)configAdmin.getConfiguration(configurationFileName).getProperties().get("destination.type");
				}
			}
		}
		return destinationType == null ? context.resolvePropertyPlaceholders("{{destination.type}}") : destinationType;
	}
}
