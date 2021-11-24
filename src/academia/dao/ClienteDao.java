/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Cliente;
import java.util.List;

/**
 *
 * @author Henrique
 */
public interface ClienteDao extends BaseDao{

    Cliente pesquisarPorMatricula(int matricula) throws Exception;
    
    List<Cliente> pesquisarPorNome(String nome) throws Exception;
}
