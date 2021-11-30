/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;


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
public class TreinoDaoImpl implements TreinoDao{

    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;
    
    @Override
    public void salvar(Object object) throws Exception {
        Treino treino = (Treino) object;
        String instrucao = "INSERT INTO treino (nometreino) VALUES(?)";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao, Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, treino.getNomeTreino());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            treino.setIdtreino(resultado.getInt(1));
        }catch (Exception e) {
            System.out.println("erro ao salvar plano" + e.getMessage());
        }finally{
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
         Treino treino = (Treino) object;
       String instrucao = "UPDATE treino SET nometreino = ? WHERE idtreino = ?";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, treino.getNomeTreino());
            preparaInstrucao.setInt(2, treino.getIdtreino());    
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao alterar o nometreino" + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement("DELETE FROM treino WHERE idtreino = ?");
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        }catch (Exception e){
            System.out.println("erro ao excluir treino" + e.getMessage());
        }finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public List<Treino> pesquisarTreinos() throws Exception {
       String instrucao = "SELECT * FROM treino";
      List<Treino> treinos = new ArrayList<>();
      try{
          conexao = FabricaConexao.abrirConexao();
          preparaInstrucao = conexao.prepareStatement(instrucao); 
          resultado = preparaInstrucao.executeQuery();
          Treino treino;
          while (resultado.next()){
             treino = new Treino();
             treino.setIdtreino(resultado.getInt("idtreino"));
             treino.setNomeTreino(resultado.getString("nometreino"));
             treinos.add(treino);
          }
      } catch (Exception e){
         System.err.println("erro ao pesquisar os treinos " + e.getMessage());
      } finally {
          conexao.close();
          preparaInstrucao.close();
          resultado.close();
      }
      
      return treinos;
    }
}

