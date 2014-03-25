public class Forlag {
	String navn;
	String sted;

	public Forlag(String navnIn, String stedIn) {
		navn = navnIn;
		sted = stedIn;
	}

	public String toString() {
		return navn + ", " + sted; 
	}	
}