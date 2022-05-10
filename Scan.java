import java.io.*;
import java.nio.charset.StandardCharsets;

public class Scan implements AutoCloseable {
    String line;
    String res = null;
    BufferedReader reader;
    InputStream inp;

    public Scan(InputStream inp) {
        this.inp = inp;
        reader = new BufferedReader(new InputStreamReader(inp, StandardCharsets.UTF_8));
    }

    public String nextString() {
        line = res;
        res = null;
        return line;
    }

    public boolean hasNextString() {
        StringBuilder bui = new StringBuilder();
        int ch;
        try {
            if (res == null) {
                ch = reader.read();
                while (ch != -1 && ch != (int) '\n') {
                    bui.append((char) ch);
                    ch = reader.read();
                }
                if (bui.length() == 0) {
                    return false;
                }
                res = bui.substring(0, bui.length() - 1);
                bui.setLength(0);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res != null;
    }

    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}