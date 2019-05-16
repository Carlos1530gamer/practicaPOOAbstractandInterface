import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class InputThread extends Thread implements PrintMethods{
    String nickName;
    Socket socket;

    InputThread(Socket socket, String nickName){
        this.socket = socket;
        this.nickName = nickName;
    }

    @Override
    public void run() {
        super.run();
        println("Conexion exitosa ya pedes chatear con " + nickName);
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while (!this.isInterrupted()) {
                final String message = dataInputStream.readUTF();
                println(nickName + ": " + message);
                if(message.toUpperCase().equals("STOP")){
                    socket.close();
                }
            }
        }catch (IOException exeption){
            println("Error " + exeption.getLocalizedMessage());
        }
    }
}
