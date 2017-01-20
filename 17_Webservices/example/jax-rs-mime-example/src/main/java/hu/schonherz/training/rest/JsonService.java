package hu.schonherz.training.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hu.schonherz.training.records.Product;

@Path("/json/product")
public class JsonService {

	// http://localhost:8080/JAX-RS-MIME/json/product/get
	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getProductInJSON() {

		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);

		return product;
	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(Product product) {
		String result = "Product created : " + product;
		return Response.status(201).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

}
