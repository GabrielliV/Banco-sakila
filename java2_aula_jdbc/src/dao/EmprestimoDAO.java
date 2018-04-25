package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Editora;
import model.Emprestimo;
import model.Estudante;
import model.Livro;
import util.ConnectionJDBC;

public class EmprestimoDAO {
    Connection connection;
    
    public EmprestimoDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }
    //Salvar
    public void save(Emprestimo emprestimo) throws Exception {
        String SQL = "INSERT INTO EMPRESTIMO VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, emprestimo.getEmprestimo_id());
            p.setInt(2, emprestimo.getLivro().getLivro_id());
            p.setInt(3, emprestimo.getEstudante().getEstudante_id());
            p.setDate(4, new java.sql.Date(emprestimo.getData_retirada().getTime()));
            p.setDate(5, new java.sql.Date(emprestimo.getData_devolucao().getTime()));
            p.setDate(6, new java.sql.Date(emprestimo.getData_entrega().getTime()));
            p.setString(7, emprestimo.getStatus());
            p.execute();
      } catch (SQLException ex) {
          throw new Exception(ex);
      }
    }
    // Atualizar
    public void update(Emprestimo emprestimo) throws Exception {
        String SQL = "UPDATE EMPRESTIMO SET LIVRO_ID=?, ESTUDANTE_ID=?, DATA_RETIRADA=?, DATA_DEVOLUCAO=?, DATA_ENTREGA=?, STATUS=? WHERE EMPRESTIMO_ID=?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setString(1, emprestimo.getStatus());
           p.setDate(2, new java.sql.Date(emprestimo.getData_entrega().getTime()));
           p.setDate(3, new java.sql.Date(emprestimo.getData_devolucao().getTime()));
           p.setDate(4, new java.sql.Date(emprestimo.getData_retirada().getTime()));
           p.setInt(5, emprestimo.getEstudante().getEstudante_id());
           p.setInt(6, emprestimo.getLivro().getLivro_id());
           p.setInt(7, emprestimo.getEmprestimo_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Deletar
    public void delete(Emprestimo emprestimo) throws Exception {
        String SQL = "DELETE FROM EMPRESTIMO WHERE EMPRESTIMO_ID = ?";
        try {
           PreparedStatement p = connection.prepareStatement(SQL); 
           p.setInt(1, emprestimo.getEmprestimo_id());
           p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    // Encontrar pela ID
    public Emprestimo findById(int id) throws Exception {
        Emprestimo objeto = new Emprestimo();
        String SQL = "SELECT EMPRESTIMO.*, LIVRO.LIVRO_ID, ESTUDANTE.ESTUDANTE_ID "
                + "FROM EMPRESTIMO "
                + "INNER JOIN LIVRO ON (LIVRO.LIVRO_ID = EMPRESTIMO.LIVRO_ID) "
                + "INNER JOIN ESTUDANTE ON (ESTUDANTE.ESTUDANTE_ID = EMPRESTIMO.ESTUDANTE_ID) "
                + "WHERE EMPRESTIMO_ID = ?";
        
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Emprestimo();
                objeto.setEmprestimo_id(rs.getInt("emprestimo_id"));
                objeto.setData_retirada(rs.getDate("data_retirada"));
                objeto.setData_devolucao(rs.getDate("data_devolucao"));
                objeto.setData_entrega(rs.getDate("data_entrega"));
                objeto.setStatus(rs.getString("status"));
                
                Livro livro = new Livro();
                livro.setLivro_id(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno( rs.getInt("ano") );
                livro.setDescricao( rs.getString("descricao") );
                
                Estudante estudante = new Estudante();
                estudante.setEstudante_id(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setCurso(rs.getString("curso"));
                estudante.setData_matricula(rs.getDate("data_matricula"));
                estudante.setStatus(rs.getString("status"));
                
                objeto.setLivro(livro);
                objeto.setEstudante(estudante);
            }
            rs.close();
            p.close();
            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        //Retorna a lista
        return objeto;
    }
    // Encontrar tudo
    public List<Emprestimo> findAll() throws Exception {
        List<Emprestimo> list = new ArrayList<>();
        Emprestimo objeto;
        String SQL = "SELECT EMPRESTIMO.*, LIVRO.LIVRO_ID, ESTUDANTE.ESTUDANTE_ID "
                + "FROM EMPRESTIMO "
                + "INNER JOIN LIVRO ON (LIVRO.LIVRO_ID = EMPRESTIMO.LIVRO_ID) "
                + "INNER JOIN ESTUDANTE ON (ESTUDANTE.ESTUDANTE_ID = EMPRESTIMO.ESTUDANTE_ID) "
                + "WHERE EMPRESTIMO_ID = ?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Emprestimo();
                objeto.setEmprestimo_id(rs.getInt("emprestimo_id"));
                objeto.setData_retirada(rs.getDate("data_retirada"));
                objeto.setData_devolucao(rs.getDate("data_devolucao"));
                objeto.setData_entrega(rs.getDate("data_entrega"));
                objeto.setStatus(rs.getString("status"));
                
                Livro livro = new Livro();
                livro.setLivro_id(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno( rs.getInt("ano") );
                livro.setDescricao( rs.getString("descricao") );
                
                Estudante estudante = new Estudante();
                estudante.setEstudante_id(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setCurso(rs.getString("curso"));
                estudante.setData_matricula(rs.getDate("data_matricula"));
                estudante.setStatus(rs.getString("status"));
                
                objeto.setLivro(livro);
                objeto.setEstudante(estudante);
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
