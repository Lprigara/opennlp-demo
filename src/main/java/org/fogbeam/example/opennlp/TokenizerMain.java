
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
import opennlp.tools.util.InvalidFormatException;

/**
 * Definicion de la clase TokenizerMain
 * Esta clase permite leer una cadena de texto en ingles para procesarlo y 
 * extraer los tokens que contenga para posteriormente mostrarlos por pantalla
 * @author leonor
 * @version 1.1.1
 */
public class TokenizerMain{

	/**
	 * @param path  ruta del fichero
	 * @return cadena de bytes en String y con la codificación ISO
	 * @throws IOException
	 */
	public static String getStringListOfFile(Path path) throws IOException{
		byte[] wikiArray = Files.readAllBytes(path);
	    return new String(wikiArray, "ISO-8859-1");
	}
	
	public static Tokenizer getTokenizer(String model) throws InvalidFormatException, IOException{
		InputStream modelIn = new FileInputStream(model);
		TokenizerModel tokenizerModel = new TokenizerModel( modelIn );
		
		return new TokenizerME(tokenizerModel);
	}
	
	/**
	 * Metodo principal que lee el fichero y obtiene la cadena de texto para extraer sus tokens.
	 * @param args
	 * @throws Exception
	 * @return No devuelve nada (void)
	 */
	public static void main( String[] args ) throws Exception{
		
		try
		{
			Tokenizer tokenizer = getTokenizer("models/en-token.model");
			
			Path path = Paths.get("prueba.txt");
			
			String[] tokens = tokenizer.tokenize(getStringListOfFile(path));
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println( "\n-----\ndone" );
	}
}
