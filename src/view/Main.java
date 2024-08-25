package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {
	
	public static void main(String[] args) {
		
		String menu = "MENU: \n"
					+ "1 - IP\n"
					+ "2 - PING\n"
					+ "9 - FIM";
		
		String opc = "";
		
		do {
			opc = JOptionPane.showInputDialog(menu);
			
			switch (opc) {
				case "1":
					RedesController redesControllerIp = new RedesController();
					redesControllerIp.ip();
					break;
				case "2":
					RedesController redesControllerPing = new RedesController();
					redesControllerPing.ping();
					break;
				case "9":
					System.out.println("FIM DO PROGRAMA");
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA");
			}
		}
		while (!opc.equals("9"));
	}
}
