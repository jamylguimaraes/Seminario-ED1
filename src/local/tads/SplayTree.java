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
		BinaryNode newNode = new BinaryNode( newValue ); // Esta variavel existe para que a classe fora do pacote que use
														 // esta classe nao precise acessar a classe BinaryNode (encapsulamento).
		if( root == null )
		{  
			//newNode.leftChild = newNode.rightChild = nullNode; // Ainda preciso entender.
			
			// Se root eh nulo a unica operacao a ser feita eh a atribuicao do novo noh a raiz.
			root = newNode;
			return;
		}

		root = splay( root, newNode.value );	// Se root eh nao nulo, inicia-se a verificacao
												// de existencia do valor informado.
		if( newNode.value < root.value )
		{
			newNode.leftChild = root.leftChild;	// Se o valor nao foi encontrado, a adicao eh permitida.
			newNode.rightChild = root;			// TODO newNode menor que root nao implica em newNode menor que root.leftChild.
			root.leftChild = nullNode;			// Corrigir com recursao de rotacoes.
			root = newNode;
		}
		else if( newNode.value > root.value ) 
		{
			newNode.rightChild = root.rightChild;
			newNode.leftChild = root;				// TODO a mesma regra que vale para o lado esquerdo deve ser aplicada no direito.
			root.rightChild = nullNode;
			root = newNode;
		}
		else { throw new Exception( "Valor duplicado." ); }
		
		newNode = null; // Para que o próximo insert seja uma nova chamada
	}
	
	/**
	 * Remove um noh da arvore, se o valor armazenado neste for o mesmo
	 * que esta sendo informado como parametro.
	 * @param key eh o numero que devera ser removido da arvore.
	 * @throws Exception se o numero informado nao for encontrado.
	 */
	public void remove( int key ) throws Exception
	{
		BinaryNode newTree = null;
		
		// Se key for encontrada, ela serah a raiz
		root = splay( root, key );
		
		// Como o splay joga o endereco noh que contem o valor
		// buscado, caso ele exista na arvore, na raiz, a unica
		// verificacao a se fazer agora eh se a raiz contem esse valor.
		if( root.value != key )
			throw new Exception( "Nao encontrado." );
		
		// Caso o valor seja encontrado, neste ponto do programa ele eh
		// a raiz, logo a partir daqui comecao a sua remocao.
		
		// Se o filho a esquerda for nulo, basta substituir root pelo
		// seu filho a direita.
		if( root.leftChild == nullNode )
			newTree = root.rightChild;
		else
		{
			// Caso o filho a esquerda nao seja nulo, root serah substituido
			// por este, em seguida o splay vai rebalancear a arvore, fazendo
			// as rotacoes necessarias e, como o menor noh da arvore da direita
			// eh maior que o maior noh da arvore da esquerda, a raiz da arvore
			// esquerda serah adicionada a direita da nova arvore.
			newTree = root.leftChild;
			newTree = splay( newTree, key );
			newTree.rightChild = root.rightChild;
		}
		root = newTree; // As operacoes de troca anteriores sao efetivamente salvas aqui.
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
		BinaryNode leftTreeMax, rightTreeMin, header;
		
		header = root;
		header.leftChild = header.rightChild = nullNode;
		
		leftTreeMax = rightTreeMin = root;
		
		nullNode.value = key;
		for( ; ; )
			if ( key < root.value )
			{
				if( key < root.leftChild.value )
				{
					BinaryNode tmp = root.leftChild;  // Este bloco faz as rotacoes com o filho da esquerda.
					root.leftChild = tmp.rightChild;
					tmp.rightChild = root;
					root = tmp;
				}
				
				if( root.leftChild == nullNode )
					break;
				
				rightTreeMin.leftChild = root;
				rightTreeMin = root;
				
				root = root.leftChild;
			} 
			else if( key > root.value )
			{
				if ( key > root.rightChild.value )
				{
					//root = Rotations.rotateWithRightChild(root);
					BinaryNode tmp = root.rightChild;        // Este bloco faz as rotacoes com o filho da direita.
				    root.rightChild = tmp.leftChild;
				    tmp.leftChild = root;
				    root = tmp;
				}
				
				if( root.rightChild == nullNode )
					break;
				
				// Ligar lado esquerdo
				leftTreeMax.rightChild = root;
				leftTreeMax = root;
				root = root.rightChild;
			}
			else
				break;
			
			// Unir as arvores
			leftTreeMax.rightChild = root.leftChild;
			rightTreeMin.leftChild = root.rightChild;
			
			root.leftChild = header.rightChild;
			root.rightChild = header.leftChild;
			
			return root; // nova raiz
		}
		
		public void display(){
		
			System.out.print("== Splay Tree ==" );
			System.out.println();
			int nBlanks = 32;
			int itemsPerRow = 1;
			int column = 0;
			int j = 0;

			String dots = "...............................";
			System.out.println(dots+dots);
			// BinaryNode tmp; Cria um tmp do tipo BinaryNode, armazena root, root recebe root.root
			while(root.leftChild != null && root.rightChild != null){
				if(column == 0)
					for(int k=0; k<nBlanks; k++)
						System.out.print(" ");
						System.out.print(root.leftChild.value);
						System.out.print(root.rightChild.value);
				if(++j == 10)
					break;
				if(++column==itemsPerRow){
					nBlanks /= 2;
					itemsPerRow *= 2;
					column = 0;
					System.out.println();
				}else
					for(int k=0; k<nBlanks*2-2; k++)
						System.out.print(" ");
					} 
				System.out.println("\n"+dots+dots); 
			} 
}
