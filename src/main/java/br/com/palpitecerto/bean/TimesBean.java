package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	private TimeService timeService;
	
	private List<Time> times;
	
	@PostConstruct
	public void init() {
		times = timeService.listar();
	}
	
	public List<Time> getTimes() {
		return times;
	}
	
}
