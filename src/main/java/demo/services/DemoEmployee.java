/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import demo.servlet.StartupManager;

/**
 * REST Web Service
 *
 * @author ctsuser1
 */
 @Path("employee")  // make this class process this url. empno is a variable that represents employee number.
public class DemoEmployee {
     /** The log. */
    private static Logger LOG = LogManager.getLogger(StartupManager.class.getName());
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DemoEmployee
     */
    public DemoEmployee() {
    }

    @GET
    @Path("/data/{empno}")
    @Produces("application/json")
    public String getJson( @PathParam("empno") int empno) {  // empno represents the empno sent from client
      switch(empno) {
          case 1 :
              return "{'name':'Harini', 'age':28}";
          case 2:
              return "{'name':'Venkat', 'age':30}";
          case 3:
              return "{'name':'Navya', 'age':2}";
          default:
              return "{'name':'unknown', 'age':-1}";
      } // end of switch
   } // end of
}
