/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Cliente;
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
            preparaInstrucao.setInt(4, cliente.getCpf());
            preparaInstrucao.setInt(5, cliente.getIdade());
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
       String instrucao = "UPDATE cliente SET nome = ?, email = ?, endereco = ?, peso = ?, altura = ? WHERE matricula = ?";
        try{
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, cliente.getNome());
            preparaInstrucao.setString(2, cliente.getEmail());
            preparaInstrucao.setString(3, cliente.getEndereco());
            preparaInstrucao.setString(4, cliente.getPeso());
            preparaInstrucao.setString(5, cliente.getAltura());
            preparaInstrucao.setInt(6, cliente.getMatricula());
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
        String consulta = "SELECT * FROM cliente WHERE matricula = ?";
        Cliente cliente = null;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setInt(1, matricula);
            resultado = preparaInstrucao.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente(resultado.getString("nome"),
                resultado.getString("email"), resultado.getString("senha"),
                resultado.getInt("cpf"), resultado.getInt("idade"),
                resultado.getString("endereco"), resultado.getString("peso"),
                resultado.getString("altura"), resultado.getDate("ultimoacesso"));
                cliente.setMatricula(matricula);
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
       String consulta = "SELECT * FROM cliente WHERE nome LIKE ?";
        List<Cliente> clientes = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, "%" + nome + "%");
            resultado = preparaInstrucao.executeQuery();
            Cliente cliente;
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setMatricula(resultado.getInt("matricula"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email")); 
                cliente.setSenha(resultado.getString("senha"));
                cliente.setCpf(resultado.getInt("cpf"));
                cliente.setIdade(resultado.getInt("idade"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setPeso(resultado.getString("peso"));
                cliente.setAltura(resultado.getString("altura"));
                cliente.setUltimoAcesso(resultado.getDate("ultimoacesso"));
                clientes.add(cliente);
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

    @Override
    public Cliente logar(String login, String senha) throws Exception {
        String consulta = "SELECT * FROM cliente WHERE email = ? and senha = ? ";
        Cliente cliente = null;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, login);
            preparaInstrucao.setString(2, senha);
            resultado = preparaInstrucao.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setMatricula(resultado.getInt("matricula"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(login);
                cliente.setSenha(senha);
                cliente.setUltimoAcesso(resultado.getDate("ultimoacesso"));
            }
        } catch (Exception e) {
            System.out.println("erro ao logar cliente " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return cliente;
    }
}