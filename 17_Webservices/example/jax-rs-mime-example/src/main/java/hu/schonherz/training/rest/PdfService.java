package hu.schonherz.training.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/pdf")
public class PdfService{

	private static final String FILE_PATH = "E:\\DLs\\primefaces_user_guide_5_3.pdf";

	// http://localhost:8080/JAX-RS-MIME/pdf/get
	@GET
	@Path("/get")
	@Produces("application/pdf")
	public Response getFile() {

		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=primefaces_user_guide_5_3.pdf");
		return response.build();

	}

}