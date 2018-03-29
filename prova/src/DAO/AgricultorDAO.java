package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Agricultor;
import util.ConnectionJDBC;

public class AgricultorDAO {
    Connection connection;
    
    public AgricultorDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    
    public void save(Agricultor agricultor) throws Exception {
        String SQL = "INSERT INTO CADASTROAGRICULTOR VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, agricultor.getId_agricultor());
            p.setString(2, agricultor.getNome());
            p.setDate(3, new java.sql.Date(agricultor.getData_cadastro().getTime()));
            p.setString(4, agricultor.getMunicipio());
            p.setString(5, agricultor.getLocalidade());
            p.setString(6, agricultor.getProdutos());
            p.setInt(7, agricultor.getStatus());
            p.setString(8, agricultor.getTelefone());
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    
    public void update(Agricultor agricultor) throws Exception {
        String SQL = "UPDATE CADASTROAGRICULTOR SET NOME=?, DATA_CADASTRO=?, MUNICIPIO=?, LOCALIDADE=?, PRODUTOS=?, STATUS=?, TELEFONE=? WHERE ID_AGRICULTOR=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL);
           p.setString(1, agricultor.getNome());
           p.setDate(2, new java.sql.Date(agricultor.getData_cadastro().getTime()));
           p.setString(3, agricultor.getMunicipio());
           p.setString(4, agricultor.getLocalidade());
           p.setString(5, agricultor.getProdutos());
           p.setInt(6, agricultor.getStatus());
           p.setString(7, agricultor.getTelefone());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Agricultor agricultor) throws Exception {
        String SQL = "DELETE FROM CADASTROAGRICULTOR WHERE ID_AGRICULTOR = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, agricultor.getId_agricultor());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
   
    
    public List<Agricultor> findAll() throws Exception {
         //Lista para manter os valores do ResulSet
        List<Agricultor> list = new ArrayList<>();
        Agricultor objeto;
        String SQL = "SELECT * FROM CADASTROAGRICULTOR";
        try {
            //Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            //Executa a SQL e mantem os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            //Navega nos registros que estão no rs
            while (rs.next()) {
                //Instancia a classe e informa os valores do BD
                objeto = new Agricultor();
                objeto.setId_agricultor(rs.getInt("id_agricultor"));
                objeto.setNome(rs.getString("nome"));
                objeto.setData_cadastro(rs.getDate("data_cadastro"));
                objeto.setMunicipio(rs.getString("municipio"));
                objeto.setLocalidade(rs.getString("localidade"));
                objeto.setProdutos(rs.getString("produtos"));
                objeto.setStatus(rs.getInt("status"));
                objeto.setTelefone(rs.getString("telefone"));
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
