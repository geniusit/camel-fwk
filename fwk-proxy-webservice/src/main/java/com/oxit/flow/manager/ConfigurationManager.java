package com.oxit.flow.manager;

import java.io.IOException;

import org.apache.camel.BeanInject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.cm.ConfigurationAdmin;

import com.oxit.flow.FlowConstant;


/**
 * 
 * Bean used to retrieve configuration properties of a processTypeName.
 *
 */
public class ConfigurationManager {
	
	private Log log = LogFactory.getLog(ConfigurationManager.class);
	
	@BeanInject
	private ConfigurationAdmin configAdmin;
	
	private String url = null;
	private String mappingRouteName = null;
	private String wsInvoker = null;
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the mappingRouteName
	 */
	public String getMappingRouteName() {
		return mappingRouteName;
	}
	
	/**
	 * @return the wsInvoker
	 */
	public String getWsInvoker() {
		return wsInvoker;
	}

	/**
	 * Set the mappingRouteName
	 * 
	 * @param processTypeName
	 * @throws IOException
	 */
	public void setMappingRouteName(final String processTypeName) throws IOException {
		
		String mappingRouteName = null;
		
		if(processTypeName != null && !StringUtils.isEmpty(processTypeName)){
			if(configAdmin != null){
				if(configAdmin.getConfiguration(processTypeName) != null){
					if(configAdmin.getConfiguration(processTypeName).getProperties() != null){
						mappingRouteName = (String)configAdmin.getConfiguration(processTypeName).getProperties().get(FlowConstant.MAPPING_ROUTE_NAME);
						log.debug("Mapping Route Name : " + mappingRouteName + " for the " + processTypeName + " process type name");
					}
				}
			}
		}
		
		this.mappingRouteName = mappingRouteName;
	}
	
	/**
	 * Set the URL to call
	 * 
	 * @param processTypeName
	 * @throws IOException
	 */
	public void setUrl(final String processTypeName) throws IOException{
		
		String url = null;
		
		if(processTypeName != null && !StringUtils.isEmpty(processTypeName)){
			if(configAdmin != null){
				if(configAdmin.getConfiguration(processTypeName) != null){
					if(configAdmin.getConfiguration(processTypeName).getProperties() != null){
						url = (String)configAdmin.getConfiguration(processTypeName).getProperties().get(FlowConstant.URL);
						log.debug("URL : " + url + " for the " + processTypeName + " process type name");
					}
				}
			}
		}
		
		this.url = url;
	}
	
	/**
	 * Set the WS Invoker
	 * 
	 * @param processTypeName
	 * @throws IOException
	 */
	public void setWsInvoker(final String processTypeName) throws IOException{
		
		String wsInvoker = null;
		
		if(processTypeName != null && !StringUtils.isEmpty(processTypeName)){
			if(configAdmin != null){
				if(configAdmin.getConfiguration(processTypeName) != null){
					if(configAdmin.getConfiguration(processTypeName).getProperties() != null){
						wsInvoker = (String)configAdmin.getConfiguration(processTypeName).getProperties().get(FlowConstant.WS_INVOKER);
						log.debug("WS Invoker : " + wsInvoker + " for the " + processTypeName + " process type name");
					}
				}
			}
		}
		this.wsInvoker = wsInvoker;
	}
}
