package chess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Command> readInput(String path) {
        Path filePath = Paths.get(path);

        try {
            List<String> lines = Files.readAllLines(filePath);
            return  parseCommands(lines);
        } catch (IOException e) {
            //e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Command> parseCommands(List<String> lines) {
        List<Command> commands = new ArrayList<>();

        for (final String line : lines) {
            try {
                // splitting each line by commas (,)
                final String[] details = line.split(",");
                Command command = parseCommand(details);
                commands.add(command);

            } catch (InvalidCommandException e) {
                System.out.println("Cannot parse command: " + line);
            }
        }
        return commands;
    }

    private static Command parseCommand(String[] details) throws InvalidCommandException {
        // making sure exactly 4 values were extracted from the line of the input file
        if (details.length == 4) {
            Player player = parsePlayer(details[0].trim());
            CommandType commandType = parseCommandType(details[1].trim());
            Location source = parseLocation(details[2].trim());
            Location destination = parseLocation(details[3].trim());

            return new Command(player, commandType, source, destination);
        } else {
            throw new InvalidCommandException();
        }
    }

    private static Player parsePlayer(String p) throws InvalidCommandException {
        // making sure the player is either W or B
        if (p.equals("W") || p.equals("B")) {
            return Player.valueOf(p);
        } else {
            throw new InvalidCommandException();
        }
    }

    private static CommandType parseCommandType (String ct) throws InvalidCommandException {
        // making sure the commandType is either MOVE or EAT
        if (ct.equals("MOVE") || ct.equals("EAT")) {
            return CommandType.valueOf(ct);
        } else {
            throw new InvalidCommandException();
        }
    }

    private static Location parseLocation(String loc) throws InvalidCommandException {
        // making sure the location is of the form (a-h)(1-8)
        if (loc.matches("[a-h][1-8]")){
            int x=0, y=0;

            // convert the string to lower case
            // take the first character
            // take that character's numeric value
            // and reduce 10 (so a becomes 0, h becomes 7)
            x = Character.getNumericValue(loc.toLowerCase().charAt(0)) - 10;
            // parsing the second character of the location as an Integer (substring was used instead of charAt since
            // parseInt takes strings not characters. This could have been done as loc.charAt(1)+"" this becoming a string.
            // and reducing one (since the array is from 0-7, rather than 1-8)
            y = Integer.parseInt(loc.substring(1)) - 1;

            return new Location(x,y);
        } else {
            throw new InvalidCommandException();
        }
    }
}