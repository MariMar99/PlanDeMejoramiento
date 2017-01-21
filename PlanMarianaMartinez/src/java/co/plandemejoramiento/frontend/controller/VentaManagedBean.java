package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.entity.Venta;
import co.plandemejoramiento.backend.persistence.facades.VentaFacade;
import co.plandemejoramiento.frontend.converter.IManagedBean;
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

/**
 *
 * @author Mariana
 */
@Named(value = "ventaManagedBean")
//@SessionScoped
@RequestScoped
public class VentaManagedBean implements Serializable, IManagedBean<Venta> {

    @EJB private VentaFacade ventafc;
    private Venta venta;
    
    public VentaManagedBean() {
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    @PostConstruct
    public void init(){
        venta = new Venta();
    }
    
    public void registrarVenta(){
        try {
            ventafc.create(venta);
        } catch (Exception e) {
        }
    }
    
    public void eliminarVenta(Venta venta){
        try {
            ventafc.remove(venta);
        } catch (Exception e) {
        }
    }
    
    public void modificarVenta(Venta venta){
        try {
            ventafc.edit(venta);
        } catch (Exception e) {
        }
    }
    
    public List<Venta> listarVenta(){
        try {
            return ventafc.findAll();
        } catch (Exception e) {
        }return null;
    }

    @Override
    public Venta getObject(Integer i) {
        return ventafc.find(i);
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
}
