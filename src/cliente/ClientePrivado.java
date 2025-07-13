package cliente;

import java.awt.BorderLayout;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utilidades.EstilosUI;

public class ClientePrivado extends JFrame {

    private static final long serialVersionUID = -2059344554844977256L;

    private JTextArea areaChatPrivado;
    private JTextField campoMensajePrivado;
    private JButton btnEnviarPrivado;
    private String usuarioDestino;
    private PrintWriter salida;

    /**
     * Constructor del chat privado.
     * 
     * @param usuarioDestino Usuario con el que se abrirá el chat privado.
     * @param salida Flujo de salida para enviar mensajes al servidor.
     */
    public ClientePrivado(String usuarioDestino, PrintWriter salida) {
        this.usuarioDestino = usuarioDestino;
        this.salida = salida;

        // **Aplicar Estilos Correctos**
        EstilosUI.aplicarEstilosGenerales();
        EstilosUI.aplicarSombraVentana(this);
        EstilosUI.estilizarTitulo(this);

        // **Configuración básica de la ventana**
        setTitle("Chat Privado con " + usuarioDestino);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // **Panel Principal**
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        EstilosUI.estilizarPanel(panelPrincipal, true); // ✅ Panel oscuro

        // **Área de Chat Privado**
        areaChatPrivado = new JTextArea();
        areaChatPrivado.setEditable(false);
        EstilosUI.estilizarAreaTexto(areaChatPrivado, true);
        JScrollPane scrollChat = new JScrollPane(areaChatPrivado);
        panelPrincipal.add(scrollChat, BorderLayout.CENTER);

        // **Panel Inferior con Campo de Mensaje y Botón**
        JPanel panelInferior = new JPanel(new BorderLayout());
        campoMensajePrivado = new JTextField();
        EstilosUI.estilizarCampoTexto(campoMensajePrivado, true);

        btnEnviarPrivado = new JButton("Enviar");
        EstilosUI.estilizarBoton(btnEnviarPrivado);

        panelInferior.add(campoMensajePrivado, BorderLayout.CENTER);
        panelInferior.add(btnEnviarPrivado, BorderLayout.EAST);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        // **Eventos para enviar mensajes privados**
        btnEnviarPrivado.addActionListener(e -> enviarMensajePrivado());
        campoMensajePrivado.addActionListener(e -> enviarMensajePrivado());
    }

    /**
     * Envía un mensaje privado al usuario seleccionado.
     * El mensaje se formatea con '@usuarioDestino mensaje' antes de enviarlo al servidor.
     */
    private void enviarMensajePrivado() {
        String mensaje = campoMensajePrivado.getText().trim();
        if (!mensaje.isEmpty()) {
            salida.println("@" + usuarioDestino + " " + mensaje);
            areaChatPrivado.append("Tú: " + mensaje + "\n"); // Agregar mensaje al área de chat
            campoMensajePrivado.setText(""); // Limpiar el campo después de enviar
        }
    }

    /**
     * Recibe y muestra un mensaje privado en el área de chat.
     * 
     * @param mensaje Mensaje recibido.
     */
    public void recibirMensajePrivado(String mensaje) {
        areaChatPrivado.append(mensaje + "\n");
    }
}
