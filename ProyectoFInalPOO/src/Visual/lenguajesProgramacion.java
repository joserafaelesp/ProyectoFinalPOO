package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class lenguajesProgramacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JCheckBox[] checkboxes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    lenguajesProgramacion dialog = new lenguajesProgramacion();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog.
     */
    public lenguajesProgramacion() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                lenguajesProgramacion.class.getResource("/imagenes/lenguajes.png")));
        setResizable(false);
        setTitle("Lenguajes de Programación");
        setBounds(100, 100, 396, 321);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.inactiveCaption);
        contentPanel.add(panel);
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        checkboxes = new JCheckBox[] { 
            new JCheckBox("Java"), new JCheckBox("JavaScript"),
            new JCheckBox("Python"), new JCheckBox("C#"),
            new JCheckBox("C++"), new JCheckBox("PHP"),
            new JCheckBox("Perl"), new JCheckBox("SQL"),
            new JCheckBox("Ruby"), new JCheckBox("C"),
            new JCheckBox("R"), new JCheckBox("Visual Basic . NET"),
            new JCheckBox("TypeScript"), new JCheckBox("Swift")
        };

        for (JCheckBox checkbox : checkboxes) {
            checkbox.setFont(new Font("Dialog", Font.BOLD, 13));
            panel.add(checkbox);
        }

        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        buttonPane.setLayout(new BorderLayout(0, 0));

        JButton okButton = new JButton("Seleccionar");
        okButton.setFont(new Font("Dialog", Font.BOLD, 13));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = false;
                for (JCheckBox checkbox : checkboxes) {
                    if (checkbox.isSelected()) {
                        isSelected = true;
                        break;
                    }
                }
                if (isSelected) {
                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(null, "Selección satisfactoria.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un lenguaje de programación.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton, BorderLayout.CENTER);
        getRootPane().setDefaultButton(okButton);
    }
}