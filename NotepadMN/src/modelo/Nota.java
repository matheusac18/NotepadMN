package modelo;

public class Nota {
	
	private String nome;
	private String nota;
	
	public Nota(String nome, String nota) {
		this.nome = nome;
		this.nota = nota;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
