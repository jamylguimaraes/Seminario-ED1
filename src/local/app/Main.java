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
	private static SplayTree tree;
	private static BufferedReader in;
	private static int loops;
	
	public static void main( String[] args )
	{
		in = new BufferedReader( new InputStreamReader( System.in ) );
		tree  = new SplayTree();
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
					tree.insert( Integer.parseInt( (String) linha.nextElement()) );
					loops--;
				}
			}
			catch( Exception e ) { continue; }
		}
		tree.display(loops);
	}
}
