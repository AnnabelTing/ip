package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class ListCommandTest {
    @Test
    public void execute_emptyList_success() {
        ListCommand listCommand = new ListCommand();
        String result = listCommand.execute(new TaskList(), new Ui(), new Storage("data/testDuke.txt"));
        Assertions.assertEquals("There are currently no tasks in your list.", result);
    }
    @Test
    public void execute_taskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("read 25 books"));
        taskList.addTask(new Deadline("write 100 reviews", LocalDate.parse("2023-12-12")));
        taskList.addTask(new Event("sleep", "12am", "12pm"));
        ListCommand listCommand = new ListCommand();
        String result = listCommand.execute(taskList, new Ui(), new Storage("data/testDuke.txt"));
        Assertions.assertEquals("Here are the task(s) in your list:" + System.lineSeparator()
                + "1. [T][ ] read 25 books" + System.lineSeparator()
                + "2. [D][ ] write 100 reviews (by: Dec 12 2023)" + System.lineSeparator()
                + "3. [E][ ] sleep (from: 12am to: 12pm)", result);
    }
}

