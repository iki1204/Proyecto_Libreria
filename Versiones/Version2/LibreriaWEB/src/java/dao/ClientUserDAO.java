package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ClientUser;
import connectionDB.connectionDB;

public class ClientUserDAO {

    public static List<ClientUser> getAllClientUsers() throws SQLException {
        List<ClientUser> clientUsers = new ArrayList<>();

        String sql = "SELECT * FROM ClientUser";

        try (Connection conn = connectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ClientUser clientUser = new ClientUser();
                clientUser.setId(rs.getInt("id"));
                clientUser.setUsername(rs.getString("username"));
                clientUser.setName(rs.getString("name"));
                clientUser.setLastname(rs.getString("lastname"));
                clientUser.setNumber(rs.getInt("number"));
                clientUser.setMail(rs.getString("mail"));
                clientUser.setPassword(rs.getString("password"));

                clientUsers.add(clientUser);
            }
        }

        return clientUsers;
    }

    public static ClientUser getClientUserByUsername(String username) throws SQLException {
        ClientUser clientUser = null;

        String sql = "SELECT * FROM ClientUser WHERE username = ?";

        try (Connection conn = connectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                clientUser = new ClientUser();
                clientUser.setId(rs.getInt("id"));
                clientUser.setUsername(rs.getString("username"));
                clientUser.setName(rs.getString("name"));
                clientUser.setLastname(rs.getString("lastname"));
                clientUser.setNumber(rs.getInt("number"));
                clientUser.setMail(rs.getString("mail"));
                clientUser.setPassword(rs.getString("password"));
            }
        }

        return clientUser;
    }

    public static void addClientUser(ClientUser clientUser) throws SQLException {
        String sql = "INSERT INTO ClientUser (username, name, lastname, number, mail, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clientUser.getUsername());
            stmt.setString(2, clientUser.getName());
            stmt.setString(3, clientUser.getLastname());
            stmt.setInt(4, clientUser.getNumber());
            stmt.setString(5, clientUser.getMail());
            stmt.setString(6, clientUser.getPassword());

            stmt.executeUpdate();
        }
    }
}