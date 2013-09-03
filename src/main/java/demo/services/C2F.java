package demo.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("c2f")
public class C2F {
    
    /** The log. */
    private static Log LOG = LogFactory.getLog(C2F.class.getName());
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFtoC() throws JSONException {

      JSONObject jsonObject = new JSONObject();

      Double fahrenheit;
      Double celsius = 0.0;
      fahrenheit = ((celsius * 9) / 5) + 32;

      jsonObject.put("celsius", celsius);
      jsonObject.put("fahrenheit", fahrenheit);

      LOG.debug(celsius + "C --> " + fahrenheit + "F");
      
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
      LOG.debug(celsius + "C --> " + fahrenheit + "F");
      return Response.status(200).entity("" + jsonObject).build();
    }
}
