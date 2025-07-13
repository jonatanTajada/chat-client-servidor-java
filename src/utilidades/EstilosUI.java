package utilidades;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EstilosUI {

    // **PALETA DE COLORES MEJORADA**
    private static final Color COLOR_FONDO_CLARO = new Color(240, 240, 240);
    private static final Color COLOR_FONDO_OSCURO = new Color(40, 40, 40);
    private static final Color COLOR_BOTON = new Color(0, 123, 255);
    private static final Color COLOR_BOTON_HOVER = new Color(0, 92, 192);
    private static final Color COLOR_TEXTO_CLARO = new Color(50, 50, 50);
    private static final Color COLOR_TEXTO_OSCURO = Color.WHITE;
    private static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 14);

    // **APLICAR TEMA GENERAL CLARO**
    public static void aplicarEstilosGenerales() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf: " + e.getMessage());
        }
    }

    // **APLICAR TEMA OSCURO PARA CHAT PRIVADO**
    public static void aplicarEstilosChatPrivado() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf Dark: " + e.getMessage());
        }
    }

    // **APLICAR SOMBRA A LAS VENTANAS**
    public static void aplicarSombraVentana(JFrame ventana) {
        ventana.getRootPane().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    //  **ESTILIZAR TÍTULOS**
    public static void estilizarTitulo(JFrame ventana) {
        ventana.setFont(FUENTE_TITULO);
    }

    // **BOTONES CON ESTILO MODERNO**
    public static void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(COLOR_TEXTO_OSCURO);
        boton.setBackground(COLOR_BOTON);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //**Bordes redondeados con sombra**
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(20, 80, 180), 2),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)
        ));

        // **Efecto Hover**
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(COLOR_BOTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(COLOR_BOTON);
            }
        });
    }

    //  **PANELES CON COLOR DISTINTO SEGÚN EL MODO**
    public static void estilizarPanel(JPanel panel, boolean oscuro) {
        panel.setBackground(oscuro ? COLOR_FONDO_OSCURO : COLOR_FONDO_CLARO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    //  **CAMPOS DE TEXTO ESTILIZADOS**
    public static void estilizarCampoTexto(JTextField campo, boolean oscuro) {
        campo.setFont(FUENTE_TEXTO);
        campo.setForeground(oscuro ? COLOR_TEXTO_OSCURO : COLOR_TEXTO_CLARO);
        campo.setBackground(oscuro ? new Color(60, 60, 60) : new Color(220, 220, 220));
        campo.setCaretColor(oscuro ? COLOR_TEXTO_OSCURO : COLOR_TEXTO_CLARO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    // **ÁREAS DE TEXTO MEJORADAS**
    public static void estilizarAreaTexto(JTextArea area, boolean oscuro) {
        area.setFont(FUENTE_TEXTO);
        area.setForeground(oscuro ? COLOR_TEXTO_OSCURO : COLOR_TEXTO_CLARO);
        area.setBackground(oscuro ? new Color(30, 30, 30) : new Color(230, 230, 230));
        area.setCaretColor(oscuro ? COLOR_TEXTO_OSCURO : COLOR_TEXTO_CLARO);
        area.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }

    //  **ETIQUETAS ESTILIZADAS**
    public static void estilizarEtiqueta(JLabel etiqueta) {
        etiqueta.setFont(FUENTE_TEXTO);
        etiqueta.setForeground(COLOR_TEXTO_CLARO);
    }
}
