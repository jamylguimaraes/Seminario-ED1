package local.tads;

public class SplayTree2 
{
	private BinaryNode root;
	
	public SplayTree2() { root = null; }
	
	public SplayTree2( int value ) { root = new BinaryNode( value ); }
	
	public void add( int value ) 
	{
		if( root == null )
		{
			root = new BinaryNode( value );
			return;
		}
		
		root = splay( root, value );
		
		if( value < root.value )
		{
			BinaryNode newLeftChild = new BinaryNode( value );
			newLeftChild.rightChild = root;
			root = newLeftChild;
		}
		else if( value > root.value )
		{
			BinaryNode newRightChild = new BinaryNode( value );
			newRightChild.leftChild = root;
			root = newRightChild;
		}
	}
	
	public BinaryNode find( int key )
	{
		if( root != null )
			root = splay( root, key );
		
		return root;
	}
	
	public void remove( int key )
	{
		if( root == null )
			return;
		
		root = splay( root, key );
		
		if( key == root.value )
			if( root.leftChild == null )
				root = root.rightChild;
			else
				root = root.leftChild;
	}
	
	private static BinaryNode splay( BinaryNode root, int key )
	{
		BinaryNode leftRoot = root.leftChild;
		BinaryNode rightRoot = root.rightChild;
		BinaryNode tmpNode = null;
		
		int rootValue = root.value;
		root = new BinaryNode( rootValue );	// Corta as ligacoes com as arvores laterais
		
		if( key < rootValue && leftRoot != null )
			tmpNode = splay( leftRoot, key );

		else if( key > rootValue && rightRoot != null )
			tmpNode = splay( rightRoot, key );
		
		root.leftChild = leftRoot;	// Refaz as ligacoes da raiz com as subarvores
		root.rightChild = rightRoot;
		
		
		tmpNode.leftChild = root;	// Prepara a substituicao dos primeiros nos
		//tmpNode.rightChild = root;
		
		root = tmpNode;
		
		return root;
	}
}
