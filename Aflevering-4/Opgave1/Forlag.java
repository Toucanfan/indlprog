public class Forlag {
	private String navn;
	private String sted;

	public Forlag(String navn, String sted) {
		this.navn = navn;
		this.sted = sted;
	}

	public String toString() {
		return String.format("Forlag(\"%s\", \"%s\")", navn.toString(), sted.toString());
	}
}
