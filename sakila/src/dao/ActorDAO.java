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
import util.ConnectionJDBC;

public class ActorDAO {
    Connection connection;
    
    public ActorDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    //Salvar
    public void save(Actor actor) throws Exception {
        String SQL = "INSERT INTO ACTOR VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, actor.getActor_id());
            p.setString(2, actor.getFirst_name());
            p.setString(3, actor.getLast_name());
            p.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    // Atualizar
    public void update(Actor actor) throws Exception {
        String SQL = "UPDATE ACTOR SET FIRST_NAME=?, LAST_NAME=?, LAST_UPDATE=? WHERE ACTOR_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, actor.getFirst_name());
           p.setString(2, actor.getLast_name());
           p.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
           p.setInt(4, actor.getActor_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Deletar
    public void delete(Actor actor) throws Exception {
        String SQL = "DELETE FROM ACTOR WHERE ACTOR_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, actor.getActor_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Encontrar pela ID
    public Actor findById(int id) {
        return new Actor();
    }
    // Encontrar tudo
    public List<Actor> findAll() throws Exception {
        List<Actor> list = new ArrayList<>();
       Actor objeto;
        String SQL = "SELECT * FROM ACTOR";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Actor();
                objeto.setActor_id(rs.getInt("actor_id"));
                objeto.setFirst_name(rs.getString("first_name"));
                objeto.setLast_name(rs.getString("last_name"));
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
