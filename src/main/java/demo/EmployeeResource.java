package demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("employee")   // make this class process this url. empno is a variable that represents employee number.
public class EmployeeResource {
    public EmployeeResource() {
    }
    @GET 
    @Path("{empno}") 
    @Produces("application/json") 
    public String getJson( @PathParam("empno") int empno) {  // empno represents the empno sent from client   
      switch(empno) {
          case 1 :
              return "{'name':'George Koch', 'age':58}";
          case 2:
              return "{'name':'Peter Norton', 'age':50}";
          default:
              return "{'name':'unknown', 'age':-1}";
      } // end of switch
   } // end of 
} 
