import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {
    public static void main(String[] args) throws IOException {
        // initialize the JFileChooser to allow user to select .txt file
        JFileChooser fileSelection = new JFileChooser();

        // establish filters to select .txt files ONLY
        fileSelection.setAcceptAllFileFilterUsed( false );

        fileSelection.setFileFilter( new FileNameExtensionFilter( "Text Files (.txt)", "txt" ) );

        File selectedFile;
        String lineString;

        // set the initial directory that JFileChooser opens up to as the current directory
        File dir = new File( System.getProperty( "user.dir" ) );
        fileSelection.setCurrentDirectory( dir );

        // if user selects a file and hits okay/select (successful attempt)
        if (fileSelection.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION) {
            // takes the selected file and gets the path, opens file
            selectedFile = fileSelection.getSelectedFile();
            Path path = selectedFile.toPath();
            InputStream in = new BufferedInputStream( Files.newInputStream( path, CREATE ) );
            BufferedReader textFileReader = new BufferedReader( new InputStreamReader( in ) );

            // initialize variables to track the official counts of the lines, words, characters in a specified file
            int linesInDocumentCount = 0;
            int wordsInDocumentCount = 0;
            int charactersInDocumentCount = 0;

            // continues to loop and read the document until there is no more text to read
            while (textFileReader.ready()) {
                lineString = textFileReader.readLine(); // reads the next line
                String[] wordsArray = lineString.split( " " ); // initializes the array to keep track of the words for each line
                wordsInDocumentCount = wordsInDocumentCount + wordsArray.length; // tracks the word count
                charactersInDocumentCount = charactersInDocumentCount + lineString.length(); // tracks the character count
                linesInDocumentCount++; // adds one for each line that is read
            }

            // print out the file alongside the count of lines, words, and characters in file.
            System.out.println("The file that was selected was: " + selectedFile.getName());
            System.out.println( "The number of lines contained in the file is: " + linesInDocumentCount );
            System.out.println( "The number of words contained in the file is: " + wordsInDocumentCount);
            System.out.println( "The total number of characters in the file is: " + charactersInDocumentCount );

        } else {
            // if user does not select file, display file was not selected
            System.out.println("No file was selected.");
        }
    }
}