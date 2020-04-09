
import cliente.FrameCliente;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidor.Servidor;


public class Main {
    public static void main(String[] args) {
        String opString = JOptionPane.showInputDialog(null, "1 - Cliente | 2 - Servidor", "CHAT COM RMI - CÁSSIO E FREDERICO", JOptionPane.QUESTION_MESSAGE);
       
        //Try catch apenas para definir o look and feel para windows
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Teste caso a pessoa digite uma letra
        try{
            int opcao = Integer.parseInt(opString);
            if(opcao == 1){
                FrameCliente cliente;
                try {
                    cliente = new FrameCliente();
                    cliente.setLocationRelativeTo(null);
                    cliente.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(opcao == 2){
                new Servidor();
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Opção inválida!");
        }
    }
}
