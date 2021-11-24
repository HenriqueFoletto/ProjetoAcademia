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
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrique
 */
public class ProfessorDaoImplTest {
    
    private Professor professor;
    private ProfessorDao professorDao;
    
    public ProfessorDaoImplTest() {
        professorDao = new ProfessorDaoImpl();
    }

//    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Treino treino = new Treino();
        treino.setIdtreino(4);
        professor = new Professor("Vagner", "Vagner@" , "43121", null);
        professor.setTreino(treino);
        professorDao.salvar(professor);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarProfessorBD();
        professor.setNomeProfessor("Rafael");
        professor.setEmail("Rafael@");
        professor.setSenha("76332");
        professorDao.alterar(professor);
        
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarProfessorBD();
        System.out.println("IdProfessor " + professor.getIdprofessor());
        professorDao.excluir(professor.getIdprofessor());
    }

//    @Test
    public void testPesquisarPeloProfessor() throws Exception {
        System.out.println("pesquisarPeloProfessor");
        buscarProfessorBD();
        Professor professorNovo = professorDao.pesquisarPeloProfessor(professor.getIdprofessor());
        mostrarProfessor(professorNovo);
    }

//    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarProfessorBD();
        List<Professor> professores = professorDao.pesquisarPorNome(professor.getNomeProfessor());
        for (Professor professor : professores) {
            mostrarProfessor(professor);
        }
    }
    
    private void mostrarProfessor(Professor prof) {
        System.out.println("Id: " + prof.getIdprofessor());
        System.out.println("Nome: " + prof.getNomeProfessor());
        System.out.println("Email: " + prof.getEmail());
        System.out.println("Senha: " + prof.getSenha());
        System.out.println("UltimoAcesso: " + prof.getUltimoAcesso());
        System.out.println("");
    }
    
    public Professor buscarProfessorBD() throws Exception {
        String consulta = "SELECT pr.*, t.nometreino t_treino"
                        + " FROM professor pr join treino t"
                        + " on pr.idtreino = t.idtreino";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        if(resultado.next()){
            professor = new Professor();
            professor.setNomeProfessor(resultado.getString("nomeprofessor"));
            professor.setEmail(resultado.getString("email"));
            professor.setSenha(resultado.getString("senha"));
            professor.setUltimoAcesso(resultado.getDate("ultimoacesso"));
            professor.setIdprofessor(resultado.getInt("idprofessor"));
        }else{
            testSalvar();
        }
        return professor;
    }
    
    //    @Test
    public void testLogarSucesso() throws Exception {
        System.out.println("logar sucesso");
        buscarProfessorBD();
        Professor professorLogado = professorDao.logar(professor.getEmail(), professor.getSenha());
        assertNotNull(professorLogado);
    }

//    @Test
    public void testLogarFalso() throws Exception {
        System.out.println("logar com erro");
        Professor professorLogado = professorDao.logar("", "");
        assertNull(professorLogado);
    }
}
