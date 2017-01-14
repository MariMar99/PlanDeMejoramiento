package co.plandemejoramiento.frontend.controller.converter;

import co.plandemejoramiento.backend.persistence.entity.Concesionario;
import co.plandemejoramiento.frontend.converter.AbstractConverters;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author Mariana
 */
//@FacesConverter (forClass = Concesionario.class)
public class ConcesionarioConverter extends AbstractConverters {

    public ConcesionarioConverter() {
        this.nombreManagedBean = "concesionarioManagedBean";
    }
    
}
