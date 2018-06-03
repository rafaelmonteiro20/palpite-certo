package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.MessagesHelper;
import br.com.palpitecerto.model.Estado;
import br.com.palpitecerto.model.Time;
import br.com.palpitecerto.service.TimeService;

@Named
@ViewScoped
public class TimesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MessagesHelper helper;
	
	@Inject
	private TimeService timeService;
	
	private Time time;
	private List<Time> times;
	
	@PostConstruct
	public void init() {
		times = timeService.listar();
		time = new Time();
	}
	
	public void salvar() {
		try {
			timeService.salvar(time);
			helper.addInfoMessage("Time salvo com sucesso.");
		} catch (Exception e) {

		}
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
