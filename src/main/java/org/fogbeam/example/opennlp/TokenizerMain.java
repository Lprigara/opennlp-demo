
package org.fogbeam.example.opennlp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Definición de la clase TokenizerMain
 * Esta clase permite leer una cadena de texto en inglés para procesarlo y 
 * extraer los tokens que contenga para posteriormente mostrarlos por pantalla
 * @author leonor
 * @version 1.1.1
 */
public class TokenizerMain{

	/**
	 * Método principal que lee el fichero y obtiene la cadena de texto para extraer sus tokens.
	 * @param args
	 * @throws Exception
	 * @return No devuelve nada (void)
	 */
	public static void main( String[] args ) throws Exception{
		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
			Path path = Paths.get("prueba.txt");
			
			byte[] wikiArray = Files.readAllBytes(path);

		    String wikiString = new String(wikiArray, "ISO-8859-1");
		      
			String[] tokens = tokenizer.tokenize(wikiString);
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
