# 💬 Chat Cliente-Servidor en Java – Interfaz Gráfica

> Proyecto de chat multiusuario con conexión por sockets, desarrollado en Java con interfaz gráfica moderna (Swing + FlatLaf). Incluye soporte para mensajes privados y gestión de usuarios en tiempo real.

---

## 🚀 **¿Qué es este proyecto?**

Un sistema de chat cliente-servidor realizado en Java SE, orientado a la práctica de redes (TCP/IP), hilos (multithreading) e interfaces gráficas. Permite que varios usuarios se conecten a un servidor común, vean quién está online, hablen en público o privado, y disfruten de una interfaz profesional.

---

## 🛠️ **Tecnologías utilizadas**

- ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white) **Java SE** – Lógica, hilos y sockets
- ![Swing](https://img.shields.io/badge/Swing-%23232F3E.svg?&style=flat) **Swing** – Interfaz gráfica
- ![FlatLaf](https://img.shields.io/badge/FlatLaf-0064a5?style=flat) **FlatLaf** – Estilo moderno para la UI
- ![Eclipse](https://img.shields.io/badge/Eclipse_IDE-2C2255?style=flat&logo=eclipse-ide&logoColor=white) **Eclipse IDE** – Entorno de desarrollo


## 🏗️ **Arquitectura**

- **Servidor**:  
  - Gestiona conexiones y mensajes
  - Soporta mensajes públicos y privados
  - Interfaz propia para visualizar actividad y usuarios conectados

- **Cliente**:  
  - Ventana principal (chat global)
  - Lista de usuarios online
  - Ventanas de chat privado (doble clic en usuario)
  - Mensajes diferenciados para usuario propio (#Tú)


## ⚡ **¿Cómo se ejecuta?**

1. **Clona el repositorio:**
https://github.com/jonatanTajada/chat-client-servidor-java.git

2. **Abre el proyecto en Eclipse (o cualquier IDE Java).**

3. **Ejecuta el servidor:**
- Ve a `src/servidor/ServidorChatGUI.java` y ejecuta el método `main`.

4. **Ejecuta uno o varios clientes:**
- Ve a `src/cliente/ClienteChat.java` y ejecuta el método `main`.  
- Repite para simular varios usuarios en tu PC
.
5. **¡Listo!**
- El servidor escucha en el puerto **5003**.
- Los clientes deben poner la IP y el puerto (usa `127.0.0.1` si todo está en tu PC).
- Escribe tu nombre de usuario y… ¡a chatear!


## ✨ Características principales

- 👥 **Chat multiusuario**: mensajes públicos y privados.
- 🔄 **Re-conexión automática** de chats privados.
- 🎨 **Interfaz moderna y personalizable** (claro/oscuro con FlatLaf).
- 🧑‍💻 **Lista de usuarios en tiempo real**.
- 📜 **Log de actividad** en el servidor.
- 💻 **Multiplataforma** (funciona en cualquier SO con Java).
- 📦 **Código estructurado y comentado**.


## 📂 Estructura del proyecto

/src
  /cliente
    ClienteChat.java
    ClientePrivado.java
  /servidor
    ServidorChat.java
    ServidorChatGUI.java
    ManejadorCliente.java
  /utilidades
    EstilosUI.java
/lib
  flatlaf-3.5.4.jar
  flatlaf-extras-3.5.4.jar
...

## 🧑‍💻 Autor
Jonatan Tajada

2º DAM – 2025

- [LinkedIn](https://www.linkedin.com/in/jonatantr)
- [GitHub](https://github.com/jonatanTajada)

## 📖 Licencia
MIT License.
Utiliza, mejora y comparte libremente (¡citando al autor si lo publicas!).

## 💡 Notas finales
Este proyecto es ideal para entender los fundamentos de la comunicación en red con Java, el uso de hilos, la gestión de usuarios y la construcción de GUIs modernas.
¿Quieres más? Mira otros proyectos de chat UDP/TCP en este repo.







