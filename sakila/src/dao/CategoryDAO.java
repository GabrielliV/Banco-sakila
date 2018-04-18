package dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Actor;
import model.Category;
import util.ConnectionJDBC;

public class CategoryDAO {
    Connection connection;
    
    public CategoryDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    //Salvar
    public void save(Category category) throws Exception {
        String SQL = "INSERT INTO CATEGORY VALUES (?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, category.getCategory_id());
            p.setString(2, category.getName());
            p.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    // Atualizar
    public void update(Category category) throws Exception {
        String SQL = "UPDATE CATEGORY SET NAME=?, LAST_UPDATE=? WHERE CATEGORY_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, category.getName());
           p.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
           p.setInt(3, category.getCategory_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Deletar
    public void delete(Category category) throws Exception {
        String SQL = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, category.getCategory_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Encontrar pela ID
    public Category findById(int id) {
        return new Category();
    }
    // Encontrar tudo
    public List<Category> findAll() throws Exception {
        List<Category> list = new ArrayList<>();
       Category objeto;
        String SQL = "SELECT * FROM CATEGORY";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Category();
                objeto.setCategory_id(rs.getInt("category_id"));
                objeto.setName(rs.getString("name"));
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
