package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author jhonatan
 */
public interface IChat extends Remote {
    public void receberMensagens(String mensagem) throws RemoteException;
    
    public LinkedList lerMensagem() throws RemoteException;
}
