package com.oxit.flow.ws;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.oxit.flow.FlowConstant;

/**
 * Specific Document Aggregation Strategy used to build the response to the WS
 * client.
 *
 */
public class DocumentAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange original, Exchange resource) {

		String payload = null;
		DocumentResponse response = new DocumentResponse();
		
		// Get the file path from the <simple> expression
		if(resource != null){
			GenericFile<?> genericFile = (GenericFile<?>) resource.getIn().getBody();
			// Read the file on the FileSystem
			File file = (File) genericFile.getBody();


			try {
				if (file != null) {
					payload  = Files.toString(file, Charsets.UTF_8);
				} else {
					throw new IOException();
				}

			} catch (IOException e) {
				payload = "Unreadable content";
			}
			response.setFilename(original.getIn().getHeader(FlowConstant.PROCESS_TYPE_NAME) + File.separator
					+ original.getIn().getHeader(FlowConstant.TIMESTAMP) + File.separator + file.getName());
			response.setContentType(getContentType(file.getName()));
		} else {
			//The payload isn't available on the FS. May be it has been deleted by the shell script ...
			payload = StringUtils.EMPTY;
		}
		
		response.setBody(payload);
		
		// Set the response
		original.getIn().setBody(response, DocumentResponse.class);

		return original;
	}

	/**
	 * Get the Content-Type of the file based on it's filename
	 * 
	 * @param filename
	 * @return String - Content-Type
	 */
	private String getContentType(String filename) {

		final String extension = FilenameUtils.getExtension(filename);

		String contentType = null;
		switch (extension) {
			case "xml":
				contentType = MediaType.TEXT_XML;
			default:
				contentType = MediaType.TEXT_PLAIN;
			}
		return contentType;

	}
}
