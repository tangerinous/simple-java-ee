package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.model.SaleProduct;
import local.diplom.service.service.ProductService;
import local.diplom.service.model.Product;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.Lob;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created  by david on 21.02.16
 */

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @GET
    public Object getAll(@QueryParam("skip") Integer skip,
                         @QueryParam("limit") Integer limit,
                         @QueryParam("count") String count,
                         @QueryParam("category") Long category) {
        return productService.findAll(skip, limit, count, category);
    }

    @POST
    public Product post(Product product) throws Exception {
        productService.insert(product);
        return product;
    }

    @POST
    @Path("/sell")
    public void sell(SaleProduct saleProduct) throws Exception {
        productService.sell(saleProduct);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        productService.update(id, jsonNode);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productService.deleteById(id);
    }
}
