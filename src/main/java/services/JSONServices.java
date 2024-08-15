package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Tasks;

public class JSONServices {
    public static void addTask(Tasks tasks) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Tasks> listTasks = new ArrayList<>();
        listTasks.add(tasks);
        objectMapper.writeValue(new File("src/main/java/json/data.json"), listTasks);
    }
}
