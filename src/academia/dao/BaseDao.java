/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;
/**
 *
 * @author Henrique
 */
public interface BaseDao {
    
    void salvar(Object object) throws Exception;
    
    void alterar(Object object) throws Exception;
    
    void excluir(int id) throws Exception; 
    
}
