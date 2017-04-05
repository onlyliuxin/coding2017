import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Refactorings {
    public static final String TXT = "txt";

    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("input." + TXT);
        String[] array = getStrings(
                in);
        Arrays.sort(array);
        for (String s : array) {
            System.out.println(s);
        }
    }

    private static String[] getStrings(FileReader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines.toArray(new String[lines.size()]);
    }
}