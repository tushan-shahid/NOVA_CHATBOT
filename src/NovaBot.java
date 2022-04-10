import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Jwiki.Jwiki;

import java.util.*;

public class NovaBot extends Main   {
String response;
String greeting; 
String quote ;
String comfort;
String help;

public String getResponse(String s) {
	if(s.equals("mad")) {
		File mad = new File("src/mad_responses.txt");
		String madArray[] = createFile(mad); 
		response = chooseRandom(madArray);
		
	}else if(s.equals("sad")) {
		
		File sadRes = new File("src/sadness_responses.txt");
		String sadArray[] = createFile(sadRes); 
		response= chooseRandom(sadArray);
		
	}else if(s.equals("happy")) {
		File happy = new File("src/happiness_responses.txt");
		String happyArray[] = createFile(happy); 
		response= chooseRandom(happyArray);
		
	} else if (s.equals("fear")) {
		File fear = new File("src/fear_responses.txt");
		String fearArray[] = createFile(fear);
		response = chooseRandom(fearArray);
		//5 responses for 5 topics the bot isn't prepared for
	} else if (s.equals("depressed")) {
		response = "You should seek professional help for your depression.";
	} else if (s.equals("suicidal")) {
		response = "You should seek professional help. Please contact your local suicide hotline.";
	}  else if (s.equals("bipolar")) {
		response = "You should seek professional help for your bipolar condition.";
	} else if (s.equals("sick")) {
		response = "Unfortunately, I cannot help you if you feel ill. Please consult a doctor.";
	} else if (s.equals("disgusted")) {
		response = "Unfortunately, I cannot help you manage your digust. ";
	} else {
		System.out.println("I'm sorry, I can't understand.");
	}
	
	return "NOVA: " + response;
}

public String getFollowup(String s) {
	//followup responses to given emotion
	if(s.equals("mad")) {
		File mad = new File("src/mad_followup.txt");
		String madArray[] = createFile(mad); 
		response = chooseRandom(madArray);
		
	}else if(s.equals("sad")) {
		
		File sadRes = new File("src/sadness_followup.txt");
		String sadArray[] = createFile(sadRes); 
		response= chooseRandom(sadArray);
		
	}else if(s.equals("happy")) {
		File happy = new File("src/happy_followup.txt");
		String happyArray[] = createFile(happy); 
		response= chooseRandom(happyArray);
		
	} else if (s.equals("fear")) {
		File fear = new File("src/fear_followup.txt");
		String fearArray[] = createFile(fear);
		response = chooseRandom(fearArray);
	} else {
		System.out.println("I'm sorry, I don't understand.");
	}
	
	return "NOVA: " + response;
}

public String getGreeting() {
	greeting = "NOVA: Hi, I am NOVA your personal emotional support bot :)" + "\n" + "NOVA: How are you feeling today? (mad, sad, happy, scared, etc.)";
	return greeting;
}

public String getComfort() {
	File comfortfile = new File("src/comforting_phrases.txt");
	String comfortarray[] = createFile(comfortfile); 
	comfort= chooseRandom(comfortarray);
	return "NOVA: " + comfort ; 
}

public String getQuote(String s) {	
	if(s.equals("mad")) {
		File madQuote = new File("src/AngerQuotes.txt");
		String[] madQuoteArray = createFile(madQuote); 
		quote= chooseRandom(madQuoteArray);
		
	}else if(s.equals("sad")) {
		File sadQuote = new File("src/SadnessQuotes.txt");
		String[] sadQuoteArray = createFile(sadQuote); 
		quote= chooseRandom(sadQuoteArray);
		
	}else if(s.equals("happy")) {
		File happyQuote = new File("src/HappinessQuotes.txt");
		String[] happyQuoteArray = createFile(happyQuote); 
		quote = chooseRandom(happyQuoteArray);	
	}
	return "NOVA: " + quote; 
}

public static void getRating(){
	    //creating instance of pipeline for sentiment analysis
	    PipelineToSentiment.init();
	    PipelineToSentiment pipelineToSentiment = new PipelineToSentiment();

//		ui.setTextArea("Please describe your experience with NovaBot in one sentence:");
		Scanner sc = new Scanner(System.in);
		String rating = UI.input;
		
		//store positive, neutral and negative responses
		String[] positiveReply = { "NOVA: I'm thrilled I was able to help you so much!" , "NOVA: I'm glad I was able to make you feel a bit better!"};
		String[] neutralReply = { "NOVA: I'm at least glad I was able to make you feel somewhat better."};
		String[] negativeReply = { "NOVA: I'm sorry I wasn't able to help you today." , "NOVA: I'm sorry you were not satisfied with my help."};
	    
		String sentiment = null;
		
		Random random = new Random();
		
		sentiment = pipelineToSentiment.getSentiment(rating);
		
	    
	    //check if rating is positive or neutral or negative
	    if(sentiment.contentEquals("Positive 3") || sentiment.contentEquals("Very positive 4")) {
	    	 int idx = random.nextInt(positiveReply.length);
	    	 ui.setTextArea(positiveReply[idx]);
	    }else if(sentiment.contentEquals("Neutral 2")) {
	    	ui.setTextArea(neutralReply[0]);
	    }else if(sentiment.contentEquals("Negative 1")|| sentiment.contentEquals("Very negative 0")) {
	    	 int idx = random.nextInt(negativeReply.length);
	    	 ui.setTextArea(negativeReply[idx]);
	    }
	    
		sc.close();
}


//method to return suggestions using WIKI api
public String helpOptions() {
	//string array to hold words to define
	String[] helpOptions = {"exercise","meditation","suicide","depression","sleeping disorder","nutrition","mental health","substance abuse"};
	//randomly choose from string array
	Random r = new Random();        
  	int rand = r.nextInt(helpOptions.length);
  	//call wiki api to get definition
	 Jwiki e = new Jwiki(helpOptions[rand]); 
	 String help = e.getExtractText();

	 //return definition
	return help;
}


//chooses a random response
public String chooseRandom(String[] s) {
	int length = s.length; 
	int ran =(int) Math.floor(Math.random()*length);
	return s[ran]; 	
}



public String[] createFile(File f) {
	String[] keyWords ;
	try {
		Scanner reader = new Scanner(f);
	
		// Creates an Array List //
		List<String> lines = new ArrayList<String>();
	
		// Puts the text File into The Array List //
		while (reader.hasNextLine()) {
	  		lines.add(reader.nextLine());
		}
		// Puts the Array list into a simple Array // 
		keyWords = lines.toArray(new String[0]);
		reader.close();	
		return keyWords; 
	} catch(FileNotFoundException e) {
		System.out.println("File was not found!!!!");
		return null; 
	}
	
}
	
}// end of class 

