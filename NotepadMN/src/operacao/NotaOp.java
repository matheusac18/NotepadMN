package operacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Nota;
import modelo.Usuario;

public class NotaOp {

	public static void adicinarNota(String nota) {

	}
	
	@SuppressWarnings("unused")
	public static void salvarNota(Nota c, Usuario u) {
		String dir = "user.dir";
		try {
			dir = new File(System.getProperty(dir)).getCanonicalPath();
		} catch (IOException ex) 
		{
			System.out.println(ex);
		}
		File arquivo = new File(dir + File.separator + u.getEmail() + File.separator + c.getNome()+".txt");
		
		try
		{
			FileOutputStream fos = new FileOutputStream(arquivo);
			fos.write(c.getNota().getBytes());
			fos.flush();
			fos.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
			
	}

	public static void removerNota(String nota) {

	}

	public static Nota buscaNota(String nome, Usuario u) {
		Nota c = null;
		String dir = "user.dir";
		try {
			dir = new File(System.getProperty(dir)).getCanonicalPath();
		} catch (IOException e1) {
		}

		File arquivo = new File(dir + File.separator + u.getEmail() + File.separator + nome+".txt");

		// obtem a lista de arquivos

		try {
			Scanner sc = new Scanner(arquivo);
			sc.useDelimiter("\\Z");
			String nota = sc.next();
			c = new Nota(nome, nota);

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		return c;
	}

	public static String[] buscarNotas(Usuario c) {
		String dir = "user.dir";
		try {
			dir = new File(System.getProperty(dir)).getCanonicalPath();
		} catch (IOException e1) {
		}

		File baseFolder = new File(dir + "\\" + c.getEmail());

		// obtem a lista de arquivos
		File[] files = baseFolder.listFiles();

		String[] notasNomes = new String[files.length];
		int i = 0;

		for (File f : files) {
			notasNomes[i] = f.getName().replace(".txt", "");
			i++;
		}

		return notasNomes;
	}

}
