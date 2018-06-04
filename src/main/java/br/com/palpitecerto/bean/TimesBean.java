package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.FacesUtil;
import br.com.palpitecerto.model.Estado;
import br.com.palpitecerto.model.Time;
import br.com.palpitecerto.service.TimeService;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

@Named
@ViewScoped
public class TimesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesUtil facesUtil;
	
	@Inject
	private TimeService timeService;
	
	private Time time;
	private List<Time> times;
	
	@PostConstruct
	public void init() {
		atualizarTimes();
		novoTime();
	}
	
	public void salvar() {
		try {
			timeService.salvar(time);
			onSuccess("Time salvo com sucesso.");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}
	
	public void remover(Time time) {
		try {
			timeService.remover(time);
			onSuccess("Time removido com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onSuccess(String mensagem) {
		atualizarTimes();
		facesUtil.addInfoMessage(mensagem);
		facesUtil.updateComponents("mensagens", "times-tabela");
	}

	public void novoTime() {
		time = new Time();
	}
	
	private void atualizarTimes() {
		times = timeService.listar();
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public List<Time> getTimes() {
		return times;
	}
	
	public Estado[] getEstados() {
		return Estado.values();
	}
	
}
