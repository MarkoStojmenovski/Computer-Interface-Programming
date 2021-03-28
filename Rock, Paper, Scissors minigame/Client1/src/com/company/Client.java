package com.company;
import java.io.*;
import java.net.*;
import java.util.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
public class Client extends JPanel implements ActionListener{
    Socket clientSocket;
    JButton send;
    JLabel lblPort, lblServer, lblMove, lblAction, lblAction1, lblAction2, lblAction3, lblA;
    JTextField txtPort, txtServer, txtMove;
    ButtonGroup group;
    public Client() {
        setLayout(null);
        send = new JButton("Send to the Server");
        send.addActionListener(this);

        txtPort=new JTextField("",5);
        lblPort = new JLabel("Port:");

        txtServer=new JTextField("",5);
        lblServer = new JLabel("Server:");

        txtMove=new JTextField("",5);
        lblMove = new JLabel("Input a move:");
        lblAction = new JLabel("");
        lblAction1 = new JLabel("");
        lblAction2 = new JLabel("");
        lblAction3 = new JLabel("");
        lblA = new JLabel("Server: 127.0.0.1 / Port: 8080");
        lblPort.setBounds(155,40,200,20);
        txtPort.setBounds(190,40,45,20);

        lblA.setBounds(5, 10, 250,20);

        lblServer.setBounds(5,40,200,20);
        txtServer.setBounds(50,40,100,20);

        lblMove.setBounds(5,80,200,20);
        txtMove.setBounds(100,80,80,20);

        send.setBounds(5,115,150,20);

        lblAction.setBounds(5,145,1000,20);
        lblAction1.setBounds(5,165,2000,20);
        lblAction2.setBounds(5,185,2000,20);
        lblAction3.setBounds(5,205,1000,20);

        add(txtPort);
        add(lblPort);
        add(lblServer);
        add(txtServer);
        add(lblMove);
        add(txtMove);
        add(send);
        add(lblAction);
        add(lblAction1);
        add(lblAction2);
        add(lblAction3);
        add(lblA);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send){
            try {
                int port = Integer.parseInt(txtPort.getText());
                String whereTo = txtServer.getText();
                clientSocket = new Socket(whereTo, port);
                BufferedReader fromTheServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter toTheServer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                String Move = txtMove.getText();
                toTheServer.println(Move);
                String reply = fromTheServer.readLine();
                String result = fromTheServer.readLine();
                String score = fromTheServer.readLine();
                lblAction.setText(reply);
                lblAction1.setText(result);
                lblAction2.setText(score);
                lblAction3.setText(fromTheServer.readLine());
            } catch (Exception ex){
                lblAction.setText("The server closed the connection");
            }
        }
    }

    /**Host*/
    private static String host = "localhost";

    /**Welcome Message*/
    private static String msgWelcome = "Hi, let's Play Rock, Paper, Scissors \n";

    public static void main(String args[]) throws Exception {
        JFrame f = new JFrame("Client");
        f.getContentPane().add(new Client());
        f.setSize(400,300);
        f.setLocation(200,200);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//
//        String input = "";
//        String response;
//
//        System.out.println(Client.msgWelcome);
//
//        //Socket clientSocket = new Socket(Client.host, 8080);
//        while(/*!clientSocket.isClosed()*/true) {
//            Socket clientSocket = new Socket(Client.host, 8080);
//            BufferedReader inUser = new BufferedReader(new InputStreamReader(
//                    System.in));
//            DataOutputStream outServer = new DataOutputStream(
//                    clientSocket.getOutputStream());
//            BufferedReader inServer = new BufferedReader(new InputStreamReader(
//                    clientSocket.getInputStream()));
//
//
//            do {
//
//                // Prompt user for select rock, paper or scissors ...
//                System.out.println("\nStart the game by selecting Rock, Paper, Scissors \n");
//                input = inUser.readLine();
//
//            } while (!input.equalsIgnoreCase("Rock") && !input.equalsIgnoreCase("Paper") && !input.equalsIgnoreCase("Scissors"));
//
//            // Transmit input to the server and provide some feedback for the user
//            outServer.writeBytes(input + "\n");
//            System.out.println("\nYour input (" + input + ") was successfully transmitted to the server.");
//
//            System.out.println(inServer.readLine());
//            // Catch respones
//            response = inServer.readLine();
//
//            // Display respones
//            System.out.println("Result: \n" + response);
//
//            System.out.println(inServer.readLine());
//
//            System.out.println(inServer.readLine());
//
//            // Close socket
//            clientSocket.close();
//        }
//
//    }


}
