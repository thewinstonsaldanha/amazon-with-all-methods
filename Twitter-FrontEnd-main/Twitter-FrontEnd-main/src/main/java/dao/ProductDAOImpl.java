package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Products;
import com.model.Status;

public class ProductDAOImpl implements ProductDAO {

    private static Connection con;

    static {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Products> getAllProducts() throws SQLException {
        List<Products> products = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()) {
            Products product = new Products(
                rs.getInt("productid"),
                rs.getString("productname"),
                rs.getInt("productprice"),
                rs.getString("productdescription"),
                rs.getInt("categoryid"), 
                rs.getString("productimage")
            );
            products.add(product);
        }
        
        // Close the ResultSet and Statement
        rs.close();
        st.close();
        
        return products;
    }


    


    @Override
    
    
    public Status addProduct(Products product) throws SQLException {
        String sql = "INSERT INTO products (productname, productprice, productdescription, categoryid, productimage) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, product.getProductname());
        pst.setInt(2, product.getProductprice());
        pst.setString(3, product.getProductdescription());
        pst.setInt(4, product.getCategoryid());
        pst.setString(5, product.getProductimage());
        
        int res = pst.executeUpdate();
        
        //pst.close(); // Close the PreparedStatement
        
        return new Status((res == 1)?true:false);
    }

    
    
 

    @Override
    public Status updateProduct(Products product) throws SQLException {
        String sql = "UPDATE products SET productname = ?, productprice = ?, productdescription = ?, categoryid = ?, productimage = ? WHERE productid = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, product.getProductname());
        pst.setInt(2, product.getProductprice());
        pst.setString(3, product.getProductdescription());
        pst.setInt(4, product.getCategoryid());
        pst.setString(5, product.getProductimage());
        pst.setInt(6, product.getProductid());
        
        int res = pst.executeUpdate();
        
        pst.close(); // Close the PreparedStatement
        
        return new Status(res == 1);
    }


    @Override
    public Status deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE productid = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setInt(1, productId);
        int res = pst.executeUpdate();
        
        Status status = new Status();
        if (res != 0) {
            status.setQueryStatus(true);
        } else {
            status.setQueryStatus(false);
        }
        
        // Close the PreparedStatement
        pst.close();
        
        return status;
    }
    
    
    public List<Products> viewMyProducts(int productid) throws SQLException {
        String sql = "SELECT productid, productname, productprice, productdescription, categoryid, productimage FROM products WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, productid);
        ResultSet rs = pst.executeQuery();
        List<Products> productList = new ArrayList<>();
        while (rs.next()) {
            Products product = new Products(
                rs.getInt(1), // productid
                rs.getString(2), // productname
                rs.getInt(3), // productprice
                rs.getString(4), // productdescription
                rs.getInt(5), // categoryid
                rs.getString(6) // productimage
            );
            
        }
        
        
        
        return productList;
    }




}