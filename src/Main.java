import AgendaGUI.AgregarContacto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Agregar Contacto"); //Trae SWING
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la aplicación al darle en cerrar
//        frame.setSize(500,500); //Define tamaño de la vnetana
//
//        JButton botonAgregarContacto = new JButton("Agregar Contacto");
//        frame.add(botonAgregarContacto, BorderLayout.NORTH); //Esto es para elegir la ubicacion
//
//        JButton boton1 = new JButton("Boton1");
//        frame.add(boton1, BorderLayout.SOUTH);
//        JButton boton2 = new JButton("Boton2");
//        frame.add(boton2, BorderLayout.EAST);
//        JButton boton3 = new JButton("Boton3");
//        frame.add(boton3, BorderLayout.WEST);
//
//        JLabel miLabel = new JLabel("Nombre de Contacto");
//        frame.add(miLabel, BorderLayout.CENTER);
//
//        frame.setVisible(true); //Debe ir siempre al final


        JFrame frame = new JFrame();
        AgregarContacto ventanaAgregar = new AgregarContacto();
        frame.setContentPane(ventanaAgregar.getVentanaDeAgregar());
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}