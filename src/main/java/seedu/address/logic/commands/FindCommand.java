package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;




/**
 * Represents a command to Find details of a person.
 * Keyword matching is case insensitive
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Use either of the parameters below\n "
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + PREFIX_PHONE + "PHONE \n"
            + PREFIX_ROOM_NUMBER + "ROOM_NUMBER \n"
            + PREFIX_TAG + "TAGS";

    private final Predicate<Person> combinedPredicate;


    /**
     * @param combinedPredicate the search condition for personlist update
     */
    public FindCommand(Predicate<Person> combinedPredicate) {
        this.combinedPredicate = combinedPredicate;
    }

    /**
     * @param model
     * @return a string denote success
     */
    @Override
    public CommandResult execute(Model model) {
        requireNotExecuted();
        requireNonNull(model);
        model.updateFilteredPersonList(combinedPredicate);
        isExecuted = true;
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return combinedPredicate.equals(otherFindCommand.combinedPredicate);
    }



    /**
     * @return a string represents FindCommand
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("find predicate", combinedPredicate)
                .toString();
    }
}
