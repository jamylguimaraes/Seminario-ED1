/**
 * Esta classe é o programa propriamente dito.
 */
package local.app;

import local.tads.*;

public class Main
{
	private static SplayTree tree;

	
	public static void main( String[] args ) throws Exception
	{
		tree  = new SplayTree();
		tree.insert(5);
		tree.insert(8);
		tree.insert(2);
		tree.insert(4);
		tree.display();
		tree.find(5);
		tree.display();
		tree.remove(5);
		tree.display();
	}
}
