/**
 * Esta classe é o programa propriamente dito.
 */
package local.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

import local.tads.*;

public class Main
{
	private static SplayTree2 tree;
	private static BufferedReader in;
	private static int loops;
	
	public static void main( String[] args )
	{
		in = new BufferedReader( new InputStreamReader( System.in ) );
		tree  = new SplayTree2();
		loops = 0;
		
		while( true )
		{
			// Com este try-catch o programa continua pedindo por um numero
			// ao usuario ateh q um valor valido seja fornecido, ou o programa
			// seja interrompido.
			try
			{
				System.out.print( "Quantidade de elementos: " );
				loops = Integer.parseInt( in.readLine() );
				break;
			}
			catch( IOException | InputMismatchException e ) { continue; }
		}
		
		while( loops > 0 )
		{
			// Com este try-catch o programa continua pedindo por mais elementos
			// ateh q o numero de nohs fornecido anteriormente seja atingido, ou
			// ateh q o programa seja interrompido.
			try
			{
				System.out.print( "Elementos (separados por espaco ou enter): " );
				
				StringTokenizer linha = new StringTokenizer( in.readLine() );
				
				while( linha.hasMoreElements() )
				{
<<<<<<< Upstream, based on branch 'master' of https://github.com/jamylguimaraes/Seminario-ED1.git
					tree.insert( Integer.parseInt( (String) linha.nextElement()) );
=======
					tree.add( Integer.parseInt( linha.nextToken() ) );
>>>>>>> dfaaf6e Essa seria a estrutura correta.
					loops--;
				}
			}
			catch( InputMismatchException | IOException e ) { continue; }
		}
		tree.display(loops);
	}
}
