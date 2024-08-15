package services;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.EnumStatus;
import domain.Tasks;

public class JSONServices {
    public static void addTask(String description) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        List<Tasks> listTasks = listTasks("all");
        Tasks tasks = new Tasks(listTasks.size() + 1, description, EnumStatus.TODO, date, date);
        listTasks.add(tasks);
        System.out.println("Adicionando " +tasks.getDescription());
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
                listTasks.get(i).setUpdatedAt(new Date());
            }
        }
        objectMapper.writeValue(new File("src/main/java/json/data.json"), listTasks);
        System.out.println("Task atualizada com sucesso!");
    }

    public static void deleteTask(int id ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Deletando " + id);
        List<Tasks> listTasks = listTasks("all");
        List<Tasks> newListTasks = new ArrayList<>();
        for (int i = 0; i < listTasks.size(); i++) {
            if(id != listTasks.get(i).getId()){
                newListTasks.add(listTasks.get(i));
            }
        }
        objectMapper.writeValue(new File("src/main/java/json/data.json"), newListTasks);
        System.out.println("Task deletada com sucesso!");
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

    public static void normalizeId() throws IOException {
        List<Tasks> normalizedlistTasks = listTasks("all");
        for (int i = 0; i < normalizedlistTasks.size(); i++) {
            normalizedlistTasks.get(i).setId(i+1);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/java/json/data.json"), normalizedlistTasks);
    }

}
