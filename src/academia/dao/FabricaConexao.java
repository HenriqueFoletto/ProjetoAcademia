/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Henrique
 */
public class FabricaConexao {
    
    public static Connection abrirConexao() throws Exception{
        Class.forName("org.postgresql.Driver");
        
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/academiatrabalho",
                "postgres", "782130");
    }
    
}
