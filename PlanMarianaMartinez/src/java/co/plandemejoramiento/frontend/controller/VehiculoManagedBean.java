package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.facades.VehiculoFacadeLocal;
import co.plandemejoramiento.backend.persistence.entity.Vehiculo;
import co.plandemejoramiento.frontend.converter.IManagedBean;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
//import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mariana
 */
@Named (value = "vehiculoManagedBean")
//@SessionScoped
//@RequestScoped
@ViewScoped
public class VehiculoManagedBean implements Serializable, IManagedBean<Vehiculo> {

    @EJB private VehiculoFacadeLocal vehiculofc;
    private Vehiculo vehiculo;
    
    public VehiculoManagedBean() {
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @PostConstruct
    public void init(){
        vehiculo = new Vehiculo();
    }
    
    public List<Vehiculo> listarVehiculo(){
        try {
            return vehiculofc.findAll();
        } catch (Exception e) {
        }return null;
    }
    
    public void registrarVehiculo(){
        try {
            vehiculofc.create(vehiculo);
            mensajeExito(" Vehiculo Registrado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void modificarVehiculo(Vehiculo vehiculo){
        try {
            vehiculofc.edit(vehiculo);
            mensajeExito(" Editado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarVehiculo(Vehiculo vehiculo){
        try {
            vehiculofc.remove(vehiculo);
            mensajeExito(" Vehiculo Eliminado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }

    @Override
    public Vehiculo getObject(Integer i) {
        return vehiculofc.find(i);
    }
    
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
