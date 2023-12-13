package tz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tz.service.ExecutorService;

@Controller
@PreAuthorize("hasRole('EXECUTOR')")
@RequestMapping("/executor")
@RequiredArgsConstructor
public class ExecutorController {
    private final ExecutorService executorService;

    @GetMapping("/myTasks/{limit}")
    public String myTasksPage(@PathVariable Integer limit, Model model) {
        model.addAttribute("tasks", executorService.getExecutorTasks(limit));
        return "executor/myTasks";
    }

    @GetMapping("/changeStatus/{id}")
    public String changeStatusPage(@PathVariable Integer id, Model model) {
        model.addAttribute("task", executorService.getById(id));
        return "executor/changeStatus";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(@RequestParam Integer id, @RequestParam String status) {
        executorService.changeStatus(id, status);
        return "redirect:/executor/myTasks/0";
    }
}
