package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Editora;
import model.Estudante;
import util.ConnectionJDBC;

public class EstudanteDAO {
    Connection connection;
    
    public EstudanteDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    
    public void save(Estudante estudante) throws Exception {
        String SQL = "INSERT INTO ESTUDANTE VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, estudante.getEstudante_id());
            p.setString(2, estudante.getNome());
            p.setString(3, estudante.getCurso());
            p.setDate(4, new java.sql.Date(estudante.getData_matricula().getTime()));
            p.setString(5, estudante.getStatus());
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    public void update(Estudante estudante) throws Exception {
        String SQL = "UPDATE ESTUDANTE SET NOME=?, CURSO=?, DATA_MATRICULA=?, STATUS=? WHERE ESTUDANTE_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, estudante.getStatus());
           p.setDate(2, new java.sql.Date(estudante.getData_matricula().getTime()));
           p.setString(3, estudante.getCurso());
           p.setString(4, estudante.getNome());
           p.setInt(5, estudante.getEstudante_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    public void delete(Estudante estudante) throws Exception {
        String SQL = "DELETE FROM ESTUDANTE WHERE ESTUDANTE_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, estudante.getEstudante_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    public Estudante findById(int id) {
        return new Estudante();
    }
    public List<Estudante> findAll() throws Exception {
        List<Estudante> list = new ArrayList<>();
        Estudante objeto;
        String SQL = "SELECT * FROM ESTUDANTE";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Estudante();
                objeto.setEstudante_id(rs.getInt("estudante_id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setCurso(rs.getString("curso"));
                objeto.setData_matricula(rs.getDate("data_matricula"));
                objeto.setStatus(rs.getString("status"));
                //Inclui na lista
                list.add(objeto);
            }
            rs.close();
            p.close();
            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        //Retorna a lista
        return list;
        
    }
}
