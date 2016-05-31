
package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain{

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
