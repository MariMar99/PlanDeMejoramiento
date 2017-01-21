package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.entity.Concesionario;
import co.plandemejoramiento.backend.persistence.facades.ConcesionarioFacade;
import co.plandemejoramiento.frontend.converter.IManagedBean;
//import co.plandemejoramiento.frontend.util.JsfUtil;    ----> Se retiro la clase Util
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
//ClaseUtil import javax.faces.component.UIComponent;
//ClaseUtil import javax.faces.context.FacesContext;
//ClaseUtil import javax.faces.convert.Converter;
//ClaseUtil import javax.faces.convert.FacesConverter;
//ClaseUtil import javax.faces.model.SelectItem;

/**
 *
 * @author Mariana
 */
@Named(value = "concesionarioManagedBean")
//@SessionScoped    -  Caso pasar a VIEW
@RequestScoped
public class ConcesionarioManagedBean implements Serializable, IManagedBean<Concesionario> {

    @EJB private ConcesionarioFacade concesionariofc;
    private Concesionario concesionario;
    
    public ConcesionarioManagedBean() {
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    
    @PostConstruct
    public void init(){
        concesionario = new Concesionario();
    }
    
    public void registrarConcesionario(){
        try {
            concesionariofc.create(concesionario);
            mensajeExito(" Concesionario Registrado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarConcesionario(Concesionario concesionario){
        try {
            concesionariofc.remove(concesionario);
        } catch (Exception e) {
        }
    }
    
    public void modificarConcesionario(Concesionario concesionario){
        try {
            concesionariofc.edit(concesionario);
        } catch (Exception e) {
        }
    }
    
    public List<Concesionario> listarConcesionario(){
        try {
            return concesionariofc.findAll();
        } catch (Exception e) {
        }return null;
    }
    
    
    //Método para iniciar sesión
        Concesionario con;
    public String iniciarSesion(){
        String redireccion = null;
        try {
            con = concesionariofc.iniciarSesion(concesionario);
            if (con!=null) {
                //Almacenar en la sesion de JSF (un atributo que verificar en la vigencia de dla sesion)
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("concesionario", con);
                //put(Este es un apodo y el valor el objeto que ocntiene la info del usuario logueado)
                //-*-*-* Redireccion 
                redireccion = "/sesionPro/principal?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_WARN, "Aviso", "Datos Incorrectos!!!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_FATAL, "Aviso", "Error!!!"));
        }return redireccion;
    }
    
    //Converter    
    @Override
    public Concesionario getObject(Integer i) {
        return concesionariofc.find(i);
    }
    
    //Mensajes de exito y error
    private void mensajeError(Exception e) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se ha Producido el siguiente Error: ", e.getMessage()));
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Insertar:", e.getMessage());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);
    }

    private void mensajeExito(String operacion) {
        String msg = "Se ha realizado exitosamente la operacion de " + operacion;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
        FacesMessage sal = new FacesMessage(FacesMessage.SEVERITY_INFO,"Opereción con Exito : ", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(sal);
    }

    public Concesionario getCon() {
        return con;
    }

    public void setCon(Concesionario con) {
        this.con = con;
    }

    
}
