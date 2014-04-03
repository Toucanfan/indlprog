public class Tidsskrift {
	public String titel;
	private Forlag forlag;
	private String issn;

	public Tidsskrift(String titel) {
		this.titel = titel;
	}

	public Forlag getForlag() {
		return this.forlag;
	}

	public void setForlag(Forlag forlag) {
		this.forlag = forlag;
	}

	public String getIssn() {
		return this.issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String toString() {
		return String.format("Tidsskrift(\"%s\")", titel.toString());
	}
}
