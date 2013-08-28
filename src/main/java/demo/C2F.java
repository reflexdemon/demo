package demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("c2f")
public class C2F {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFtoC() throws JSONException {

      JSONObject jsonObject = new JSONObject();

      Double fahrenheit;
      Double celsius = 0.0;
      fahrenheit = ((celsius * 9) / 5) + 32;

      jsonObject.put("celsius", celsius);
      jsonObject.put("fahrenheit", fahrenheit);

      return Response.status(200).entity("" + jsonObject).build();
    }

    @Path("{c}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFtoCfromInput(@PathParam("c") Double c) throws JSONException {

      JSONObject jsonObject = new JSONObject();
      Double fahrenheit;
      Double celsius = c;
      fahrenheit = ((celsius * 9) / 5) + 32;
      
       
      jsonObject.put("celsius", celsius);
      jsonObject.put("fahrenheit", fahrenheit);

      return Response.status(200).entity("" + jsonObject).build();
    }
}
