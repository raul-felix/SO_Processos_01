package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
	
	public void ip() {
		String osName = os().toLowerCase();
		if (osName.contains("windows")) {
			try {
				Process p = Runtime.getRuntime().exec("IPCONFIG".split(" "));
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					StringBuffer nomeAdaptador = new StringBuffer();
					if (linha.contains("Adaptador")) {
						nomeAdaptador = new StringBuffer(linha);
					}
					do {
						linha = buffer.readLine();
						if (linha == null) {
							break;
						}
						if (linha.contains("IPv4")) {
							System.out.println(nomeAdaptador);
							System.out.println(linha);
							System.out.println();
						}
					}
					while (!linha.contains("Adaptador"));
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else if (osName.contains("linux")) {
			try {
				Process p = Runtime.getRuntime().exec("ip addr".split(" "));
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					StringBuffer nomeAdaptador = new StringBuffer();
					if (linha.contains(": <")) {
						nomeAdaptador = new StringBuffer(linha);
					}
					do {
						linha = buffer.readLine();
						if (linha == null) {
							break;
						}
						if (linha.contains("inet ")) {
							System.out.println(nomeAdaptador);
							System.out.println(linha);
							System.out.println();
						}
					}
					while (!linha.contains(": <"));
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	public void ping() {
		String osName = os().toLowerCase();
		if (osName.contains("windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ping -4 -n 10 www.google.com.br".split(" "));
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("M�dia")) {
						String[] linhaArr = linha.split(" ");
						String media = linhaArr[linhaArr.length - 1];
						System.out.println("Tempo médio do ping: " + media);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else if (osName.contains("linux")) {
			try {
				Process p = Runtime.getRuntime().exec("ping -4 -c 10 www.google.com.br".split(" "));
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("avg")) {
						System.out.println(linha);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	public void callProcess(String proc) {
		try {
			Runtime.getRuntime().exec(proc.split(" "));
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(proc);
				try {
					Runtime.getRuntime().exec(buffer.toString().split(" "));					
				}catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			} else {
				System.err.println(msg);
			}
		}
	}
	
	public void readProcess(String proc) {
		try {
			Process p = Runtime.getRuntime().exec(proc.split(" "));
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void killProcess(String valor) {
		String cmdPid = "TASKKILL /PID";
		String cmdNome = "TASKKILL /IM";
		StringBuffer buffer = new StringBuffer();
		int pid = 0;
		
		try {
			pid = Integer.parseInt(valor);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
		}catch (Exception e) {
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(valor);
		}
		callProcess(buffer.toString());
	}
}
