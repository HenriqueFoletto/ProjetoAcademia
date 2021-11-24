/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Professor;
import academia.entidade.Treino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Henrique
 */
public class ProfessorDaoImpl implements ProfessorDao{
    
    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvar(Object object) throws Exception {
       Professor professor = (Professor) object;
        String instrucao = "INSERT INTO professor (nomeprofessor, idtreino) VALUES(?, ?)";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao, Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, professor.getNomeProfessor());
            preparaInstrucao.setInt(2, professor.getTreino().getIdtreino());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            professor.setIdprofessor(resultado.getInt(1));
        }catch (Exception e) {
            System.out.println("erro ao salvar professor" + e.getMessage());
        }finally{
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
        Professor professor = (Professor) object;
       String instrucao = "UPDATE professor SET nomeprofessor = ? WHERE idprofessor = ?";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, professor.getNomeProfessor());
            preparaInstrucao.setInt(2, professor.getIdprofessor());    
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao alterar o nomeprofessor" + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
         try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement("DELETE FROM professor WHERE idprofessor = ?");
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        }catch (Exception e){
            System.out.println("erro ao excluir professor" + e.getMessage());
        }finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }   

    @Override
    public Professor pesquisarPeloProfessor(int idprofessor) throws Exception {
        String consulta = "SELECT pr.*, t.nometreino t_treino"
                        + " FROM professor pr join treino t"
                        + " on pr.idtreino = t.idtreino WHERE idprofessor = ?";
        Professor professor = null;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setInt(1, idprofessor);
            resultado = preparaInstrucao.executeQuery();
            Treino treino;
            if (resultado.next()) {
                professor = new Professor(resultado.getString("nomeprofessor"));
                professor.setIdprofessor(idprofessor);
                treino = new Treino(
                        resultado.getString("t_nometreino"));
                professor.setTreino(treino);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisar pelo IdProfessor " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return professor;
    }

    @Override
    public List<Professor> pesquisarPorNome(String nomeProfessor) throws Exception {
        String consulta = "SELECT pr.*, t.nometreino t_treino"
                        + " FROM professor pr join treino t"
                        + " on pr.idtreino = t.idtreino WHERE idprofessor LIKE ?";
        List<Professor> professores = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, "%" + nomeProfessor + "%");
            resultado = preparaInstrucao.executeQuery();
            Professor professor;
            Treino treino;
            while(resultado.next()){
                professor = new Professor();
                professor.setIdprofessor(resultado.getInt("idprofessor"));
                professor.setNomeProfessor(resultado.getString("nomeprofessor"));
                professores.add(professor);
                treino = new Treino(
                        resultado.getString("t_nometreino"));
                professor.setTreino(treino);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisar nomeProfessor " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return professores;
    }
}
  

