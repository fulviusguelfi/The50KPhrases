package the50kphrases;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author fulvi
 */
public class The50KPhrases {

    private final Path file;
    private final Map<String, Integer> the50KMoreUsed = new LinkedHashMap<>();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length > 0) {
            The50KPhrases the50KPhrases = new The50KPhrases(Paths.get(args[0]));
            the50KPhrases.populateThe50KMoreUsed();
            List<String> list = the50KPhrases.showThe50KMoreUsed();
            System.out.println(list);
            System.out.print("List size: ");
            System.out.println(list.size());
        } else {
            int lineToCreateFile = 50001;
            Path file = Paths.get("The50KPhrases.TextFile.txt");

            if (!Files.exists(file)) {
                String content = " Hello World! | ";
                String phrase = "";
                String line;

                System.out.println("Prepare Line ");
                for (int i = 0; i < 50; i++) {
                    phrase += "<phrase #" + i + ">/" + content;
                }

                System.out.println("Loading File... ");
                Files.createFile(file);

                int buffferSize = 1024 * 150;
                ByteBuffer buf;
                buf = ByteBuffer.allocate(buffferSize);

                RandomAccessFile aFile = new RandomAccessFile(file.toFile(), "rw");
                GatheringByteChannel fc;
                fc = aFile.getChannel();

                for (int j = 0; j < lineToCreateFile; j++) {
                    line = "<line #" + j + ">/" + phrase + System.getProperty("line.separator");
                    buf.put(line.getBytes());
                    if (j % 100 == 0) {
                        buf.flip();
                        while (buf.hasRemaining() && fc.isOpen()) {
                            fc.write(buf);
                        }
//                        Files.write(file, line.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.DSYNC);
                        buf.clear();
                        buf = ByteBuffer.allocate(buffferSize);
                        System.out.print("\rFile Size: ");
                        System.out.print(Files.size(file) / 1024 / 1024);
                        System.out.print(" MB");
                    }
                }
                fc.close();
            }
            The50KPhrases the50KPhrases = new The50KPhrases(file);
            the50KPhrases.populateThe50KMoreUsed();
            List l = the50KPhrases.showThe50KMoreUsed();
            System.out.println("");
            System.out.print("list size:");
            System.out.println(l.size());
            System.out.print("File Created");
        }
    }

    public The50KPhrases(Path file) {
        this.file = file;
    }

    /**
     *
     * @throws IOException
     */
    public void populateThe50KMoreUsed() throws IOException {
        Files.lines(this.file, Charset.defaultCharset())
                .map((String line) -> Arrays.asList(line.split("\\|", 50)))
                .flatMap((List<String> a) -> a.stream())
                .forEach((String phrase) -> this.the50KMoreUsed.merge(phrase, 1, Integer::sum));
        System.out.print("Object Complete Size: ");
        System.out.println(this.the50KMoreUsed.size());
//        
    }

    /**
     *
     * @return a List of 50.000 more used
     */
    public List showThe50KMoreUsed() {
        return this.the50KMoreUsed.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((Integer o1, Integer o2) -> -1 * o1.compareTo(o2)))
                .map(Map.Entry::getKey).limit(50000)
                .collect(Collectors.toList());
    }
}
