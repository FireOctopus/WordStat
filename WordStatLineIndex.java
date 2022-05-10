import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordStatLineIndex {
    public static void main(String[] args) {
        int i;
        int num = 1;
        int j;
        LinkedHashMap<String, ArrayList<String>> words = new LinkedHashMap<>();
        StringBuilder str = new StringBuilder();
        try {
            try(Scan myscan = new Scan(new FileInputStream(args[0]))) {
                String line;
                while (myscan.hasNextString()) {
                    j = 1;
                    i = 0;
                    line = myscan.nextString();
                    line = line + " ";
                    while (i < line.length()) {
                        if ((Character.isLetter(line.charAt(i)) ||
                                (Character.getType(line.charAt(i)) == Character.DASH_PUNCTUATION) ||
                                (line.charAt(i)) == '\'')) {
                            str.append(line.charAt(i));
                        } else if (!((str.toString()).isEmpty())) {
                            String tmp = str.toString().toLowerCase();
                            if (!words.containsKey(tmp)) {
                                words.put(tmp, new ArrayList<>());
                            }
                            words.get(tmp).add((num) + ":" + (j));
                            j++;
                            str.setLength(0);
                        }
                        i++;
                    }
                    num++;
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
                for (Map.Entry<String, ArrayList<String>> k : words.entrySet()) {
                    writer.write(k.getKey() + " " + k.getValue().size());
                    for (String index : k.getValue()) {
                        writer.write(" " + index);
                    }
                    writer.newLine();
                }
            }
            System.out.println("Program worked successfully");
        } catch (FileNotFoundException error) {
            System.out.println("File not found: " + error.getMessage());
        } catch (IOException error) {
            System.out.println("IO exception: " + error.getMessage());
        }
    }
}