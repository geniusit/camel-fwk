package com.oxit.flow.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentResponse {

	@XmlElement(required = true, nillable = true)
	private String filename;
	
	@XmlElement(required = true, nillable = true)
	private String body;
	
	@XmlElement(required = false, nillable = true)
	private String reveiverID;
	
	@XmlElement(required = false, nillable = true)
	private String originalFilename;
	
	@XmlElement(required = false, nillable = true)
	private String contentType;
	
	@XmlElement(required = false, nillable = true)
	private String error;
	
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the reveiverID
	 */
	public String getReveiverID() {
		return reveiverID;
	}
	/**
	 * @param reveiverID the reveiverID to set
	 */
	public void setReveiverID(String reveiverID) {
		this.reveiverID = reveiverID;
	}
	/**
	 * @return the originalFilename
	 */
	public String getOriginalFilename() {
		return originalFilename;
	}
	/**
	 * @param originalFilename the originalFilename to set
	 */
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}
