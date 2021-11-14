package vista;

import codigo.LecturaDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class Vista extends JFrame {
    private JTextField textTraducir;
    private JButton traducirButton;
    private JTextField textTraducido;
    private JButton registrarButton;
    private JPanel vista;
    private JButton limpiarDatosButton;

    public Vista() {

        add(vista);
        setResizable(false);
        setBounds(100, 100, 500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        LecturaDB diccionario = new LecturaDB();

        traducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabra = textTraducir.getText();
                StringBuilder palabraTraducida = new StringBuilder();
                String palabraTraducida2 = "";
                if (palabra.length() > 0) {
                    try {
                        String[] traduccion = diccionario.buscarTraduccion(palabra);
                        int longitud = traduccion.length;
                        for (int i = 1; i < longitud; i++) {
                            palabraTraducida.append(traduccion[i]);
                        }
                        palabraTraducida2 = String.valueOf(palabraTraducida);
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(vista, "No existe la palabra a buscar\n intenta registrarla");
                    }
                    textTraducido.setText(palabraTraducida2);
                } else {
                    JOptionPane.showMessageDialog(vista, "Debes ingresar una palabra a buscar");
                }
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabra = textTraducir.getText();
                if (palabra.length() > 0) {
                    String traduccion = JOptionPane.showInputDialog("Ingresa la traduccion\n si la traduccion contiene varias palabras\n separalas con una coma");
                    try {
                        diccionario.registrarPalabra(palabra + "," + traduccion);
                        JOptionPane.showMessageDialog(vista, "Registro existoso");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(vista, "error intenta nuevamente");
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Ingresa la palabra a traduccion el campo de texto");
                }
            }
        });
        limpiarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textTraducir.setText("");
                textTraducido.setText("");
            }
        });
    }

}
