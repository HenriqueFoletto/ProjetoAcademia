/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Beneficio;
import academia.entidade.Plano;
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
public class BeneficioDaoImplTest {
    
    private Beneficio beneficio;
    private BeneficioDao beneficioDao;
    
    public BeneficioDaoImplTest() {
        beneficioDao = new BeneficioDaoImpl();
    }

//    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Plano plano = new Plano();
        plano.setIdplano(6);
        beneficio = new Beneficio("30%");
        beneficio.setPlano(plano);
        beneficioDao.salvar(beneficio);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarBeneficioBD();
        beneficio.setBeneficio("15%");
        beneficioDao.alterar(beneficio);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarBeneficioBD();
        System.out.println("idbeneficio " + beneficio.getIdbeneficio());
        beneficioDao.excluir(beneficio.getIdbeneficio());
    }
    
    public Beneficio buscarBeneficioBD() throws Exception{ 
       String consulta = "SELECT * FROM beneficio";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        if(resultado.next()){
            beneficio = new Beneficio();
            beneficio.setBeneficio(resultado.getString("beneficio"));
            beneficio.setIdbeneficio(resultado.getInt("idbeneficio"));
        }else{
            testSalvar();
        }
       
        return beneficio;
    }   
    
}
