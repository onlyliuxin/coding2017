import java.nio.file.*;
import java.util.*;
import java.util.List;

public class Watcher {
    public static void main(String[] args) {
        Path this_dir = Paths.get(".");
        System.out.println("Now watching the current directory ...");

        try {
            WatchService watcher = this_dir.getFileSystem().newWatchService();
            this_dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            WatchKey watckKey = watcher.take();

            List<WatchEvent<?>> events = watckKey.pollEvents();
            for (WatchEvent event : events) {
                System.out.println("Someone just created the file '" + event.context().toString() + "'.");

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}