public class Complex {
	
	private double re;
	private double im;
	
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	public Complex() {
		this(0,0);
	}
	
	public Complex(Complex z) {
		this(z.getRe(),z.getIm());
	}
	
	public double getRe() {
		return re;
	}
	
	public double getIm() {
		return im;
	}
	
	public double abs() {
		return Math.sqrt(re*re+im*im);
	}

	public Complex plus(Complex other) {
		return new Complex(re+other.getRe(),im+other.getIm());
	}

	public Complex addRe(Double addedRe) {
		return new Complex(re+addedRe,im);
	}
	
	public Complex addIm(Double addedIm) {
		return new Complex(re,im+addedIm);
	}
	
	public Complex setRe(Double setRe) {
		return new Complex(setRe,im);
	}
	
	public Complex setIm(Double setIm) {
		return new Complex(re,setIm);
	}
	
	public Complex times(Complex other) {
		double re = this.re*other.getRe() - this.im*other.getIm();
		double im = this.re*other.getIm() + this.im*other.getRe();
		return new Complex(re,im);
	}

	public String toString() {
		return re + " + i" + im;
	}
	
}
