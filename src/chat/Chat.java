package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/**
 *
 * @author jhonatan
 */
public class Chat extends UnicastRemoteObject implements IChat{
    public LinkedList<String> mensagens;
    
    public Chat() throws RemoteException{
        mensagens = new LinkedList<>();
    }
    
    @Override
    public void receberMensagens(String mensagem) throws RemoteException {
        mensagens.add(mensagem);
    }

    @Override
    public LinkedList<String> lerMensagem() throws RemoteException {
        return mensagens;
    }
}