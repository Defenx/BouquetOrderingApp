package org.example.dao;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.example.entity.Bouquet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BouquetDAO {
    private final HikariDataSource hikariDataSource;

    public List<Bouquet> getAllBouquets() throws SQLException {

        List<Bouquet> bouquets = new ArrayList<>();
        try (Statement statement = hikariDataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bouquets");

            while (resultSet.next()) {
                Bouquet bouquet = Bouquet.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getBigDecimal("price"))
                        .photoUrl(resultSet.getString("photo_url"))
                        .description(resultSet.getString("description"))
                        .build();
                bouquets.add(bouquet);
            }
        }

        return bouquets;
    }

    public void addBouquet(Bouquet bouquet) throws SQLException {
        String sql = "INSERT INTO bouquets (name, price, photo_url, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = hikariDataSource.getConnection().prepareStatement(sql)) {
            statement.setString(1, bouquet.getName());
            statement.setBigDecimal(2, bouquet.getPrice());
            statement.setString(3, bouquet.getPhotoUrl());
            statement.setString(4, bouquet.getDescription());
            statement.executeUpdate();
        }
    }
}