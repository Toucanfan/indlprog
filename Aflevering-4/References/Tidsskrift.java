public class Tidsskrift {
	String navn;
	String issn;
	Forlag forlag;

	public Tidsskrift(String navnIn) {
		navn = navnIn;
	}

	public void setISSN(String issnIn) {
		issn = issnIn;
	}

	public void setForlag(Forlag forlagIn) {
		forlag = forlagIn;
	}

	public String toString() {
		return navn + ", " + forlag;
	}
}