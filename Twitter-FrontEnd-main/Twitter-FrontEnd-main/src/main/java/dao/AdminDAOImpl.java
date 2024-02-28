package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Status;
import com.model.Admin;

public class AdminDAOImpl implements AdminDAO {

    private Connection connection;

    public AdminDAOImpl() {
        connection = DBUtil.getConnection();
        System.out.println("Admin impl's connection= " + connection.hashCode());
    }

    @Override
    public Status signUp(Admin admin) throws SQLException {
    	String sql = "insert into admin (ADMIN_USERNAME,ADMIN_EMAIL ,ADMIN_PASSWORD ) values(?,?,?)";
        System.out.println(admin.getAdmin_username());
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, admin.getAdmin_username());
            pst.setString(2, admin.getAdmin_email());
            pst.setString(3, admin.getAdmin_password());
            pst.executeUpdate();
            Status s = new Status();
            s.setQueryStatus(true);
            return s;
        }
    }
    
   
    
  

    @Override
    public Admin signIn(Admin admin) throws SQLException {
        String sql = "SELECT * FROM admin WHERE admin_email = ? AND admin_password";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, admin.getAdmin_email());
            pst.setString(2, admin.getAdmin_password());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int adminId = rs.getInt("admin_id");
                    String adminUsername = rs.getString("admin_username");
                    String adminEmail = rs.getString("admin_email");
                    String adminPassword = rs.getString("admin_password");
                    return new Admin(adminId, adminUsername, adminPassword, adminEmail);
                }
            }
        }
        return null;
    }
}