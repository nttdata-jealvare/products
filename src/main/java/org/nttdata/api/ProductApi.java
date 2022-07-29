package org.nttdata.api;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.nttdata.entities.Product;
import org.nttdata.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {

    @Inject
    ProductRepository pd;

    @GET
    public Uni<List<PanacheEntityBase>> list() {
        return Product.listAll(Sort.by("name"));
    }

    @POST
    public Uni<Response> add(Product p) {
        return pd.add(p);
    }

    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long id) {
        return pd.delete(id);
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@RestPath Long id, Product p) {
        return pd.update(id, p);
    }

    @GET
    @Path("/{Id}")
    public Uni<Product> findById(@PathParam("Id") Long id){
        return pd.findById(id);
    }

}