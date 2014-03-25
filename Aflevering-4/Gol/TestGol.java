public class TestGol {
	public static void main(String[] args) {
		GameOfLife gol = new GameOfLife(10);

		gol.world[5][5] = 1;
		gol.world[5][4] = 1;
		gol.world[5][3] = 1;
		gol.world[4][3] = 1;
		gol.world[3][4] = 1;

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);

		gol.nextState();

		System.out.println(gol);
	}
}