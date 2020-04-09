
import cliente.FrameCliente;
import javax.swing.JOptionPane;
import servidor.FrameServidor;

public class Main {
    public static void main(String[] args) {
        String opString = JOptionPane.showInputDialog(null, "1 - Cliente | 2 - Servidor", "CHAT - CÁSSIO E FREDERICO", JOptionPane.QUESTION_MESSAGE);
       
        //Try catch apenas para definir o look and feel para windows
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Teste caso a pessoa digite uma letra
        try{
            int opcao = Integer.parseInt(opString);
            if(opcao == 1){
                FrameCliente cliente = new FrameCliente();
                cliente.setLocationRelativeTo(null);
                cliente.setVisible(true);
            }
            else if(opcao == 2){
                FrameServidor servidor = new FrameServidor();
                servidor.setLocationRelativeTo(null);
                servidor.setVisible(true);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Opção inválida!");
        }
    }
}
