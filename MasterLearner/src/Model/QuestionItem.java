package Model;

import java.util.ArrayList;

//Test
public class QuestionItem {
	private String mstrQuestion;
	public String getQuestion() { return mstrQuestion; }
	public void setQuestion(String value) { mstrQuestion = value; }
	
	private ArrayList<String> mlstAnswers;
	public ArrayList<String> getAnswers() { return mlstAnswers; }
	
	public QuestionItem() {
		mlstAnswers = new ArrayList<String>();
	}
	public QuestionItem(String csvString) {
		this();
		
		String[] data = csvString.split(";");
	
		if ( data.length > 0 ) {
			
			this.setQuestion( data[0] );
			
			for (int i = 1; i < data.length; i++) {
				
				mlstAnswers.add( data[i] );
				
			}
		}
	}
	
	@Override public String toString() {
		String strResult = "";
		
		if (mstrQuestion.length() > 20) {		
			strResult = mstrQuestion.substring(0,20);
		} else {
			strResult = mstrQuestion;			
		}
		
		return strResult;
	}
}
