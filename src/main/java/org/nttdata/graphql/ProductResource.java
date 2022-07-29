package org.nttdata.graphql;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.nttdata.entities.Product;
import org.nttdata.repositories.ProductRepository;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class ProductResource {

    @Inject
    private ProductRepository pr;

    @Query("allProducts")
    @Description("Get all products from a database")
    public Uni<List<Product>> getAllProducts(){
        return pr.listAll();
    }

    @Query("product")
    @Description("Get a product if exists")
    public Uni<Product> getProduct(@Name("productId") Long id){
        return pr.findById(id);
    }

}
