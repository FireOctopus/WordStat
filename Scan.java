import java.io.*;

public class Scan {
	String line;
	String res = null;
	BufferedReader reader = null;
	InputStream inp;
	public Scan (InputStream inp) {
		this.inp = inp;
		try {
			reader = new BufferedReader(new InputStreamReader(inp,"UTF-8"));
		} catch (UnsupportedEncodingException e){
			System.out.println ("UnsupportedEncodingException" + e.getMessage());
		}
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
				res = bui.toString().substring(0, bui.length() - 1);
				bui.setLength(0);
			}
		} catch (IOException e) {
			System.out.println (e.getMessage());
		} finally {
			return res != null;
		}
	}
	public void close() {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			System.out.println (e.getMessage());
		}
	}
}