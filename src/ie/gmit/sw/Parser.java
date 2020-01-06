package ie.gmit.sw;

/**
 *  @author David Nolan G00363191
 * @version 1.0
 * @since 1.8
 * Parser class containing everything needed to
 * read the file and to go through the method
 * to check what language the sentence
 * in the query file is.
 */
import java.io.*;
import java.util.*;

public class Parser implements Runnable {

	private Database db = null;
	private String file;
	private int k;

	public Parser(String file, int k) {
		this.file = file;
		this.k = k;
	}

	public void setDb(Database db) {
		this.db = db;
	}

	@Override
	public void run() {

		System.out.print("processing files... \n");

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] record = line.trim().split("@");
				if (record.length != 2)
					continue;
				// System.out.print("r1: " + record[0] + "\nr2:" + record[1] );
				parse(record[0], record[1]);

			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parse(String text, String lang, int... ks) {
		Language language = Language.valueOf(lang);

		for (int i = 0; i <= text.length() - k; i++) {
			CharSequence kner = text.substring(i, i + k);
			db.add(kner, language);
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */

	public void analyseQuery(String file) throws IOException {
		System.out.println("Loading result... \n");
		int kmers = 0;
		int freq = 1;

		String queryFile;

		Map<Integer, LanguageEntry> query = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		while ((queryFile = br.readLine()) != null) {

			for (int i = 0; i <= queryFile.length() - k; i++) {
				CharSequence charSeq = queryFile.substring(i, i + k);
				kmers = charSeq.hashCode();

				if (query.containsKey(kmers)) {
					freq += query.get(kmers).getFrequency();
				}

				LanguageEntry l = new LanguageEntry(kmers, freq);
				query.put(kmers, l);
			}

		}
		Language languageMatch = db.getLanguage(query);
		System.out.println("Language is :" + languageMatch);

	}// end of analyseQuery

}// EOF
