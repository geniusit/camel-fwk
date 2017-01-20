package com.oxit.flow.beans;

public class Runtime {
	
	private String traceTimestamp;
	private IntegrationServer integrationServer;
	
	/**
	 * @return the traceTimestamp
	 */
	public String getTraceTimestamp() {
		return traceTimestamp;
	}

	/**
	 * @param traceTimestamp the traceTimestamp to set
	 */
	public void setTraceTimestamp(String traceTimestamp) {
		this.traceTimestamp = traceTimestamp;
	}

	/**
	 * @return the integrationServer
	 */
	public IntegrationServer getIntegrationServer() {
		return integrationServer;
	}

	/**
	 * @param integrationServer the integrationServer to set
	 */
	public void setIntegrationServer(IntegrationServer integrationServer) {
		this.integrationServer = integrationServer;
	}

}
