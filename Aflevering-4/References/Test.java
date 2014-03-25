public class Test {
	public static void main(String[] args){
		Forlag up = new Forlag("University Press", "Denmark");

		Tidsskrift jol = new Tidsskrift("Journal of Logic");
		jol.setForlag(up);

		Tidsskrift brain = new Tidsskrift("Brain");
		brain.setForlag(up);

		Artikel a = new Artikel("A", "A. Abe & A. Turing", jol);
		Artikel b = new Artikel("B", "B. Bim", brain);

		Artikel[] refs = {b};

		a.setReferenceList(refs);

		System.out.println(a);
	}
}