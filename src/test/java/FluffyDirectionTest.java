import org.junit.jupiter.api.Test;
import model.Fluffy;
import model.State;


class FluffyDirectionTest{

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Test
	void test(){
		new State(0);
		Fluffy fluffy = new Fluffy();
		assert (fluffy.move() == 2);
		assert (fluffy.move() == 4);
		assert (fluffy.move() == 6);
		assert (fluffy.move() == 8);
		assert (fluffy.move() == 1);
		assert (fluffy.move() == 3);
		assert (fluffy.move() == 5);
		assert (fluffy.move() == 7);
		assert (fluffy.move() == 2);
		assert (fluffy.move() == 4);
		assert (fluffy.move() == 6);
		assert (fluffy.move() == 8);
		assert (fluffy.move() == 1);
		assert (fluffy.move() == 3);
		assert (fluffy.move() == 5);
		assert (fluffy.move() == 7);
	}

}
