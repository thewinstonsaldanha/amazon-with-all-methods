package com.postbook;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.model.*;

import dao.AdminDAOImpl;
import dao.ProductDAOImpl;


@Path("amazon")
public class MyResource {

//	<------------------User url------------------>

	AdminDAOImpl userImpl = new AdminDAOImpl();

	@Path("admin/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status addAdmin(Admin admin) throws SQLException {
		System.out.println("addAdmincalled");
	    System.out.println(admin.toString());
		return userImpl.signUp(admin);
	}

	@Path("admin/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Admin loginUser(Admin admin) throws SQLException {
		return userImpl.signIn(admin);
	}
	
	
	ProductDAOImpl productdao= new ProductDAOImpl();
	
	
	
	//Status addProduct(Products product);   //post
		@Path("products/add")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Status addProduct(Products product) throws SQLException {
			return productdao.addProduct(product);
		}
	// List<Products> getAllProducts();
	
	@Path("products/getAllProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Products> getAllProducts() throws SQLException {
		return productdao.getAllProducts();
	}
	
	
	
	
	@Path("products/updateProducts")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status updateProducts(Products product) throws SQLException {
		return productdao.updateProduct(product);
	}
	
	@Path("tweets/deleteProducts/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Status deleteProducts(@PathParam("id") int id) throws SQLException {
		return productdao.deleteProduct(id);
	}
	
	
	
	
	
	
	
	
	
	
	
 /*
	@Path("users/getUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Users getUser(Users user) throws SQLException {
		return userImpl.viewProfile(user);
	}

	@Path("users/updateUser")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status updateUser(Users user) throws SQLException {
		return userImpl.updateProfile(user);
	}
*/
	
	

	

}
