package main.model;

public class ContaNaoEncontrada extends RuntimeException{

	public ContaNaoEncontrada(String mensage) {
		super(mensage);
	}
	
	public ContaNaoEncontrada(Throwable cause) {
		super(cause);
	}
}
