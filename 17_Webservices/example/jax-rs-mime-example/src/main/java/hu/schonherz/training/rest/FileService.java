package hu.schonherz.training.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/file")
public class FileService {

	private static final String FILE_PATH = "E:\\DLs\\X5.txt";

	@GET
	@Path("/get")
	@Produces("text/plain")
	public Response getFile() {

		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"bmw_x5.txt\"");
		return response.build();

	}

}