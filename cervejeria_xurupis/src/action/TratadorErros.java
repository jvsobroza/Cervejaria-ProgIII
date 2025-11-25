package action;

import javax.swing.JOptionPane;

public class TratadorErros {
	
	public static void mensagemErro(String erro) {
		String[] erroFull = erro.split(";");
		String tipoErro = erroFull[0];
		String error = erroFull[1];
		JOptionPane.showMessageDialog(null, error, tipoErro, JOptionPane.ERROR_MESSAGE);
	}
}
