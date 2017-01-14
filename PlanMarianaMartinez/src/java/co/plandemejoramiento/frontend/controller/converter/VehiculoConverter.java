package co.plandemejoramiento.frontend.controller.converter;

import co.plandemejoramiento.backend.persistence.entity.Vehiculo;
import javax.faces.convert.FacesConverter;
import co.plandemejoramiento.frontend.converter.AbstractConverters;

/**
 *
 * @author Mariana
 */
@FacesConverter (forClass = Vehiculo.class)
public class VehiculoConverter extends AbstractConverters{

    public VehiculoConverter() {
        this.nombreManagedBean = "vehiculoManagedBean";
    }
    
}
