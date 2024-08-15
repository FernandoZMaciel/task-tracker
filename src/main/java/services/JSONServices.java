package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Tasks;

public class JSONServices {
    public static void addTask(Tasks tasks) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Adicionando " +tasks.getDescription());
        List<Tasks> listTasks = listTasks("all");
        listTasks.add(tasks);
        objectMapper.writeValue(new File("src/main/java/json/data.json"), listTasks);
        System.out.println("Task adicionada com sucesso!");
    }

    public static void updateTask(int id, String description) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Atualizando " + id);
        List<Tasks> listTasks = listTasks("all");
        for (int i = 0; i < listTasks.size(); i++) {
            if(listTasks.get(i).getId() == id){
                listTasks.get(i).setDescription(description);
            }
        }
        objectMapper.writeValue(new File("src/main/java/json/data.json"), listTasks);
        System.out.println("Task atualizada com sucesso!");
    }

    public static List<Tasks> listTasks(String command) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/java/json/data.json");

        if (!file.exists()) {
            return new ArrayList<>();
        }

        if (Stream.of("todo", "done", "inprogress").anyMatch(command::equalsIgnoreCase)){
            List<Tasks> fullListTasks =objectMapper.readValue(file, new TypeReference<List<Tasks>>() {} );
            List<Tasks> listTasks = new ArrayList<Tasks>();
            for (int i = 0; i < fullListTasks.size(); i++) {
                if(fullListTasks.get(i).getEnumStatus().toString().equalsIgnoreCase(command)){
                    listTasks.add(fullListTasks.get(i));
                }
            }
            return listTasks;
        } else if (command.equalsIgnoreCase("all")){
            return objectMapper.readValue(file, new TypeReference<List<Tasks>>() {} );
        } else return new ArrayList<>();

    }


}
