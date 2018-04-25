package dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import util.ConnectionJDBC;

public class AddressDAO {
    Connection connection;
    
    public AddressDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    //Salvar
    public void save(Address address) throws Exception {
        String SQL = "INSERT INTO ADDRESS VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, address.getAddress_id());
            p.setString(2, address.getAddress());
            p.setString(3, address.getAddress2());
            p.setString(4, address.getDistrict());
            p.setInt(5, address.getCity_id());
            p.setInt(6, address.getPostal_code());
            p.setInt(7, address.getPhone());
            p.setTimestamp(8, new java.sql.Timestamp(new Date().getTime()));
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    // Atualizar
    public void update(Address address) throws Exception {
        String SQL = "UPDATE ACTOR SET ADDRESS=?, ADDRESS2=?, DISTRICT=?, CITY_ID=?, PORTAL_CODE=?, "
                + "PHONE=?, LAST_UPDATE=? WHERE ADDRESS_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, address.getAddress());
           p.setString(2, address.getAddress2());
           p.setString(3, address.getDistrict());
           p.setInt(4, address.getCity_id());
           p.setInt(5, address.getPostal_code());
           p.setInt(6, address.getPhone());
           p.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
           p.setInt(8, address.getAddress_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Deletar
    public void delete(Address address) throws Exception {
        String SQL = "DELETE FROM ADRESS WHERE ADRESS_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, address.getAddress_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Encontrar pela ID
    public Address findById(int id) {
        return new Address();
    }
    // Encontrar tudo
    public List<Address> findAll() throws Exception {
        List<Address> list = new ArrayList<>();
       Address objeto;
        String SQL = "SELECT * FROM ADDRESS";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Address();
                objeto.setAddress_id(rs.getInt("address_id"));
                objeto.setAddress(rs.getString("address"));
                objeto.setAddress2(rs.getString("address2"));
                objeto.setCity_id(rs.getInt("city_id"));
                objeto.setPostal_code(rs.getInt("postal_code"));
                objeto.setPhone(rs.getInt("phone"));
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
