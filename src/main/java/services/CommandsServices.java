package services;

import domain.EnumStatus;
import domain.Tasks;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommandsServices {
    public ArrayList<String> readCommand(String command){
        int index = command.indexOf(" ");
        var finalCommand = new ArrayList<String>();
        if (index != -1){
            finalCommand.add(command.substring(0 , index));
            finalCommand.add(command.substring(index +1));
            return finalCommand;
        } else {
            finalCommand.add(command);
            return  finalCommand;
        }
    }

    public void selectCommand (List<String> command){
        if (command.getFirst().equalsIgnoreCase("add")){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println("Adicionando "+command.get(1));
            try {
                Tasks tasks = new Tasks(1 , command.get(1), EnumStatus.TODO, date, date);
                JSONServices.addTask(tasks);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (command.getFirst().equalsIgnoreCase("update")){
            int index = command.get(1).indexOf(" ");
            System.out.println("Atualizando " + command.get(1).substring(0, index) + " com descrição: " + command.get(1).substring(index +1));
        } else if (command.getFirst().equalsIgnoreCase("delete")){
            int index = command.get(1).indexOf(" ");
            System.out.println("Excluindo " + command.get(1).substring(0, index) + " com descrição: " + command.get(1).substring(index +1));
        } else if (command.getFirst().equalsIgnoreCase("exit")){
            System.out.println("Saindo do Programa");
        } else {
            System.out.println("Comando inválido");
        }
    }

}
