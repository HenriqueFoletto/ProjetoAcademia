/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.dao;

import academia.entidade.Professor;
import java.util.List;

/**
 *
 * @author Henrique
 */
public interface ProfessorDao extends BaseDao{
    
    Professor pesquisarPeloProfessor(int idprofessor) throws Exception;
    
    List<Professor> pesquisarPorNome(String nomeProfessor) throws Exception;
}
