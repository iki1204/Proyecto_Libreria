package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ClientUser;
import model.AdminUser;
import model.SalesAgent;

public class connectionDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/libusers";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void registerUser(ClientUser user) throws SQLException {
        String sql = "INSERT INTO user(username, name, lastname, number, mail, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getLastname());
            stmt.setInt(4, user.getNumber());
            stmt.setString(5, user.getMail());
            stmt.setString(6, user.getPassword());

            stmt.executeUpdate();
        }
    }

    public static ClientUser loginClientUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClientUser user = new ClientUser();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setNumber(rs.getInt("number"));
                user.setMail(rs.getString("mail"));
                user.setPassword(rs.getString("password"));

                return user;
            }
        }

        return null;
    }

    public static AdminUser loginAdminUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM adminuser WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                AdminUser user = new AdminUser();
                user.setUsername(rs.getString("username"));
                user.setSecuritycode(rs.getInt("securitycode"));
                user.setPassword(rs.getString("password"));

                return user;
            }
        }

        return null;
    }

    public static SalesAgent loginSalesAgent(String username, String password) throws SQLException {
        String sql = "SELECT * FROM salesagent WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                SalesAgent user = new SalesAgent();
                user.setUsername(rs.getString("username"));
                user.setSecuritycode(rs.getInt("securitycode"));
                user.setPassword(rs.getString("password"));

                return user;
            }
        }

        return null;
    }
}