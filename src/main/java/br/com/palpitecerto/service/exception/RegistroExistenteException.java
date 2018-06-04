package br.com.palpitecerto.service.exception;

public class RegistroExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroExistenteException() {
		super("Registro já cadastro.");
	}

	public RegistroExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
