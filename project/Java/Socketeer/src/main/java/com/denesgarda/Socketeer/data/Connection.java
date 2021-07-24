package com.denesgarda.Socketeer.data;

import com.denesgarda.Socketeer.data.event.Listener;
import com.denesgarda.Socketeer.data.lang.RestrictedObjectException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The connection established between a server and a client
 * @author denesgarda
 */
public class Connection {

    /**
     * This end of the connection
     */
    private End THIS;

    /**
     * The other end of the connection
     */
    private End THAT;

    /**
     * The port the connection was opened on
     */
    private int port;

    /**
     * The listener of the server
     */
    private Listener listener;

    /**
     * The socket of the connection
     */
    private Socket socket;

    /**
     * If the connection is open or closed
     */
    protected boolean open = true;

    /**
     * The constructor of Connection
     * @param THIS This end of the connection
     * @param THAT The other end of the connection
     * @param port The port the connection was opened on
     * @param listener The listener of the server
     * @param socket The socket of the connection
     */
    protected Connection(End THIS, End THAT, int port, Listener listener, Socket socket) {
        this.THIS = THIS;
        this.THAT = THAT;
        this.port = port;
        this.listener = listener;
        this.socket = socket;
    }

    /**
     * Get the other end of the connection
     * @return The other end of the connection
     */
    public End getOtherEnd() {
        return THAT;
    }

    /**
     * Sends a message to the server that the client is connected to
     * @param object The object to be send to the server
     * @return The server's response
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object sendToServer(Object object) throws IOException, ClassNotFoundException {
        if(object.equals("01101111 01101110 01100101 01010100 01101001 01101101 01100101 01000011 01101111 01101110 01101110 01100101 01100011 01110100 01101001 01101111 01101110") || object.equals("01101111 01101011")) {
            throw new RestrictedObjectException();
        }
        else {
            this.socket = new Socket(THAT.getAddress(), this.port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            Object reply = objectInputStream.readObject();
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
            socket = new Socket();
            return reply;
        }
    }

    /**
     * Check if the connection is open or closed
     * @return If the connection is open or closed
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
     * Connection types
     */
    public enum ConnectionType {

        /**
         * A one-time connection
         */
        ONE_TIME_CONNECTION,

        /**
         * A static connection that stays open until closed or killed
         * @apiNote Not yet implemented
         */
        STATIC
    }
}
