package org.nttdata.repositories;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import org.nttdata.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Long> {

    public Uni<Response> add(Product p) {
        return Panache.withTransaction(p::persist)
                .replaceWith(Response.ok(p).status(Response.Status.CREATED)::build);
    }

    public Uni<Response> delete(Long id) {
        return Panache.withTransaction(() -> Product.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }

    public Uni<Product> findById(Long id){
        return Product.findById(id);
    }

    public Uni<Response> update(Long id, Product p) {
        return Panache.withTransaction(() -> Product.<Product> findById(id)
                .onItem().ifNotNull().invoke(entity -> {
                    entity.setDescription(p.getDescription());
                    entity.setName(p.getName());
                    entity.setCode(p.getCode());
                }))
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }


}
