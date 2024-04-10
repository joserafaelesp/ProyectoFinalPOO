package Server;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.net.*;

public class Respaldo extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Respaldo dialog = new Respaldo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Respaldo() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton backupButton = new JButton("Respaldar");
                backupButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        respaldarArchivo();
                    }
                });
                backupButton.setActionCommand("OK");
                buttonPane.add(backupButton);
                getRootPane().setDefaultButton(backupButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

   
    private void respaldarArchivo() {
        try {
            Socket socket = new Socket("127.0.0.1", 7000);
            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
            
            
            File file = new File("empresa.dat");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                flujoSalida.write(buffer, 0, bytesRead);
            }
            flujoSalida.flush();
            fileInputStream.close();
            
            flujoSalida.close();
            socket.close();
            
            System.out.println("Respaldo completado.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
