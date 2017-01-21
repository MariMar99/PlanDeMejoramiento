package co.plandemejoramiento.frontend.controller.converter;

import co.plandemejoramiento.backend.persistence.entity.Cliente;
import co.plandemejoramiento.frontend.converter.AbstractConverters;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mariana
 */
@FacesConverter (forClass = Cliente.class)

public class ClienteConverter extends AbstractConverters {

    public ClienteConverter() {
        this.nombreManagedBean = "clienteManagedBean";
    }
    
}
