package br.com.palpitecerto.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.palpitecerto.model.Campeonato;

@FacesConverter(value = "campeonatoConverter", forClass = Campeonato.class)
public class CampeonatoConverter implements Converter {

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
			Campeonato campeonato = (Campeonato) value;

			if (campeonato.getId() != null) {
				this.addAttribute(componente, campeonato);

				if (campeonato.getId() != null) {
					return String.valueOf(campeonato.getId());
				}
				
				return (String) value;
			}
		}
		
		return "";
	}

	private void addAttribute(UIComponent componente, Campeonato campeonato) {
		this.getAttributesFrom(componente).put(campeonato.getId().toString(), campeonato);
	}

	private Map<String, Object> getAttributesFrom(UIComponent componente) {
		return componente.getAttributes();
	}
	
}