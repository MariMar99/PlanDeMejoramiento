package co.plandemejoramiento.frontend.controller.converter;

import co.plandemejoramiento.frontend.converter.AbstractConverters;
import javax.faces.convert.FacesConverter;
import co.plandemejoramiento.backend.persistence.entity.Venta;

/**
 *
 * @author Mariana
 */
@FacesConverter (forClass = Venta.class)
public class VentaConverter extends AbstractConverters {

    public VentaConverter() {
        this.nombreManagedBean = "ventaManagedBean";
    }
    
}
