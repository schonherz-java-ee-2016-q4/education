package hu.schonherz.training.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

	//http://localhost:8080/JAX-RS-PathParam/users/id/6	
	@GET
	@Path("/id/{id}")
	public Response getUserById(@PathParam("id") String id) {

		return Response.status(200).entity("getUserById is called, id : " + id).build();

	}

	//http://localhost:8080/JAX-RS-PathParam/users/2016/14/17	
	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(@PathParam("year") int year, @PathParam("month") int month,
			@PathParam("day") int day) {

		String date = year + "/" + month + "/" + day;

		return Response.status(200).entity("getUserHistory is called, year/month/day : " + date).build();

	}

	//http://localhost:8080/JAX-RS-PathParam/users	
	@GET
	public Response getUser() {

		return Response.status(200).entity("getUser is called").build();

	}

	//http://localhost:8080/JAX-RS-PathParam/users/vip
	@GET
	@Path("/vip")
	public Response getUserVIP() {

		return Response.status(200).entity("getUserVIP is called").build();

	}

	// http://localhost:8080/JAX-RS-PathParam/users/name/Jani	
	@GET
	@Path("/name/{name}")
	public Response getUserByName(@PathParam("name") String name) {

		return Response.status(200).entity("getUserByName is called, name : " + name).build();

	}

	// http://localhost:8080/JAX-RS-PathParam/users/666
	@GET
	@Path("{id : \\d+}")
	public Response getUserByIdWithCheck(@PathParam("id") String id) {

		return Response.status(200).entity("getUserByIdWithCheck is called, id : " + id).build();

	}

	// http://localhost:8080/JAX-RS-PathParam/users/username/Jani -> Bad
	// http://localhost:8080/JAX-RS-PathParam/users/username/J6 -> Good
	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {

		return Response.status(200).entity("getUserByUserName is called, username : " + username).build();

	}

	// http://localhost:8080/JAX-RS-PathParam/users/books/999	
	@GET
	@Path("/books/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn) {

		return Response.status(200).entity("getUserBookByISBN is called, isbn : " + isbn).build();

	}

	// http://localhost:8080/JAX-RS-PathParam/users/query?from=100&to=200&orderBy=age&orderBy=name
	@GET
	@Path("/query")
	public Response getUsers(@DefaultValue("1000") @QueryParam("from") int from,
			@DefaultValue("999") @QueryParam("to") int to,
			@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {

		return Response.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString())
				.build();

	}

}