package vendademercadorias;

import apoio.ConexaoBD;
import java.sql.*;
import javax.swing.JOptionPane;
import view.DlgLogin;
import view.FrmPrincipal;


/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class VendaDeMercadorias {

    static Connection conexao = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new DlgLogin(null, true).setVisible(true);        
        if (ConexaoBD.getInstance().getConnection() != null) {                
            new FrmPrincipal().setVisible(true); 
            } else {
            JOptionPane.showMessageDialog(null, "Falha ao tentar conectar-se com o Banco de Dados!");
        }
      
    }
}



