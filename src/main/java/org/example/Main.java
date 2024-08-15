package org.example;

import domain.Commands;
import domain.EnumCommands;
import services.CommandsServices;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String status = " ";
        int antiBreak = 0;
        Scanner input = new Scanner(System.in);
        CommandsServices commandsServices = new CommandsServices();
        while (!status.equals("EXIT")) {
            System.out.println("Programa rodando");
            System.out.print("Digite um comando: ");
            commandsServices.selectCommand(commandsServices.readCommand(input.nextLine()));
            if (antiBreak == 5) status = "EXIT";
            antiBreak++;
        }
    }
}