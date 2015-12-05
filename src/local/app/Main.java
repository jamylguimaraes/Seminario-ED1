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
		tree.insert(6);
		tree.insert(5);
		tree.insert(8);
		tree.insert(2);
		tree.insert(4);
		tree.display();
		System.out.println("Inserido: 6,5,8,2,4");
		tree.find(5);
		tree.display();
		System.out.println("Find: 5");
		tree.remove(5);
		tree.display();
		System.out.println("Remove: 5");
	}
}
