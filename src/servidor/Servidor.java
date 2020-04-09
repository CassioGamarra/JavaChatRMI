/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;
import cliente.Cliente;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Cássio e Frederico
 */
public class Servidor {
    public Servidor(){}
    
    PrintWriter enviar;
    Thread thServidor = new Thread();
    ServerSocket serverSocket;
    public void servidor(FrameServidor frame) throws IOException{
        int numeroPorta = Integer.parseInt(frame.getFieldPorta().getText());
        System.out.println("RODANDO....");
        serverSocket = new ServerSocket(numeroPorta);
        Socket clientSocket = serverSocket.accept();
        enviar = new PrintWriter(clientSocket.getOutputStream(), true);
        Scanner receber = new Scanner(clientSocket.getInputStream());
        
        thServidor = new Thread(){
            @Override
            public void run() {
                String userInput;
                try{
                    while(receber.hasNextLine()){
                        if((userInput = receber.nextLine()) != null){
                            userInput = userInput +"\n";
                            frame.getTxtAreaChat().append(userInput);
                        }
                    }
                }catch(Exception ex){
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thServidor.start();
    }
    public void enviar(String apelido, String msg, FrameServidor frame){
        String serverInput;
        if(!msg.contains("Enviar mensagem...")){
            serverInput = apelido+" diz:"+msg;
            enviar.println(serverInput);
            frame.getTxtAreaChat().append(serverInput+"\n");
        }
    }
    
}
