/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;
import audio.TocarSom;
import chat.Chat;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cássio e Frederico
 */
public class Servidor {
    String IP_SERVIDOR = "192.168.0.13";
    String URL_SERVIDOR = "rmi://"+IP_SERVIDOR+"/Chat";
    TocarSom somInicio = new TocarSom("/audio/start.wav");
    
    public Servidor(){
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Chat objetoRemoto = new Chat();
            Naming.bind(URL_SERVIDOR, objetoRemoto);
            FrameServidor svFrame = new FrameServidor();
            somInicio.tocarSom();
            svFrame.setLocationRelativeTo(null);
            svFrame.setVisible(true);
        } catch (RemoteException | AlreadyBoundException | MalformedURLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
