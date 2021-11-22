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
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrique
 */
public class TreinoDaoImplTest {
    
    private Treino treino;
    private TreinoDao treinoDao;
    
    public TreinoDaoImplTest() {
        treinoDao = new TreinoDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        treino = new Treino("Elevação Lateral, Apoio com Step, Panturrilha, Mesa flexora");
        treinoDao.salvar(treino);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarTreinoBD();
        treino.setNomeTreino("bíceps, Afundo, Agachamento");
        treinoDao.alterar(treino);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarTreinoBD();
        System.out.println("IdTreino " + treino.getIdtreino());
        treinoDao.excluir(treino.getIdtreino());
    }
    
    public Treino buscarTreinoBD() throws Exception {
        String consulta = "SELECT * FROM treino";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        if(resultado.next()){
            treino = new Treino();
            treino.setNomeTreino(resultado.getString("nometreino"));
            treino.setIdtreino(resultado.getInt("idtreino"));
        }else{
            testSalvar();
        }
        return treino;
    }
}
