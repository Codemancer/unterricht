package ViewModel;

import java.io.*;
import java.util.List;
import javax.swing.DefaultListModel;
import Model.*;



public class QuestionVM {
	private static QuestionVM instance = null;
	public static QuestionVM getInstance() {		
		if (instance == null) instance = new QuestionVM();		
		return instance;		
	}
	
	private DefaultListModel<QuestionItem> mlstQuestions;
	public DefaultListModel<QuestionItem> getQuestions() { return mlstQuestions; }
	
	public QuestionVM() {
		mlstQuestions = new DefaultListModel<QuestionItem>();
	}	
	
	public void LoadFile(File file) {
		
		System.out.println( getFileExtension(file));
		
		switch ( getFileExtension(file) ) {
			case "csv": LoadCSV(file); break;
		}
		
	}	
	
	private void LoadCSV(File file) {
		mlstQuestions.clear();
		
		try ( BufferedReader objReader = new BufferedReader( new FileReader( file ) ) ) {
			
			String line = null;										
			while( (  line = objReader.readLine() ) != null ) {			
							
				mlstQuestions.addElement( new QuestionItem(line) );
			}
			
		} catch ( IOException ex ) { 
			
			System.out.println( ex.getMessage() );
			
		}
		
	}
	
	private String getFileExtension(File file) {
		return file.getName().substring( file.getName().indexOf(".") + 1);
	}
}
