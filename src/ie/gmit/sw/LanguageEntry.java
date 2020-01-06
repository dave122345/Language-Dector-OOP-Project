package ie.gmit.sw;

/**
 *  @author David Nolan G00363191
 * @version 1.0
 * @since 1.8
 * 
 *<b>LanguageEntry class</b> is used for when needed 
 * to create a new Language entry within the database. 
 * Also makes it easier to know what <i>language</i> 
 * is what for the query.
 * 
 */
public class LanguageEntry implements Comparable<LanguageEntry> {
	private int kmer;
	private int frequency;
	private int rank;

	public LanguageEntry(int kmer, int frequency) {
		super();
		this.kmer = kmer;
		this.frequency = frequency;
	}

	public int getKmer() {
		return kmer;
	}

	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(LanguageEntry next) {
		return -Integer.compare(frequency, next.getFrequency());
	}

	@Override
	public String toString() {
		return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}
}