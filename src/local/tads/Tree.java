package local.tads;

class Tree
{
	private Node root;
	
	protected Tree() { root = new Node( 0 ); }
	
	protected Tree( int newValue ) { root = new Node( newValue ); }
	
	protected Node left() { return root.left; }
	
	protected Node right() { return root.right; }
	
	protected int value() { return root.value; }
}
