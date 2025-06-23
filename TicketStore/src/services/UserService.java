package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import utils.DBConnection;
import utils.PasswordUtils;

public class UserService {
    public static User createUser(String username, String password, String email) {
       try (Connection connection = DBConnection.getConnection()) {
            User foundUser = getUserByEmail(email);
            if (foundUser != null) throw new Exception("Email already in use");
            else {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
                ps.setString(1, username);
                ps.setString(2, PasswordUtils.hashPassword(password));
                ps.setString(3, email);
                ps.executeUpdate();
                return new User(username, password, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User verifyUser(String email, String password) {
        try (Connection connection = DBConnection.getConnection()) {
            User foundUser = getUserByEmail(email);

            if (foundUser != null) {
                String storedPassword = foundUser.getPassword();
                if (PasswordUtils.checkPassword(password, storedPassword)) return foundUser;
                throw new Exception("Invalid password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
