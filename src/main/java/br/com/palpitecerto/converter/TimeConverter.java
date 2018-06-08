package br.com.palpitecerto.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.palpitecerto.model.Time;

@FacesConverter(value = "timeConverter", forClass = Time.class)
public class TimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent componente, String value) {
		if (value != null) {
			return componente.getAttributes().get(value);
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent componente, Object value) {

		if (value != null) {
			Time time = (Time) value;

			if (time.getId() != null) {
				this.addAttribute(componente, time);

				if (time.getId() != null) {
					return String.valueOf(time.getId());
				}
				
				return (String) value;
			}
		}
		
		return "";
	}

	private void addAttribute(UIComponent componente, Time time) {
		this.getAttributesFrom(componente).put(time.getId().toString(), time);
	}

	private Map<String, Object> getAttributesFrom(UIComponent componente) {
		return componente.getAttributes();
	}
	
}