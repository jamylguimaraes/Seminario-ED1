/**
 * Esta classe representa uma estrutura do tipo Arvore Splay,
 * uma estrutura de dados apropriada para buscar as informacoes
 * que sao acessadas com maior frequencia.
 * 
 * Na raiz da arvore sempre estarah o ultimo valor acessado,
 * e, quanto mais proximo do nivel das folhas estiver um noh
 * menos acessado ele terah sido.
 * 
 * Esta estrutura nao deixar de ser uma variacao de uma arvore
 * binaria de busca, possuindo as caracteristicas de uma arvore
 * AVL (arvore binaria balanceada), com o diferencial de ser
 * menos complexa, pois, a informacao da altura eh irrelevante.
 */
package local.tads;
import local.classes.*;

public class SplayTree
{
	// Variavel da classe, que serve de parametro para comparacoes
	// ou como auxiliar para troca de informacoes, em casos raros.
	private static BinaryNode nullNode = new BinaryNode();
	private BinaryNode root;

	/**
	 * Construtor principal da classe.
	 */
	public SplayTree() {}
	
	/**
	 * Construtor adicional, para jah inicializar com um valor informado.
	 * @param newNode eh o primeiro noh da arvore (a raiz ).
	 */
	public SplayTree( BinaryNode newNode ) { root = newNode; }
	
	/**
	 * Verifica se um valor informado existe na arvore.
	 * @param key eh o vaor a ser encontrado.
	 * @return um ponteiro para o "endereco" do valor
	 * encontrado na arvore, ou nulo caso ela nao contenha
	 * este valor.
	 */
	public BinaryNode find( int key )
	{
		root = splay( root, key );
		
		if( root == null || key != root.value )
			return null;
		
		return root;
	}
	
	/**
	 * Insere um novo valor na arvore.
	 * @param newValue eh o numero que se deseja adicionar.
	 * @throws Exception se o numero informado jah existir.
	 */
	public void insert( int newValue ) throws Exception
	{
		BinaryNode r = root;
		BinaryNode newNode = new BinaryNode(); // Esta variavel existe para que a classe fora do pacote que use
		newNode.value = newValue;
														 // esta classe nao precise acessar a classe BinaryNode (encapsulamento).
		if( r == null )
		{  
			newNode.leftChild = newNode.rightChild = null;
			root = newNode;
			return;
		}

		r = splay( r, newNode.value );	// Se root eh nao nulo, inicia-se a verificacao
												// de existencia do valor informado.
		if( newNode.value < r.value )
		{
			newNode.leftChild = r.leftChild;	// Se o valor nao foi encontrado, a adicao eh permitida.
			newNode.rightChild = r;			// TODO newNode menor que root nao implica em newNode menor que root.leftChild.
			r.leftChild = null;			// Corrigir com recursao de rotacoes.
			root = newNode;
			return;
		}
		else if( newNode.value > r.value ) 
		{
			newNode.rightChild = r.rightChild;
			newNode.leftChild = r;				// TODO a mesma regra que vale para o lado esquerdo deve ser aplicada no direito.
			root.rightChild = null;
			root = newNode;
			return;
		}
		else { 
			root = r;
			return;
		}
	}
	
	/**
	 * Remove um noh da arvore, se o valor armazenado neste for o mesmo
	 * que esta sendo informado como parametro.
	 * @param key eh o numero que devera ser removido da arvore.
	 * @throws Exception se o numero informado nao for encontrado.
	 */
	public void remove( int key ) throws Exception
		{
			BinaryNode newTree = root, x;
		
			if(newTree == null) return;
		// Se key for encontrada, ela serah a raiz
			newTree = splay( newTree, key );
		
			if( newTree.value == key ){
				// Se o filho a esquerda for nulo, basta substituir root pelo
				// seu filho a direita.
				if( root.leftChild == null ){
					x = newTree.rightChild;
				}else
				{
			// Caso o filho a esquerda nao seja nulo, root serah substituido
			// por este, em seguida o splay vai rebalancear a arvore, fazendo
			// as rotacoes necessarias e, como o menor noh da arvore da direita
			// eh maior que o maior noh da arvore da esquerda, a raiz da arvore
			// esquerda serah adicionada a direita da nova arvore.
					x = splay(newTree.leftChild, key);
					x.rightChild = newTree.rightChild;
				}
				root = x; // As operacoes de troca anteriores sao efetivamente salvas aqui.
				return;
			}
			root = newTree;
		}
	/**
	 * Faz uma busca binaria na arvore, ao mesmo tempo em que a atualiza,
	 * jogando o valor encontrado ou o valor mais proximo possivel, na raiz.
	 * 
	 * Este eh o metodo que mantem a principal propriedade deste tipo de
	 * arvore: a de manter os valores mais acessados, ou mais recentes,
	 * a uma distancia menor da raiz, para que no proximo acesso, o custo de
	 * processamento seja menor.
	 * @param root eh o endereco do noh apartir do qua a busca serah iniciada.
	 * @param key eh o numero que se deseja encontrar.
	 * @return o "endereco" na arvore onde o numero procurado se encontra,
	 * caso este seja encontrado, ou o numero mais proximo (maior ou menor)
	 * dele.
	 */
	private BinaryNode splay( BinaryNode root, int key )
	{
		if (root == null) return null;
		
		BinaryNode header = new BinaryNode(), leftTreeMax, rightTreeMin,tmp;
		
		header.leftChild = header.rightChild = null;
		leftTreeMax = rightTreeMin = header;
		
		for( ; ; )
		{
			if ( key < root.value )
			{
				if (root.leftChild == null) break;
				if( key < root.leftChild.value )
				{
					tmp = root.leftChild;  // Este bloco faz as rotacoes com o filho da esquerda.
					root.leftChild = tmp.rightChild;
					tmp.rightChild = root;
					root = tmp;
					if (root.leftChild == null) break;
				}
				
				rightTreeMin.leftChild = root;
				rightTreeMin = root;
				root = root.leftChild;
			} 
			else if( key > root.value )
			{
				if (root.rightChild == null) break;
				if ( key > root.rightChild.value )
				{
					//root = Rotations.rotateWithRightChild(root);
					tmp = root.rightChild;        // Este bloco faz as rotacoes com o filho da direita.
				    root.rightChild = tmp.leftChild;
				    tmp.leftChild = root;
				    root = tmp;
				    if (root.rightChild == null) break;
				}
			
				leftTreeMax.rightChild = root;
				leftTreeMax = root;
				root = root.rightChild;
			}
			else
			{
				break;
			}
			}
			// Unir as arvores
			leftTreeMax.rightChild = root.leftChild;
			rightTreeMin.leftChild = root.rightChild;
			root.leftChild = header.rightChild;
			root.rightChild = header.leftChild;
			
			return root; // nova raiz
		}
		
		public void Order( BinaryNode root)
		{
			if (root != null)
			{
				System.out.print(root.value+" ");
				if(root.leftChild != null){
					Order(root.leftChild);
				}
				if(root.rightChild != null){
					Order(root.rightChild);
				}
			}
		}
		
		public void display(){
		
			System.out.print("== Splay Tree ==" );
			System.out.println();
			BinaryNode tmp = new BinaryNode();
			tmp = root;
			System.out.print("Elementos da Arvore: ");
			Order(tmp);
			System.out.println();
			BTreePrinter.printNode(tmp);
			} 
}
