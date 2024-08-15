package org.example;

import domain.Commands;
import domain.EnumCommands;
import services.CommandsServices;
import services.JSONServices;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String status = " ";
        int antiBreak = 0;
        Scanner input = new Scanner(System.in);
        CommandsServices commandsServices = new CommandsServices();
        while (!status.equals("EXIT")) {
            System.out.println("Programa rodando");
            System.out.print("Digite um comando: ");
            commandsServices.selectCommand(commandsServices.readCommand(input.nextLine()));
            status = "EXIT";
        }
    }
}