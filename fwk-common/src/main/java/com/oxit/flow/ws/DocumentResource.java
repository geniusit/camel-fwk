package com.oxit.flow.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Document Resource Rest WS
 * 
 * This resource can be called from any external source in order the get the payload of a specific exchange.
 *
 */

@Path("/document/") 
public interface DocumentResource {

	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	DocumentResponse getDocument(	@QueryParam("processTypeName") String processTypeName, 
									@QueryParam("timestamp") String timestamp, 
									@QueryParam("documentID") String documentID);
}