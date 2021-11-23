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
public class Beneficio {
    
    private Integer idbeneficio;
    private String beneficio;
    private Plano Plano;

    public Beneficio(){
        
    }
        
    public Beneficio(String beneficio){
        this.beneficio = beneficio;
    }    
    
    public Integer getIdbeneficio() {
        return idbeneficio;
    }

    public void setIdbeneficio(Integer idbeneficio) {
        this.idbeneficio = idbeneficio;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public Plano getPlano() {
        return Plano;
    }

    public void setPlano(Plano Plano) {
        this.Plano = Plano;
    }
    
    
}
