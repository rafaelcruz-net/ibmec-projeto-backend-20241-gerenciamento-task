package br.com.ibmec.backend.taskmanager.controller;

import br.com.ibmec.backend.taskmanager.model.Task;
import br.com.ibmec.backend.taskmanager.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Task> tasks = this.repository.findAll();
        model.addAttribute("listaTask", tasks);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid Task task, BindingResult result) {

        if (result.hasErrors()) {
            return "create";
        }

        task.setStatus("CRIADA");

        this.repository.save(task);
        return "redirect:/task";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        Optional<Task> task = this.repository.findById(id);

        if (task.isEmpty() == true) {
            return "redirect:/task";
        }

        this.repository.delete(task.get());
        return "redirect:/task";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Task> task = this.repository.findById(id);
        model.addAttribute("task", task.get());
        return "edit";
    }

    @PostMapping("/save-edit/{id}")
    public String edit(@PathVariable("id") int id, Task newData) {
        Task task = this.repository.findById(id).get();

        task.setStatus(newData.getStatus());
        task.setName(newData.getName());
        task.setOwner(newData.getOwner());
        task.setDescription(newData.getDescription());

        this.repository.save(task);
        return "redirect:/task";
    }

}
