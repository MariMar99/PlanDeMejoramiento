/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.entity.Concesionario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Mariana
 */
@Named(value = "plantillaManagedBean")
@ViewScoped
public class PlantillaManagedBean implements Serializable {

    /**
     * Creates a new instance of PlantillaManagedBean
     */
    public PlantillaManagedBean() {
    }
    
    public void verificarSesion(){
        try {
            //Para no repetir el codigo se realiza una variable
            FacesContext context = FacesContext.getCurrentInstance();
            
            Concesionario con = (Concesionario) context.getExternalContext().getSessionMap().get("concesionario");
        //el metodo get- se coloca el apodo del bean del usuario(concesionario-index) - Haciendo que esto me devuelva un obj tipo usuario(conceisonario)
            if (con == null) { //Si no se almacena se redireciona
                context.getExternalContext().redirect("./../permisos.xhtml");
            }
        } catch (Exception e) {
        }
    }
  
    
}
