
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
		
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );
		BufferedReader br = new BufferedReader(new FileReader("prueba.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    List<String> wordList = new ArrayList<String>();
		    
		    while (line != null) {
		    	StringTokenizer stringTokenizer = new StringTokenizer(line);
	                while(stringTokenizer.hasMoreTokens()){
                		String word= stringTokenizer.nextToken();
                		if(!wordList.contains(word)){
                			wordList.add(word);
                		}	
		            	line = br.readLine();
		        }
		    }
		    
		    for (String word : wordList) {
			System.out.println("Word: " + word);
		    }
		}catch( RuntimeException e){
			System.out.println("Al leer el fichero se ha producido la siguiente excepción" + e.getMessage());
		} finally {
		    br.close();
		}
		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			String[] tokens = tokenizer.tokenize
					(  "A ranger journeying with Oglethorpe, founder of the Georgia Colony, " 
							+ " mentions \"three Mounts raised by the Indians over three of their Great Kings" 
							+ " who were killed in the Wars.\"" );
			
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
