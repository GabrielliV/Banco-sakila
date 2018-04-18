package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Country;
import util.ConnectionJDBC;

public class CountryDAO {
    Connection connection;
    
    public CountryDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    //Salvar
    public void save(Country country) throws Exception {
        String SQL = "INSERT INTO COUNTRY VALUES (?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, country.getCountry_id());
            p.setString(2, country.getCountry());
            p.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    // Atualizar
    public void update(Country country) throws Exception {
        String SQL = "UPDATE COUNTRY SET COUNTRY=?, LAST_UPDATE=? WHERE COUNTRY_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, country.getCountry());
           p.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
           p.setInt(3, country.getCountry_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Deletar
    public void delete(Country country) throws Exception {
        String SQL = "DELETE FROM COUNTRY WHERE COUNTRY_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, country.getCountry_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Encontrar pela ID
    public Country findById(int id) {
        return new Country();
    }
    // Encontrar tudo
    public List<Country> findAll() throws Exception {
        List<Country> list = new ArrayList<>();
       Country objeto;
        String SQL = "SELECT * FROM COUNTRY";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Country();
                objeto.setCountry_id(rs.getInt("country_id"));
                objeto.setCountry(rs.getString("Country"));
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
