package org.example;

public class Main {
    public static void main(String[] args) {
        String status = " ";
        int antiBreak = 0;
        while (!status.equals("EXIT")) {
            System.out.println("Programa rodando");


            if (antiBreak == 3) status = "EXIT";
            antiBreak++;
        }
    }
}