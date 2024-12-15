package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.LoggingService;
import fr.eni.ludotheque.bll.LoggingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    @GetMapping("/logging")
    public String voirLogs(Model model) {
        List<String> logs = loggingService.getLastLogs(20);
        model.addAttribute("logs", logs);
        return "logging";
    }
}
