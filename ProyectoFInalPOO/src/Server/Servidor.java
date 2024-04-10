package Server;

import java.io.*;
import java.net.*;

public class Servidor extends Thread {
    
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7000);
        } catch (IOException ioe) {
            System.out.println("Error al abrir el socket del servidor: " + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión aceptada de: " + clientSocket.getInetAddress());
                
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream("empresa_respaldada.dat");
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                
                fileOutputStream.close();
                inputStream.close();
                clientSocket.close();
                
                System.out.println("Respaldo completado.");
            } catch (IOException ioe) {
                System.out.println("Error al manejar la conexión: " + ioe);
            }
        }
    }
}
