/**
 * 
 */
package net.entrofi.hrm.rest.resources;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import net.entrofi.hrm.domain.persistence.entity.User;
import net.entrofi.hrm.domain.persistence.repository.UserRepository;
import net.entrofi.hrm.domain.service.PersistenceServiceBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hcomak
 *
 */
@Named
@Path("/users")
public class UserResource {
	
	public static final String PATH_VERSION_EXPRESSION = "/{version: v([0-9]){1}((.)([0-9]){1,2}){0,1}}";
	
	public static final String PATH_SEGMENT_ITEM = "/item-";
	
	public static final String QUERY_PARAM_LIMIT= "limit";
	
	public static final String QUERY_PARAM_OFFSET= "offset";
	
	public static final String QUERY_PARAM_ID= "id";
	
	public static final String PATH_PARAM_VERSION = "version";
	
	public static final String PATH_PARAM_SITE = "site";
	
	
	private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

	@Resource(name="userService")
	private PersistenceServiceBase<User, UserRepository> service;
	
	
	@Context
	private UriInfo uriInfo;
	
	
	@POST
	@Path(PATH_VERSION_EXPRESSION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(User user){
		User persistedUser = service.persist(user);
		
		URI entityURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(persistedUser.getId())).build();
		
		return Response.status(Status.CREATED).entity(persistedUser).location(entityURI).build();
	}
	
	
	
	/**
	 * 
	 * list TODO documentation:method
	 *
	 * @param uriInfo
	 * @param version
	 * @return
	 */
	@GET
	@Path(PATH_VERSION_EXPRESSION+"/{list:(list){0,1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> list(@Context UriInfo uriInfo, @PathParam("version") String version , @QueryParam("limit") int limit, @QueryParam("offset") int offset){
		return service.findAll();
	}
	
	
	/**
	 * 
	 * find TODO documentation:method
	 *
	 * @param version
	 * @param id
	 * @return
	 */
	@GET
	@Path(PATH_VERSION_EXPRESSION + PATH_SEGMENT_ITEM+"{id}")
	public User find(@PathParam("version") String version, @PathParam("id") String id){
		return service.find(Long.valueOf(id));
	}
	
	
	
	/**
	 * 
	 * delete TODO documentation:method
	 *
	 * @param version
	 * @param t
	 * @return
	 */
	@DELETE
	@Path(PATH_VERSION_EXPRESSION)
	public Response delete(@PathParam(PATH_PARAM_VERSION)String version, User user) {
		LOG.debug("Deleting entity: " + user.toString());
		service.delete(user);
		return Response.noContent().build();
	}



	public PersistenceServiceBase<User, UserRepository> getService() {
		return service;
	}



	public void setService(PersistenceServiceBase<User, UserRepository> service) {
		this.service = service;
	}
	
	
	
}
