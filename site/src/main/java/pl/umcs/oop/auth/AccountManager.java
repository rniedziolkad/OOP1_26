package pl.umcs.oop.auth;

import pl.umcs.oop.database.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    public void register (String username, String password){
        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";

        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql) ){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authenticate (String username, String password) {
        String sql = "SELECT password FROM account WHERE (username = ?)";

        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)){
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String password_DB = rs.getString("password");
                return password_DB.equals(password);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Account getAccount (int id) {
        String sql = "SELECT username FROM account WHERE id = ?";

        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String username = rs.getString("username");
                return new Account(id, username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
