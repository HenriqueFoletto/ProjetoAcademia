/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Treino;
import java.util.List;

/**
 *
 * @author Henrique
 */
public interface TreinoDao extends BaseDao{
    
    List<Treino> pesquisarTreinos() throws Exception;
}
