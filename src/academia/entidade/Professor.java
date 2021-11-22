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
public class Professor {
    
    private Integer idprofessor;
    private String nomeProfessor;
    private Treino treino;
    
    public Professor(){
    }
    
    public Professor(String nomeProfessor){
        this. nomeProfessor = nomeProfessor;
    }

    public Integer getIdprofessor() {
        return idprofessor;
    }

    public void setIdprofessor(Integer idprofessor) {
        this.idprofessor = idprofessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
   
}
