package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa el servidor de chat.
 * Maneja la conexión de clientes, envío de mensajes y la actualización de la lista de usuarios.
 */
public class ServidorChat {
    private static final int PUERTO = 5003;
    private Map<String, PrintWriter> clientes = new HashMap<>();
    private ServerSocket serverSocket;
    private ServidorChatGUI gui;
    private boolean servidorActivo = true;

    /**
     * Constructor del servidor de chat.
     *
     * @param gui Interfaz gráfica del servidor.
     */
    public ServidorChat(ServidorChatGUI gui) {
        this.gui = gui;
    }

    /**
     * Inicia el servidor y espera conexiones de clientes.
     */
    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            gui.actualizarLog("Servidor iniciado en el puerto " + PUERTO);

            while (servidorActivo) {
                Socket socket = serverSocket.accept();

                // Crea un manejador de cliente y lo ejecuta en un nuevo hilo.
                ManejadorCliente manejador = new ManejadorCliente(socket, this);
                new Thread(manejador).start();
            }
        } catch (IOException e) {
            if (servidorActivo) {
                gui.actualizarLog("Error en el servidor: " + e.getMessage());
            }
        }
    }

    /**
     * Registra un nuevo cliente en el servidor.
     *
     * @param nombre   Nombre del usuario.
     * @param escritor Objeto PrintWriter para enviar mensajes al cliente.
     */
    public synchronized void registrarCliente(String nombre, PrintWriter escritor) {
        if (!clientes.containsKey(nombre)) { // Evita registrar el mismo usuario dos veces.
            clientes.put(nombre, escritor);
            actualizarListaUsuarios(); // Actualiza la lista de usuarios en el chat.
        }
    }

    /**
     * Elimina un cliente del servidor cuando se desconecta.
     *
     * @param nombre Nombre del usuario a eliminar.
     */
    public synchronized void eliminarCliente(String nombre) {
        if (clientes.containsKey(nombre)) { // Verifica si el usuario está registrado antes de eliminarlo.
            clientes.remove(nombre);
            actualizarListaUsuarios();
            difundirMensaje("Servidor: " + nombre + " ha salido del chat."); // Notifica a los demás usuarios.
        }
    }

    /**
     * Difunde un mensaje a todos los clientes conectados.
     *
     * @param mensaje Mensaje a enviar.
     */
    public synchronized void difundirMensaje(String mensaje) {
        if (mensaje.startsWith("[Privado]")) {
            return; // No enviar mensajes privados a todos los clientes.
        }
        for (PrintWriter escritor : clientes.values()) {
            escritor.println(mensaje);
        }
        gui.actualizarLog("Mensaje público: " + mensaje);
    }

    /**
     * Envía un mensaje privado a un usuario específico.
     *
     * @param destinatario Nombre del usuario destinatario.
     * @param mensaje      Mensaje a enviar.
     */
    public synchronized void enviarMensajePrivado(String destinatario, String mensaje) {
        PrintWriter escritor = clientes.get(destinatario);
        if (escritor != null) {
            escritor.println("[Privado] " + mensaje);
        } else {
            gui.actualizarLog("Intento de enviar mensaje privado a usuario no conectado: " + destinatario);
        }
    }

    /**
     * Envía la lista de usuarios conectados a todos los clientes.
     */
    public synchronized void enviarListaUsuarios() {
        StringBuilder lista = new StringBuilder("[Usuarios]");
        for (String usuario : clientes.keySet()) {
            lista.append(usuario).append(",");
        }
        String listaUsuarios = lista.toString();

        for (PrintWriter escritor : clientes.values()) {
            escritor.println(listaUsuarios);
        }
    }

    /**
     * Detiene el servidor y cierra la conexión del socket.
     */
    public void detenerServidor() {
        servidorActivo = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            gui.actualizarLog("Error al detener el servidor: " + e.getMessage());
        }
    }

    /**
     * Obtiene el mapa de clientes conectados.
     *
     * @return Mapa con los nombres de usuario y sus respectivos PrintWriter.
     */
    public synchronized Map<String, PrintWriter> getClientes() {
        return clientes;
    }

    /**
     * Obtiene la interfaz gráfica del servidor.
     *
     * @return Objeto ServidorChatGUI.
     */
    public ServidorChatGUI getGui() {
        return gui;
    }

    /**
     * Actualiza la lista de usuarios en la GUI del servidor y la envía a los clientes.
     */
    public synchronized void actualizarListaUsuarios() {
        if (gui != null) { 
            gui.actualizarUsuarios(clientes.keySet()); // Actualiza la lista en la interfaz del servidor.
        }
        enviarListaUsuarios(); // Envia la lista de usuarios a los clientes.
    }
}
