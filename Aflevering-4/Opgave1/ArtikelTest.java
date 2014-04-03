public class ArtikelTest {
	public static void main(String[] args) {
		Forlag up = new Forlag("University Press", "Denmark");
		Tidsskrift jol = new Tidsskrift("Journal of Logic");
		Tidsskrift brn = new Tidsskrift("Brain");

		jol.setForlag(up);
		brn.setForlag(up);

		Artikel a = new Artikel("A", new String[] {"A. Abe", "A. Turing"}, jol);
		Artikel b = new Artikel("B", new String[] {"B. Bim",}, jol);

		a.setReferenceListe(new Artikel[] {b,});

		System.out.println(up.toString());
		System.out.println(jol.toString());
		System.out.println(brn.toString());
		System.out.println(a.toString());
		System.out.println(b.toString());
	}
}
