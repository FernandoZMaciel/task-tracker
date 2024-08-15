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
        boolean keepAsking = true;
        Scanner input = new Scanner(System.in);
        CommandsServices commandsServices = new CommandsServices();
        JSONServices.normalizeId();
        commandsServices.selectCommand(commandsServices.readCommand("list all"));
        while (keepAsking) {
            System.out.print("Digite um comando: ");
            keepAsking = commandsServices.selectCommand(commandsServices.readCommand(input.nextLine()));
        }
        JSONServices.normalizeId();
    }
}