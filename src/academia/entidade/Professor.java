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
    private String email;
    private String senha;
    private Treino treino;
    
    public Professor(){
    }
    
    public Professor(String nomeProfessor, String email, String senha){
        this. nomeProfessor = nomeProfessor;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
   
    
}
