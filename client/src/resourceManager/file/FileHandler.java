package src.resourceManager.file;

import java.io.*;
import java.util.Scanner;

/**
 * @author Oscar Andersson
 *
 * This class handles all reading and writing of information to files.
 */
public class FileHandler implements ImplFileHandler {

    private static FileHandler instance = null;

    /* Singleton Designpattern
     */
    public static FileHandler getInstance() {
        if (instance == null) instance = new FileHandler();
        return instance;
    }

    /* This method recieves one string containing the filename.
     * 
     * A stringbuilder is created and is later converted and returned as a string.
     * 
     */

    @Override
    public String readFile(String fileName) {
        final StringBuilder tempArr = new StringBuilder(); 
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f = new File(System.getProperty("user.home") + File.separator + fileName);
            }
            final FileReader fr = new FileReader(f);
            final Scanner in = new Scanner(fr);

            /* As this while loop "steps" through every row of the specified file,
             * the stringbuilder adds each line to itself along with a "\n" char.
             */
            while (in.hasNextLine()){
                tempArr.append(in.nextLine());
                tempArr.append("\n"); //Write \n to the builder after each scanned row in file
            }

            in.close();
        } catch (IOException e) {
            System.out.println("Read Error");
        }
        return tempArr.toString();
    }

    /* This method recieves 2 strings, a fileName and a string to write in the file
     * 
     * The content string will be "converted" into an
     * string array, devided by every \n character.
     * 
     * And finally it writes the content of the string array to the assigned file, 
     * each element of the string array becomes a row in the file.
     * Returning true if the write was successful, false otherwise.
     */

    @Override
    public Boolean writeFile(String fileName, String writeContent) {
        final String[] splitContent = writeContent.split("\n");
        try {
            PrintWriter out = new PrintWriter(fileName);
            for (int steg = 0; steg < splitContent.length; steg++) {
                out.println(splitContent[steg]);
            }

            out.close();
            return true; //return true if writing was a success
        } catch (IOException e) {
            return false; //return false if writing was not a success
        }
    }
}
