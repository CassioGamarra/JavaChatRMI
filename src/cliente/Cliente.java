/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import audio.TocarSom;
import chat.IChat;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cássio e Frederico
 */
public class Cliente {
    public Cliente(){}
    
    IChat chat;
    String servidor, nickName, mensagem;
    LinkedList<String> mensagens = new LinkedList<>();
    
    TocarSom somMsgRecebida = new TocarSom("/audio/msn.wav");
    
    public void cliente(FrameCliente frame){
        servidor = frame.getFieldEndereco().getText();
        nickName = frame.getFieldApelido().getText();
        System.out.println(servidor);
        try {
            chat = (IChat) Naming.lookup("rmi://" + servidor + "/Chat");
        
            frame.getTxtAreaChat().append("Conectado...\n\n");
            mensagens.addAll(chat.lerMensagem());
            
            new Thread(){
                @Override
                public void run(){
                    do{
                        try {
                            if(chat.lerMensagem().size() > mensagens.size()) {
                                frame.getTxtAreaChat().setText("");
                                mensagens.clear();
                                
                                for(Object msg : chat.lerMensagem()){
                                    frame.getTxtAreaChat().append(msg.toString()+"\n");
                                    mensagens.add(msg.toString());
                                }
                                
                                somMsgRecebida.tocarSom();
                            }
                            Thread.sleep(2000);
                        } 
                        catch (RemoteException | InterruptedException ex) {
                            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }while(true);
                }
            }.start();
        }
        catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviar(String apelido, String msg, FrameCliente frame){
        mensagem = apelido+" diz: "+frame.getFieldMsg().getText();
        if(!mensagem.isEmpty()){
            try {
                chat.receberMensagens(mensagem);
                frame.getFieldMsg().setText("");
                frame.getTxtAreaChat().append(chat.lerMensagem().getLast() + "\n");
            }
            catch (RemoteException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
