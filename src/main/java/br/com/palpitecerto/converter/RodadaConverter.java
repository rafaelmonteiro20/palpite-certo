package br.com.palpitecerto.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.palpitecerto.model.Rodada;

@FacesConverter(value = "rodadaConverter", forClass = Rodada.class)
public class RodadaConverter implements Converter {

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
			Rodada rodada = (Rodada) value;

			if (rodada.getId() != null) {
				this.addAttribute(componente, rodada);

				if (rodada.getId() != null) {
					return String.valueOf(rodada.getId());
				}
				
				return (String) value;
			}
		}
		
		return "";
	}

	private void addAttribute(UIComponent componente, Rodada rodada) {
		this.getAttributesFrom(componente).put(rodada.getId().toString(), rodada);
	}

	private Map<String, Object> getAttributesFrom(UIComponent componente) {
		return componente.getAttributes();
	}
	
}