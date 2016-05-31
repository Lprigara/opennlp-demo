
package org.fogbeam.example.opennlp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
	public static void main( String[] args ) throws Exception
	{
		
		public String[] readFile(String file){ 
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
			    StringBuilder sb = new StringBuilder();
		   	    String line = br.readLine();

		   	     String[] wordList;
		    
		    	     while (line != null) {
				wordList += line.split(" ");
	            		line = br.readLine();
		    	     }
			}catch( RuntimeException e){
				System.out.println("Al leer el fichero se ha producido la siguiente excepción" + e.getMessage());
			} 

			br.close();
			return wordList;
		}
		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
			String[] tokens = tokenizer.tokenize(readFile("prueba.txt"));
			
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
