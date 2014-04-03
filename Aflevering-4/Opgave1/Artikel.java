public class Artikel {
	public String[] forfattere;
	public String titel;
	public Tidsskrift tidsskrift;
	private Artikel[] referenceliste;

	public Artikel(String titel,
						String[] forfattere,
						Tidsskrift tidsskrift) {
		this.forfattere = forfattere;
		this.titel = titel;
		this.tidsskrift = tidsskrift;
	}

	public void setReferenceListe(Artikel[] liste) {
		this.referenceliste = liste;
	}

	public String toString() {
		String rval = "Artikel(\"%s\", %s, %s)";
		return String.format(rval, 
			titel.toString(), forfattere.toString(),
			tidsskrift.toString());
	}

	public Artikel[] getReferenceListe() {
		return this.referenceliste;
	}
}



