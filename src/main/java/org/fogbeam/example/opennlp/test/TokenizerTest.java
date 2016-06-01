package org.fogbeam.example.opennlp.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.fogbeam.example.opennlp.TokenizerMain;
import org.junit.Test;

import junit.framework.TestCase;
import opennlp.tools.tokenize.Tokenizer;

public class TokenizerTest extends TestCase {

	 @Test
	 public void testTokensEquals() throws IOException {
        Path path = Paths.get("ficheros/prueba.txt");
         
		try {
			Tokenizer tokenizer = TokenizerMain.getTokenizer("models/en-token.model");
			
			String[] tokens = tokenizer.tokenize
					(  TokenizerMain.getStringListOfFile(path) );
			
            String[] listaTokensVerdaderos = {"If", "I", "were", "a", "boy", "Even", "just", "for", "a", "day"};
        
            for(int i = 0; i < tokens.length; i++){
            	assertEquals(listaTokensVerdaderos[i], tokens[i]);
            }
		}catch( IOException e ){
			e.printStackTrace();
			throw new IOException();
		}
    }
	 
	 @Test
	 public void testTokensNotEquals() throws IOException {
        Path path = Paths.get("ficheros/prueba.txt");
         
		try {
			Tokenizer tokenizer = TokenizerMain.getTokenizer("models/en-token.model");
			
			String[] tokens = tokenizer.tokenize
					(  TokenizerMain.getStringListOfFile(path) );
			
            String[] listaTokensVerdaderos = {"Falla", "I", "were", "a", "boy", "Even", "just", "for", "a", "day"};
            assertFalse(Arrays.equals(listaTokensVerdaderos, tokens));
            
		}catch( IOException e ){
			e.printStackTrace();
			throw new IOException();
		}
    }
	 
}
