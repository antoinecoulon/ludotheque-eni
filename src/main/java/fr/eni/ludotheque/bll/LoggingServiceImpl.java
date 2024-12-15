package fr.eni.ludotheque.bll;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoggingServiceImpl implements LoggingService {

    private static final String LOG_FILE_PATH = "logs/ludotheque.log";

    @Override
    public List<String> getLastLogs(int numberOfLines) {
        List<String> logs = new ArrayList<>();
        try {
            Path path = Paths.get(LOG_FILE_PATH);
            if(Files.exists(path)) {
                List<String> allLines = Files.readAllLines(path);
                int start = Math.max(0, allLines.size() - numberOfLines);
                logs = allLines.subList(start, allLines.size());
            }
        } catch (IOException e) {
            logs.add("Erreur lors de la lecture du fichier de log : " + e.getMessage());
        }
        return logs;
    }
}
