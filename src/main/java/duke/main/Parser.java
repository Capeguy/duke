package duke.main;

import duke.command.*;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

/**
 * Class that parses user inputs into executed commands
 */
public class Parser {
    /**
     * Parses user input to run respective commands
     * @param input String user input
     * @param taskList TaskList object containing list of Tasks
     * @return boolean true if ByeCommand is invoked by user
     */
    static boolean parseCommand(String input, TaskList taskList) {
        try {
            String param;
            String[] tokens = input.split(" ", 2);
            Command.CommandType command = Command.CommandType.valueOf(tokens[0].toUpperCase());
            if (tokens.length > 1) {
                param = tokens[1];
            } else {
                param = "";
            }
            switch (command) {
                case CLEAR:
                    ClearCommand.run(taskList);
                    break;
                case TODO:
                    TodoCommand.run(taskList, param);
                    break;
                case DEADLINE:
                    DeadlineCommand.run(taskList, param);
                    break;
                case EVENT:
                    EventCommand.run(taskList, param);
                    break;
                case BYE:
                    ByeCommand.run();
                    return false;
                case LIST:
                    ListCommand.run(taskList);
                    break;
                case DONE:
                    DoneCommand.run(taskList, param);
                    break;
                case DELETE:
                    DeleteCommand.run(taskList, param);
                    break;
                case CALENDAR:
                    CalendarCommand.run(taskList, param);
                    break;
                default:
                    throw new UnknownCommandException();
            }
        } catch (DukeException ex) {
            System.out.println(ex);
        }
        return true;
    }
}
