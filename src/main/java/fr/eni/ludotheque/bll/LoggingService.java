package fr.eni.ludotheque.bll;

import java.util.List;

public interface LoggingService {
    List<String> getLastLogs(int numberOfLines);
}
