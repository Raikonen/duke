import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskFileManager {

    private File taskFile;

    public TaskFileManager(File file) {
        this.taskFile = file;
    }

    public void writeListToFile(List<Task> list) {
        try {
            String textToAdd = "";
            FileWriter fw = new FileWriter(taskFile.getPath());
            for (Task t : list) {
                textToAdd += t.serialise();
            }
            fw.write(textToAdd);
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(taskFile.getPath(), true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(taskFile); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                Task t = Task.deserialize(s.nextLine());
                taskList.add(t);
            }
        } catch (FileNotFoundException e) {
            // If no file, create the file
            System.out.println("List is currently empty");
        }
        return taskList;
    }

}