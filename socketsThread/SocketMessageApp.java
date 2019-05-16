import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketMessageApp {
    public static void main(String[] args){
        boolean exit = false;
            do {

                println("Quieres ser cliente o servidor?: ");
                final String option = new Scanner(System.in).nextLine().toUpperCase();
                if(option.equals("CLIENTE")){
                    clientCode();
                    exit = true;
                }else{
                    if(option.equals("SERVIDOR")){
                        serverCode();
                        exit = true;
                    }else{
                        println("escoje una opcion.");
                    }
                }

            }while (!exit);
    }


    private static void print(Object arg){
        System.out.print(arg);
    }

    private static void println(Object arg){
        System.out.println(arg);
    }

    private static void serverCode(){
        String nickname;
        int port;

        print("Dame tu nickname: ");
        nickname = new Scanner(System.in).nextLine();
        try{

            print("Dame el puerto que quieres ser server: ");
            port = new Scanner(System.in).nextInt();

            ServerSocket serverSocket = new ServerSocket(port);
            Socket refSocket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(refSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(refSocket.getOutputStream());

            dataOutputStream.writeUTF(nickname);
            dataOutputStream.flush();

            String clientNickname = dataInputStream.readUTF();

            InputThread inputThread = new InputThread(refSocket,clientNickname);
            inputThread.start();

            while(true){
                final String yourMessage = new Scanner(System.in).nextLine();
                dataOutputStream.writeUTF(yourMessage);
                dataOutputStream.flush();
                if(yourMessage.toUpperCase().equals("STOP")){
                    break;
                }
            }
            print("Conexion terminada");
            inputThread.stop();
            serverSocket.close();
            refSocket.close();
        }catch (Exception exeption){
            println("error");
        }
    }

    private static void clientCode(){
        String nickname, ipServer, serverNickname;
        int port;

        print("Dame tu nickname: ");
        nickname = new Scanner(System.in).nextLine();
        print("Dame el ip del server: ");
        ipServer = new Scanner(System.in).nextLine();
        try{

            print("Dame el puerto al que quieres conectarte: ");
            port = new Scanner(System.in).nextInt();

            Socket socket = new Socket(ipServer,port);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            serverNickname = dataInputStream.readUTF();

            dataOutputStream.writeUTF(nickname);
            dataOutputStream.flush();

            InputThread inputThread = new InputThread(socket,serverNickname);
            inputThread.start();
            while(socket.isConnected()) {
                final String yourMessage = new Scanner(System.in).nextLine();
                dataOutputStream.writeUTF(yourMessage);
                dataOutputStream.flush();
            }
            print("conexion terminada.");
            inputThread.stop();
            socket.close();
        }catch (Exception exeption){
            println("error");
        }
    }
}
