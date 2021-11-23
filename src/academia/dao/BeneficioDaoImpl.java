/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Beneficio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Henrique
 */
public class BeneficioDaoImpl implements BeneficioDao{
    
    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvar(Object object) throws Exception {
       Beneficio beneficio = (Beneficio) object;
        String instrucao = "INSERT INTO beneficio (beneficio, idplano) VALUES(?, ?)";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao, Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, beneficio.getBeneficio());
            preparaInstrucao.setInt(2, beneficio.getPlano().getIdplano());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            beneficio.setIdbeneficio(resultado.getInt(1));
        }catch (Exception e) {
            System.out.println("erro ao salvar beneficio" + e.getMessage());
        }finally{
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
       Beneficio beneficio = (Beneficio) object;
       String instrucao = "UPDATE beneficio SET beneficio = ? WHERE idbeneficio = ?";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, beneficio.getBeneficio());        
            preparaInstrucao.setInt(2, beneficio.getIdbeneficio());    
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao alterar beneficio" + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement("DELETE FROM beneficio WHERE idbeneficio = ?");
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        }catch (Exception e){
            System.out.println("erro ao excluir beneficio" + e.getMessage());
        }finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }
}
