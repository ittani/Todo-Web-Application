package App.ittani.TodoApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("/Todo")
    public String DisplayTodo(Model model) {
        List<Todo> allTodos = todoService.getTodoList();
        model.addAttribute("todos", allTodos);
        return "Todo";
    }

    @RequestMapping(value = "/addTodopage", method = RequestMethod.GET)
    public String showNewTodopage() {
        return "AddTodo";
    }

    @RequestMapping(value = "/addTodopage", method = RequestMethod.POST)
    public String addNewTodoPage(
            @RequestParam("description") String description, Model model) {

        // Log the contents of allTodos before adding a new todo for debugging
        List<Todo> allTodosBefore = getAllTodos();
        logger.info("All Todos before adding new todo: {}", allTodosBefore);

        todoService.addTodo(description, LocalDate.now().plusYears(1), false);

        // Log the contents of allTodos after adding a new todo for debugging
        List<Todo> allTodosAfter = getAllTodos();
        logger.info("All Todos after adding new todo: {}", allTodosAfter);

        return "redirect:/Todo";
    }
    @PostMapping("/deleteTodo")
    public String deleteTodo(@RequestParam("id") int id, Model model) {
        // Log the contents of allTodos before deleting the todo for debugging
        List<Todo> allTodosBefore = getAllTodos();
        logger.info("All Todos before deleting todo: {}", allTodosBefore);

        todoService.deleteTodoById(id);

        // Log the contents of allTodos after deleting the todo for debugging
        List<Todo> allTodosAfter = getAllTodos();
        logger.info("All Todos after deleting todo: {}", allTodosAfter);

        return "redirect:/Todo";
    }
    @RequestMapping("/updateTodo")
    public String showUpdateTodoPage(@RequestParam("id") int id, Model model) {
        Todo todoToUpdate = todoService.getTodoById(id);
        model.addAttribute("todoToUpdate", todoToUpdate);
        return "UpdateTodo";
    }

    @RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
    public String updateTodo(
            @RequestParam("id") int id,
            @RequestParam("description") String description,
            @RequestParam("targetDate") LocalDate targetDate,
            @RequestParam("done") boolean done,
            Model model) {
        todoService.updateTodo(id, description, targetDate, done);
        return "redirect:/Todo";
    }

    private List<Todo> getAllTodos() {
        return todoService.getTodoList();
    }
}
