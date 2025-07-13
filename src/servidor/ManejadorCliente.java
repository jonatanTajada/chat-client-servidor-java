package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {

    private Socket socket;
    private ServidorChat servidor;
    private PrintWriter escritor;
    private BufferedReader lector;
    private String nombreUsuario;

    /**
     * Constructor del manejador de cliente.
     *
     * @param socket   Socket del cliente conectado.
     * @param servidor Instancia del servidor para gestionar la comunicación.
     */
    public ManejadorCliente(Socket socket, ServidorChat servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    /**
     * Método que ejecuta el hilo del cliente.
     * Maneja la conexión del cliente, su registro en el chat y la comunicación con otros clientes.
     */
    @Override
    public void run() {
        try {
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escritor = new PrintWriter(socket.getOutputStream(), true);

            // **Solicitar y validar el nombre de usuario**
            escritor.println("Ingrese su nombre de usuario:");
            while (true) {
                nombreUsuario = lector.readLine();
                if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
                    escritor.println("Nombre no válido. Intente de nuevo:");
                } else if (servidor.getClientes().containsKey(nombreUsuario)) {
                    escritor.println("Nombre en uso. Elija otro:");
                } else {
                    synchronized (servidor) {
                        servidor.registrarCliente(nombreUsuario, escritor);
                        servidor.getGui().actualizarLog("Nuevo cliente conectado: " + nombreUsuario);
                    }
                    break;
                }
            }

            escritor.println("Bienvenido al chat, " + nombreUsuario + "! Puedes escribir mensajes.");

            // **Escuchar y procesar mensajes del cliente**
            String mensaje;
            while ((mensaje = lector.readLine()) != null) {
                mensaje = mensaje.trim();
                if (mensaje.isEmpty()) continue;

                // **Si el usuario escribe "salir", se desconecta**
                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }

                // **Si el mensaje es privado (@usuario mensaje)**
                if (mensaje.startsWith("@")) {
                    String[] partes = mensaje.split(" ", 2);
                    if (partes.length == 2) {
                        String destinatario = partes[0].substring(1);
                        servidor.enviarMensajePrivado(destinatario, nombreUsuario + ": " + partes[1]);
                    } else {
                        escritor.println("Formato incorrecto. Usa: @usuario mensaje");
                    }
                } else {
                    // **Si el mensaje es público, se envía a todos los clientes**
                    servidor.difundirMensaje(nombreUsuario + ": " + mensaje);
                }
            }
        } catch (IOException e) {
            servidor.getGui().actualizarLog("Cliente desconectado: " + nombreUsuario);
        } finally {
            // **Desconectar al usuario y cerrar recursos**
            try {
                if (nombreUsuario != null) {
                    servidor.eliminarCliente(nombreUsuario);
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                servidor.getGui().actualizarLog("Error cerrando conexión con " + nombreUsuario + ": " + e.getMessage());
            }
        }
    }
}
