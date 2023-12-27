package App.ittani.TodoApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class TodoService {
    private static int todosCount=0;
    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    private static List<Todo> todoList  = new ArrayList<>();

    static {
        todoList.add(new Todo(++todosCount, "Learn Java", LocalDate.now().plusYears(1), true));
        todoList.add(new Todo(++todosCount, "Study Engineering", LocalDate.now().plusYears(2), true));
        todoList.add(new Todo(++todosCount, "Sell Oranges", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(++todosCount, "Play Music", LocalDate.now().plusYears(2), true));
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void addTodo(String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, description, targetDate, done);
        todoList.add(todo);
        logger.info("New todo added: {}", todo);
    }

    public void deleteTodoById(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
        logger.info("Todo deleted with id: {}", id);
    }


    public Todo getTodoById(int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null; // Handle the case where no todo with the given id is found
    }
    public void updateTodo(int id, String description, LocalDate targetDate, boolean done) {
        Todo todoToUpdate = getTodoById(id);
        if (todoToUpdate != null) {
            // Update the properties of the existing todo
            todoToUpdate.setDescription(description);
            todoToUpdate.setTargetDate(targetDate);
            todoToUpdate.setDone(done);

            logger.info("Todo updated: {}", todoToUpdate);
        } else {
            // Handle the case where no todo with the given id is found
            logger.warn("Todo with id {} not found for update.", id);
        }
    }
    public void addTodo(String username, String description, LocalDate targetDate, boolean done )
    {
   Todo todo = new Todo(++todosCount,description,targetDate,done);
   todoList.add(todo);
        logger.info("New todo added: {}", todo);
    }
}
