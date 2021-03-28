package com.company;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;

public class Server {

    /**Host*/
    private static String host = "localhost";

    /**Welcome Message*/
    private static String msgWelcome = "Hi, let's Play Rock, Paper, Scissors \n";

    public static void main(String args[]) throws Exception {

        String resClient1 = "";
        String inputClient1;
        int user=0;
        int comp=0;

        // Create new server socket & display a status msg
        ServerSocket welcomeSocket = new ServerSocket(8080);
        System.out.println("\nOk, we're up and running on port " + welcomeSocket.getLocalPort());

        while (!welcomeSocket.isClosed()) {

            // Client
            Socket client1 = welcomeSocket.accept();
            if (client1.isConnected()) {
                System.out.println("\nClient (" + (client1.getLocalAddress().toString()).substring(1) + ":"
                        + client1.getLocalPort()+")");
            }
            PrintWriter s = new PrintWriter( new OutputStreamWriter( client1.getOutputStream() ), true );
            //DataOutputStream outClient1 = new DataOutputStream(client1.getOutputStream());
            BufferedReader inClient1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));


                // Get client input
                inputClient1 = inClient1.readLine();
                    String list[] = {"Rock", "Paper", "Scissors"};
                    String move;
                    Random r = new Random();
                    move = list[r.nextInt(3)];

                    s.println("The Computer's move is: " + move);

                    /**If the moves from the Client and the Computer are the same then server sends the result as a draw*/
                    if (inputClient1.equalsIgnoreCase(move)) {
                        resClient1 = "Draw";
                        System.out.println("It's a draw.");
                    }
                    /**If the move from the Client is rock and the Computer's move is scissors it sets the result to a win for the Client and prints the winner
                     * and increases the score by 1 for Client*/
                    else if (inputClient1.equalsIgnoreCase("Rock") && move.equalsIgnoreCase("Scissors")) {
                        resClient1 = "You win";
                        System.out.println("Player one wins.");
                        user++;
                    }
                    /**If the move from the Client is scissors and the Computer's move is rock it sets the result to a loss for the Client and prints the winner
                     * and increases the score by 1 for Computer*/
                    else if (inputClient1.equalsIgnoreCase("Scissors") && move.equalsIgnoreCase("Rock")) {
                        resClient1 = "You lose";
                        System.out.println("Computer wins.");
                        comp++;
                    }
                    else if (inputClient1.equalsIgnoreCase("Rock") && move.equalsIgnoreCase("Paper")) {
                        resClient1 = "You lose";
                        System.out.println("Computer wins.");
                        comp++;
                    }
                    else if (inputClient1.equalsIgnoreCase("Paper") && move.equalsIgnoreCase("Rock")) {
                        resClient1 = "You win";
                        System.out.println("Player one wins.");
                        user++;
                    }
                    else if (inputClient1.equalsIgnoreCase("Scissors") && move.equalsIgnoreCase("Paper")) {
                        resClient1 = "You win";
                        System.out.println("Player one wins.");
                        user++;
                    }
                    else if (inputClient1.equalsIgnoreCase("Paper") && move.equalsIgnoreCase("Scissors")) {
                        resClient1 = "You lose";
                        System.out.println("Computer wins.");
                        comp++;
                    }
                    else{
                        resClient1="You entered an insufficient move";
                    }

                    // Send responses in uppercase and close sockets
                    s.println(resClient1.toUpperCase());

                    s.println(user + " : " + comp);
                    if(user==3){
                        s.println("You won the game! You're Lucky.");
                        System.exit(0);
                    }
                    else if(comp==3){
                        s.println("The computer wins the game! Tough luck.");
                        System.exit(0);
                    }
                    else{
                        s.println();
                    }
                    //client1.close();
        }
    }
}