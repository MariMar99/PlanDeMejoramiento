package co.plandemejoramiento.frontend.converter;

import co.plandemejoramiento.backend.persistence.entity.IEntity;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mariana
 */
public abstract class AbstractConverters implements Converter{

    protected String nombreManagedBean;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer key = Integer.valueOf(value);
            IManagedBean mBean = (IManagedBean) context.getApplication().getELResolver().getValue(context.getELContext(), null, nombreManagedBean);
            return mBean.getObject(key);
        } catch (NumberFormatException e) {
            context.addMessage(null, new FacesMessage("NO se puede convertir el objeto "+ e.getMessage()));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if (value instanceof IEntity) {
                IEntity e = (IEntity) value;
                return e.getPK();
            }
        } catch (Exception e) {
        }
        return null;
    }

    
    
    
   
    
}
