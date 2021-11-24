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
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrique
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDao clienteDao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

//    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Professor professor = new Professor();
        professor.setIdprofessor(3);
        cliente = new Cliente("Pedro", "Pedro@", "23167", 132357943, 20,
                "Ipiranga", "80kg", "1.87m", null);
        cliente.setProfessor(professor);
        clienteDao.salvar(cliente);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarClienteBD();
        Professor professor = new Professor();
        professor.setIdprofessor(4);
        cliente.setNome("Henrique");
        cliente.setEmail("Henrique@");
        cliente.setSenha("54314");
        cliente.setEndereco("Bela Vista");
        cliente.setPeso("65Kg");
        cliente.setAltura("1.83m");
        clienteDao.alterar(cliente);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarClienteBD();
        System.out.println("Matricula " + cliente.getMatricula());
        clienteDao.excluir(cliente.getMatricula());
    }

//    @Test
    public void testLogarSucesso() throws Exception {
        System.out.println("logar sucesso");
        buscarClienteBD();
        Cliente clienteLogado = clienteDao.logar(cliente.getEmail(), cliente.getSenha());
        assertNotNull(clienteLogado);
    }

//    @Test
    public void testLogarFalso() throws Exception {
        System.out.println("logar com erro");
        Cliente clienteLogado = clienteDao.logar("", "");
        assertNull(clienteLogado);
    }

    @Test
    public void testPesquisarPorMatricula() throws Exception {
        System.out.println("pesquisarPorMatricula");
        buscarClienteBD();

        Cliente ClienteNovo = clienteDao.pesquisarPorMatricula(cliente.getMatricula());
        mostrarCliente(ClienteNovo);
    }

//    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("PesquisarPorNome");
        buscarClienteBD();

        List<Cliente> clientes = clienteDao.pesquisarPorNome(cliente.getNome());
        for (Cliente cliente : clientes) {
            mostrarCliente(cliente);
        }
    }

    private void mostrarCliente(Cliente client) {
        System.out.println("Matricula: " + client.getMatricula());
        System.out.println("Nome: " + client.getNome());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Senha: " + client.getSenha());
        System.out.println("cpf: " + client.getCpf());
        System.out.println("Idade: " + client.getIdade());
        System.out.println("Endereco: " + client.getEndereco());
        System.out.println("Peso: " + client.getPeso());
        System.out.println("Altura: " + client.getAltura());
        System.out.println("UltimoAcesso: " + client.getUltimoAcesso());
        System.out.println("");
    }

    private Cliente buscarClienteBD() throws Exception {
        String consulta = "SELECT c.*, pr.nomeprofessor pr_nomeprofessor"
                + " FROM cliente c  join professor pr"
                + " on c.idprofessor = pr.idprofessor";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        Professor professor;
        if (resultado.next()) {
            cliente = new Cliente();
            cliente.setNome(resultado.getString("nome"));
            cliente.setEmail(resultado.getString("email"));
            cliente.setSenha(resultado.getString("senha"));
            cliente.setCpf(resultado.getInt("cpf"));
            cliente.setIdade(resultado.getInt("idade"));
            cliente.setEndereco(resultado.getString("endereco"));
            cliente.setPeso(resultado.getString("peso"));
            cliente.setAltura(resultado.getString("altura"));
            cliente.setUltimoAcesso(resultado.getDate("ultimoacesso"));
            cliente.setMatricula(resultado.getInt("matricula"));
        } else {
            testSalvar();
        }
        return cliente;
    }

}
