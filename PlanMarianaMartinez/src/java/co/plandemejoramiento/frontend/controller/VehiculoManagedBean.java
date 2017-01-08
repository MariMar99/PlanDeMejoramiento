package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.facades.VehiculoFacade;
import co.plandemejoramiento.backend.persistence.entity.Vehiculo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Mariana
 */
@Named(value = "vehiculoManagedBean")
@SessionScoped
public class VehiculoManagedBean implements Serializable {

    @EJB private VehiculoFacade vehiculofc;
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
        } catch (Exception e) {
        }
    }
    
    
}
