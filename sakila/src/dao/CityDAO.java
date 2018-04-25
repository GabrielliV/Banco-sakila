package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.City;
import util.ConnectionJDBC;

public class CityDAO {
    Connection connection;
    
    public CityDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    //Salvar
    public void save(City city) throws Exception {
        String SQL = "INSERT INTO CITY VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, city.getCity_id());
            p.setString(2, city.getCity());
            p.setInt(3, city.getCountry_id());
            p.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    // Atualizar
    public void update(City city) throws Exception {
        String SQL = "UPDATE CITY SET CITY=?, COUNTRY_ID=?, WHERE CITY_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, city.getCity());
           p.setInt(2, city.getCountry_id());
           p.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
           p.setInt(4, city.getCity_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Deletar
    public void delete(City city) throws Exception {
        String SQL = "DELETE FROM CITY WHERE CITY_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, city.getCity_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Encontrar pela ID
    public City findById(int id) {
        return new City();
    }
    // Encontrar tudo
    public List<City> findAll() throws Exception {
        List<City> list = new ArrayList<>();
       City objeto;
        String SQL = "SELECT * FROM CITY";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new City();
                objeto.setCity_id(rs.getInt("city_id"));
                objeto.setCity(rs.getString("city"));
                objeto.setCountry_id(rs.getInt("Country_id"));
                objeto.setLast_update(rs.getTimestamp("last_update"));
                //Inclui na lista
                list.add(objeto);
            }
            rs.close();
            p.close();
            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
        
    }
}
