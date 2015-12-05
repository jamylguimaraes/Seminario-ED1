/**
 * Esta classe representa cada noh de uma arvore binaria
 * de busca. Despreza a altura, pois, pra este uso
 * especifico, esta eh uma informacao insignificante.
 * 
 * Todas as propriedades e metodos sao restritos ao
 * pacote, pois, esta classe sozinha nao tem nenhuma
 * utilidade.
 */
package local.tads;

public class BinaryNode
{
	public int value;
	public BinaryNode leftChild;
	public BinaryNode rightChild;
	
	BinaryNode()
	{
		leftChild = null;
		rightChild = null;
	}
	
	BinaryNode( int value )
	{
		leftChild = null;
		rightChild = null;
		this.value = value;
	}
}
