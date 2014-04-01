public class Artikel {
	String forfattere;
	Artikel[] referenceListe;
	String titel;
	Tidsskrift tidsskrift;

	public Artikel(String titelIn, String forfattereIn, Tidsskrift tidsskriftIn) {
		forfattere = forfattereIn;
		titel = titelIn;
		tidsskrift = tidsskriftIn;
	}

	public void setReferenceList(Artikel[] refListeIn) {
		referenceListe = refListeIn;
	}

	public String toString() {
		String output = "";

		output += "\"" + titel + "\" by " + forfattere + "\n\nReferences:\n";

		for (int i = 0; i < referenceListe.length; i++) {
			output += referenceListe[i].forfattere + ": " + referenceListe[i].titel + ", " + referenceListe[i].tidsskrift + "\n";
		}

		return output;
	}
}