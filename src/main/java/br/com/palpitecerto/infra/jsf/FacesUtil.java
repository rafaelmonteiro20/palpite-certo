package br.com.palpitecerto.infra.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

public class FacesUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;

	public void addInfoMessage(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message);
	}
	
	public void addErrorMessage(String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, message);
	}
	
	public void addFlash(String message) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		addInfoMessage(message);
	}
	
	public void validationFailed(String message) {
		addErrorMessage(message);
		context.validationFailed();
	}

	private void addMessage(Severity severity, String message) {
		FacesMessage facesMessage = new FacesMessage(severity, message, message);
		context.addMessage(null, facesMessage);
	}
	
	public void updateComponents(String...components) {
		PrimeFaces.current().ajax().update(components);
	}
	
	public void execute(String script) {
		PrimeFaces.current().executeScript(script);
	}
	
	public String getUrl() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}
	
}