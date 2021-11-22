/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.entidade;

/**
 *
 * @author Henrique
 */
public class Treino {
    
    private Integer idtreino;
    private String NomeTreino;
    
    public Treino(){
        
    }

    public Treino(String NomeTreino){
        this.NomeTreino = NomeTreino;
    }
    
    public Integer getIdtreino() {
        return idtreino;
    }

    public void setIdtreino(Integer idtreino) {
        this.idtreino = idtreino;
    }

    public String getNomeTreino() {
        return NomeTreino;
    }

    public void setNomeTreino(String NomeTreino) {
        this.NomeTreino = NomeTreino;
    }
}    
   
