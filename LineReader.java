import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 */
public class LineReader {
	private static final File LOCAL_DIR  = new File(".");
	private File	baseDirectory;

	public LineReader() {
		this(LOCAL_DIR);
	}
	
	public LineReader(String baseDirectory) {
		this(new File(baseDirectory));
	}
	
	public LineReader(File baseDirectory) {
		if (!baseDirectory.isDirectory())
			throw new IllegalArgumentException(baseDirectory.getAbsolutePath() + " is not a directory");
		this.baseDirectory = baseDirectory;
	}
	
	public String[] readArray(String fileName) {
		List<String> lines = read(fileName);
		String[] arr = new String[lines.size()];
		lines.toArray(arr);
		return arr;
	}

	/**
	 * @param fileName
	 * @return
	 */
	private List<String> read(String fileName) {
		File f = createFile(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			List<String> lines = new ArrayList<String>();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (acceptable(line))
					lines.add(line);
			}			
			reader.close();
			return lines;
		} catch (IOException e) {
			throw new RuntimeException("failed to read file: " + f.getAbsolutePath(), e);
		}
	}

	/**
	 * @param line
	 * @return
	 */
	private boolean acceptable(String line) {
		line = line.trim();
		if (line.length() == 0)
		return false;
		if (line.startsWith("//"))
			return false;
		if (line.startsWith("#"))
			return false;
		return true;
	}
	
	/**
	 * @param fileName
	 * @return
	 */
	private File createFile(String fileName) {
		File f = new File(baseDirectory, fileName);
		if (f.exists())
			return f;
		String firstPath = f.getAbsolutePath();
		f = new File(LOCAL_DIR, fileName);
		if (f.exists())
			return f;
		throw new IllegalArgumentException(String.format("cannot find file at %s or %s", firstPath, f.getAbsolutePath()));
	}
	
	public static void main(String[] args) {
		LineReader r = new LineReader();
		String[] lines = r.readArray("src/LineReader.java");
		
		for (String l : lines)
			System.err.format("%s\n", l);
	}
}
