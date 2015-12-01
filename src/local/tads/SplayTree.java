package local.tads;

public class SplayTree extends Tree
{
	private void splay( Tree tree, int key )
	{
		Tree leftTree = null, rightTree = null;
		
		while( key != tree.value() || !( tree.left() == null && tree.right() == null ) )
		{
			if( key > tree.value() )
			{
				if( leftTree == null )
					leftTree = new Tree( tree.root() );
				
				leftTree.add( tree.root() );
				tree = new Tree( tree.right() );
			}
			else if( key < tree.value() )
			{
				if( rightTree == null )
					rightTree = new Tree( tree.root() );
				
				rightTree.add( tree.root() );
				tree = new Tree( tree.left() );
			}
		}

		tree.add( leftTree.root() );
		tree.add( rightTree.root() );
	}
}
