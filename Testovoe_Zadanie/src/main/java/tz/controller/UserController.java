package tz.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tz.dto.Task;
import tz.service.TaskService;
import tz.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
@PreAuthorize("hasRole('USER')")
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/allTasks/{limit}")
    public String allTasksPage(@PathVariable Integer limit, Model model) {
        model.addAttribute("tasks", taskService.getAll(limit));
        return "user/tasks";
    }

    @GetMapping("/myTasks/{limit}")
    public String myTasksPage(@PathVariable Integer limit, Model model) {
        model.addAttribute("tasks", taskService.getAllByUser(limit));
        return "user/myTasks";
    }

    @GetMapping("/create")
    public String createTaskPage(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("executors", userService.getAllExecutor());
        return "user/createTask";
    }

    @PostMapping("/create")
    public String createTask(@Valid @ModelAttribute Task task, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("executors", userService.getAllExecutor());
            return "user/createTask";
        }
        taskService.create(task);
        return "redirect:/task/myTasks/0";
    }

    @GetMapping("/update/{id}")
    public String updateTaskPage(@PathVariable Integer id, Model model) {
        model.addAttribute("task", taskService.get(id));
        model.addAttribute("executors", userService.getAllExecutor());
        return "user/updateTask";
    }

    @PostMapping("/update")
    public String updateTask(@Valid @ModelAttribute Task task, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("executors", userService.getAllExecutor());
            return "user/updateTask";
        }
        taskService.update(task);
        return "redirect:/task/myTasks/0";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskService.delete(id);
        return "redirect:/task/myTasks/0";
    }
}
