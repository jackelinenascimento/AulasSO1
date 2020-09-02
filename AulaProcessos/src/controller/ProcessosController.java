package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {

	public ProcessosController() {
		super();
	}
	
	public String os() {
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		return os + " - v. " + version + " - arch. " + arch;
	}
	
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
			
			if(msgErro.contains("740")) {
				// cmd /c caminho_do_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}
	}
	
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream(); // fluxo de bits
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
		
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
