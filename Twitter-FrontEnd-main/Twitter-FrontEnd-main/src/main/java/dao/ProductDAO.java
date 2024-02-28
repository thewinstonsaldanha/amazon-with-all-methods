package dao;

import java.sql.SQLException;
import java.util.List;
import com.model.Products;
import com.model.Status;

public interface ProductDAO {
	Status addProduct(Products product) throws SQLException;   //post
	Status updateProduct(Products product) throws SQLException; //put
	Status deleteProduct(int productId) throws SQLException;    //delete
    List<Products> getAllProducts() throws SQLException; //get method
   
   
   
   
}