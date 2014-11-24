/**
 * 
 */
package demo.services.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * @author vvenkatraman
 *
 */
public class DefaultContainerResponseFilter implements ContainerResponseFilter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sun.jersey.spi.container.ContainerResponseFilter#filter(com.sun.jersey
     * .spi.container.ContainerRequest,
     * com.sun.jersey.spi.container.ContainerResponse)
     */
    @Override
    public ContainerResponse filter(ContainerRequest creq,
            ContainerResponse cresp) {
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Credentials",
                "true");
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Methods",
                "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        cresp.getHttpHeaders().putSingle("Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With");

        return cresp;
    }

}
