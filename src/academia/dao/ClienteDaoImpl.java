/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Cliente;
import academia.entidade.Professor;
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
public class ClienteDaoImpl implements ClienteDao{
    
    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvar(Object object) throws Exception {
        Cliente cliente = (Cliente) object;
        String instrucao = "INSERT INTO cliente (nome, email, senha, cpf, "
                + " idade, endereco, peso, altura, idprofessor) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao, Statement.RETURN_GENERATED_KEYS);         
            preparaInstrucao.setString(1, cliente.getNome());
            preparaInstrucao.setString(2, cliente.getEmail());
            preparaInstrucao.setString(3, cliente.getSenha());
            preparaInstrucao.setString(4, cliente.getCpf());
            preparaInstrucao.setString(5, cliente.getIdade());
            preparaInstrucao.setString(6, cliente.getEndereco());
            preparaInstrucao.setString(7, cliente.getPeso());
            preparaInstrucao.setString(8, cliente.getAltura());
            preparaInstrucao.setInt(9, cliente.getProfessor().getIdprofessor());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            int id = resultado.getInt(1);
            cliente.setMatricula(id);
        }catch (Exception e) {
            System.out.println("erro ao salvar cliente" + e.getMessage());
        }finally{
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
       Cliente cliente = (Cliente) object;
       String instrucao = "UPDATE cliente SET nome = ?, email = ?, idade = ?, endereco = ?, peso = ?, altura = ? WHERE matricula = ?";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, cliente.getNome());
            preparaInstrucao.setString(2, cliente.getEmail());
            preparaInstrucao.setString(3, cliente.getIdade());
            preparaInstrucao.setString(4, cliente.getEndereco());
            preparaInstrucao.setString(5, cliente.getPeso());
            preparaInstrucao.setString(6, cliente.getAltura());
            preparaInstrucao.setInt(7, cliente.getMatricula());
            preparaInstrucao.executeUpdate();        
        } catch (Exception e) {
            System.out.println("erro ao alterar dados do cliente" + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement("DELETE FROM cliente WHERE matricula = ?");
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        }catch (Exception e){
            System.out.println("erro ao excluir cliente" + e.getMessage());
        }finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public Cliente pesquisarPorMatricula(int matricula) throws Exception {
        String consulta = "SELECT c.*, pr.nomeprofessor pr_nomeprofessor"
                + " FROM cliente c  join professor pr"
                + " on c.idprofessor = pr.idprofessor WHERE c.matricula = ?";
        
        Cliente cliente = null;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setInt(1, matricula);
            resultado = preparaInstrucao.executeQuery();
            Professor professor;
            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setMatricula(matricula);
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email")); 
                cliente.setSenha(resultado.getString("senha"));
                cliente.setCpf(resultado.getString("cpf")); 
                cliente.setIdade(resultado.getString("idade"));
                cliente.setEndereco(resultado.getString("endereco")); 
                cliente.setPeso(resultado.getString("peso"));
                cliente.setAltura(resultado.getString("altura"));
                professor = new Professor(
                      resultado.getString("pr_nomeprofessor"),
                      resultado.getString("email"),
                      resultado.getString("senha"));
                cliente.setProfessor(professor);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisar matricula " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return cliente;
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome) throws Exception {
       Cliente cliente; 
       String consulta = "SELECT c.*, pr.nomeprofessor pr_nomeprofessor"
                + " FROM cliente c  join professor pr"
                + " on c.idprofessor = pr.idprofessor WHERE c.nome LIKE ?";
        List<Cliente> clientes = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, "%" + nome + "%");
            resultado = preparaInstrucao.executeQuery();
            Professor professor;
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setMatricula(resultado.getInt("matricula"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email")); 
                cliente.setSenha(resultado.getString("senha"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setIdade(resultado.getString("idade"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setPeso(resultado.getString("peso"));
                cliente.setAltura(resultado.getString("altura"));
                clientes.add(cliente);
                professor = new Professor(
                      resultado.getString("pr_nomeprofessor"),
                      resultado.getString("email"),
                      resultado.getString("senha"));
                cliente.setProfessor(professor);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisar por nome " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return clientes;
    }     
}