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
        treino.setIdtreino(6);
        professor = new Professor("José");
        professor.setTreino(treino);
        professorDao.salvar(professor);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarProfessorBD();
        professor.setNomeProfessor("José");
        professorDao.alterar(professor);
        
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarProfessorBD();
        System.out.println("IdTreino " + professor.getIdprofessor());
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
        System.out.println("");
    }
    
    public Professor buscarProfessorBD() throws Exception {
        String consulta = "SELECT * FROM professor";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        if(resultado.next()){
            professor = new Professor();
            professor.setNomeProfessor(resultado.getString("nomeprofessor"));
            professor.setIdprofessor(resultado.getInt("idprofessor"));
        }else{
            testSalvar();
        }
        return professor;
    }
}
