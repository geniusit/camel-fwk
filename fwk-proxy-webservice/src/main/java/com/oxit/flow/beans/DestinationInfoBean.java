package com.oxit.flow.beans;

/**
 * Destination info. Listener and Producer can share some common data like - JMS provider - destination name -
 * destination type (queue/topic) 
 *
 * This bean declare those elements.
 */
public class DestinationInfoBean {
	
	private String providerType;
	private String destinationName;
	private String destinationType;
	
	/**
	 * @return the providerType
	 */
	public String getProviderType() {
		return providerType;
	}
	/**
	 * @param providerType the providerType to set
	 */
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}
	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return destinationName;
	}
	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	/**
	 * @return the destinationType
	 */
	public String getDestinationType() {
		return destinationType;
	}
	/**
	 * @param destinationType the destinationType to set
	 */
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
}
