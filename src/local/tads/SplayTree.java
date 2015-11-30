package local.tads;

public class SplayTree extends Tree
{
	private Node splay( Node root, int key )
	{
		if( root == null )
			return root;
		
		Tree leftTree = null, rightTree = null;
		
		if( key > root.value )
		{
			leftTree = new Tree( root );
			return splay( root.right, key );
		}
		else if( key < root.value )
		{
			rightTree = new Tree( root );
			return splay( root.left, key );
		}
	}
}
