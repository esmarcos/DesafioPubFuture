package main.model;

public class ObjetoNaoEncontrado extends RuntimeException{

	public ObjetoNaoEncontrado(String mensage) {
		super(mensage);
	}
	
	public ObjetoNaoEncontrado(Throwable cause) {
		super(cause);
	}
}
