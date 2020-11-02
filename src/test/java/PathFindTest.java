import org.junit.jupiter.api.Test;
import model.Entity;


class PathFindTest{

	/*       -
	 * 	   -[1] [2] [3]+
	 * 		[8] [0] [4]
	 * 		[7] [6] [5]
	 *       +
	 */
	@Test
	void test(){
		assert (Entity.pathfind(3, 3, 3, 3) == 0);
		assert (Entity.pathfind(3, 3, 1, 1) == 1);
		assert (Entity.pathfind(3, 3, 3, 1) == 2);
		assert (Entity.pathfind(3, 3, 5, 1) == 3);
		assert (Entity.pathfind(3, 3, 5, 3) == 4);
		assert (Entity.pathfind(3, 3, 1, 3) == 8);
		assert (Entity.pathfind(3, 3, 1, 5) == 7);
		assert (Entity.pathfind(3, 3, 3, 5) == 6);
		assert (Entity.pathfind(3, 3, 5, 5) == 5);

	}

}
