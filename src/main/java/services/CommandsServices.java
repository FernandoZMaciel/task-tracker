package services;

import domain.Tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandsServices {
    public ArrayList<String> readCommand(String command) {
        int index = command.indexOf(" ");
        var finalCommand = new ArrayList<String>();
        if (index != -1) {
            finalCommand.add(command.substring(0, index));
            finalCommand.add(command.substring(index + 1));
            return finalCommand;
        } else {
            finalCommand.add(command);
            return finalCommand;
        }
    }

    public boolean selectCommand(List<String> command) throws RuntimeException {
        if (command.getFirst().equalsIgnoreCase("add")) {
            try {
                JSONServices.addTask(command.get(1));
                JSONServices.normalizeId();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (command.getFirst().equalsIgnoreCase("update")) {
            int index = command.get(1).indexOf(" ");
            try {
                JSONServices.updateTask(Integer.parseInt(command.get(1).substring(0, index)), command.get(1).substring(index + 1));
                JSONServices.normalizeId();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (command.getFirst().equalsIgnoreCase("delete")) {
            try {
                JSONServices.deleteTask(Integer.parseInt(command.get(1)));
                JSONServices.normalizeId();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (command.getFirst().equalsIgnoreCase("exit")) {
            System.out.println("Saindo do Programa");
            return false;
        } else if (command.getFirst().equalsIgnoreCase("list")) {
            try {
                Tasks tasks = new Tasks();
                tasks.printTasks(JSONServices.listTasks(command.get(1)));
//                System.out.println(JSONServices.listTasks(command.get(1)));
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (command.getFirst().equalsIgnoreCase("mark")) {
            int index = command.get(1).indexOf(" ");
            try {
                JSONServices.updateTasksStatus(Integer.parseInt(command.get(1).substring(0, index)), command.get(1).substring(index + 1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else if (command.getFirst().equalsIgnoreCase("help")) {
           if (command.size()==1){
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Escreva \"add descrição\" para adicionar uma nova task.");
                System.out.println("Escreva \"update id descrição\" para modificar uma task.");
                System.out.println("Escreva \"delete id descrição\" para deletar uma task.");
                System.out.println("Escreva \"delete id descrição\" para deletar uma task.");
                System.out.println("Escreva \"list status \" para listar as tasks.");
                System.out.println("Escreva \"mark id status \" para mudar os status de uma task.");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                return  true;
            }

            if(command.get(1).equalsIgnoreCase("list")) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Escreva \"list status\" para listar as tasks.");
                System.out.println("As opções de status são: all, todo, done, inprogress.");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            if(command.get(1).equalsIgnoreCase("mark")) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Escreva \"mark id status\" para atualizar o status de uma task.");
                System.out.println("As opções de status são: todo, done, inprogress.");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

            return true;
        } else {
            System.out.println("Comando inválido digite \"help\" para ver a lista de comandos");
            return true;
        }
    }

}
