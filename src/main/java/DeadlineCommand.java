import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deadline = new Deadline(this.description, this.by);
            tasks.addTask(deadline);
            ui.formatTaskResponse(deadline, tasks);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    public boolean isExit() {
        return false;
    };
}
