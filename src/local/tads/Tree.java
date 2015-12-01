package local.tads;

class Tree
{
	private Node root;
	
	protected Tree() { root = new Node( 0 ); }
	
	protected Tree( Node newNode ) { root = newNode; }
	
	protected Node add( Node newNode ) { return add( this.root, newNode ); }
	
	private Node add( Node root, Node newNode )
	{
		if( root == null )
			root = newNode;
		
		else if( newNode.value < root.value )
			return add( root.left, newNode );
		
		else if( newNode.value > root.value )
			return add( root.right, newNode );
		
		return root;
	}
	
	protected Node binarySearch( int key ) { return binarySearch( this.root, key ); }

	private Node binarySearch( Node root, int key )
	{
		if( root.value == key )
			return root;
		
		if( key < root.value )
			return binarySearch( root.left, key );
		
		if( key > root.value )
			return binarySearch( root.right, key );
		
		return null;
	}
	
	protected Node left() { return root.left; }

	protected Node remove( int key ) { return remove( this.root, key ); }
	
	private Node remove( Node root, int key )
	{
		root = binarySearch( root, key );
		
		if( root != null )
		{
			if( root.left == null )
				root = root.right;
			
			else
			{
				Node middle = root.left;
				while( middle.right != null )
					middle = middle.right;

				Node minimum = middle.left;
				while( minimum.left != null )
					minimum = minimum.left;
				
				minimum.left = root.left;
				root = middle;
			}
		}
		return root;
	}
	
	protected Node right() { return root.right; }
	
	protected Node root() { return root; }
	
	protected int value() { return root.value; }
}
