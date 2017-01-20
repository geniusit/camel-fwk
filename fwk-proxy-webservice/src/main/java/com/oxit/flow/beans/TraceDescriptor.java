package com.oxit.flow.beans;

/**
 * Bean that holds route information. 
 * This is better to have a bean instead of using header value because the information is persistent in bean as long as the route isn't yet finished. 
 * Header values can be loose in case of intermediary route call.
 *
 */
public class TraceDescriptor {
	
	private Runtime runtime;
	private Metadata metadata;
	/**
	 * @return the runtime
	 */
	public Runtime getRuntime() {
		return runtime;
	}
	/**
	 * @param runtime the runtime to set
	 */
	public void setRuntime(Runtime runtime) {
		this.runtime = runtime;
	}
	/**
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
}
