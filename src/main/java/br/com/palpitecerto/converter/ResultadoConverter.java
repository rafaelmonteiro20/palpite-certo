package br.com.palpitecerto.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.palpitecerto.model.Resultado;

@FacesConverter(value="resultadoConverter")
public class ResultadoConverter implements Converter {

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
			Resultado resultado = (Resultado) value;

			if (resultado != null) {
				this.addAttribute(componente, resultado);
				return resultado.toString();
			}
		}
		
		return "";
	}

	private void addAttribute(UIComponent componente, Resultado resultado) {
		this.getAttributesFrom(componente).put(resultado.toString(), resultado);
	}

	private Map<String, Object> getAttributesFrom(UIComponent componente) {
		return componente.getAttributes();
	}
	
}