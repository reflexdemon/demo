package demo;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import demo.domain.DemoVO;

@Path("f2c")
public class F2C {

    @GET
    @Produces("application/json")
    @HeaderParam("Access-Control-Allow-Origin: *")
    public Response convertFtoC() throws JSONException {

	DemoVO jsonObject = new DemoVO();
	float fahrenheit = 0.0f;
	float celsius;
	celsius = (fahrenheit - 32.0f) * 5.0f / 9.0f;
	jsonObject.setFahrenheit(fahrenheit);
	jsonObject.setCelsius(celsius);


	return Response.status(200).entity(jsonObject).build();
    }

    @Path("{f}")
    @GET
    @Produces("application/json")
    @HeaderParam("Access-Control-Allow-Origin: *")
    public Response convertFtoCfromInput(@PathParam("f") float f)
	    throws JSONException {

	
	System.out.println("convertFtoCfromInput(" + f + ")");
	DemoVO jsonObject = new DemoVO();
	float celsius;
	float fahrenheit = f;
	celsius = (fahrenheit - 32.0f) * 5.0f / 9.0f;
	jsonObject.setFahrenheit(fahrenheit);
	jsonObject.setCelsius(celsius);

	return Response.status(200).entity(jsonObject).build();
    }
}
