package dao;

import java.sql.SQLException;

import com.model.Admin;
import com.model.Status;


public interface AdminDAO {
	//Status signUp(Users user) throws SQLException;

	//Users signIn(Users user) throws SQLException;

	Status signUp(Admin admin) throws SQLException;

	Admin signIn(Admin admin) throws SQLException;

	
}