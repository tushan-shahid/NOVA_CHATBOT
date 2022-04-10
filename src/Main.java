import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Main extends Dialogue {
	
	
	
	
static UI ui = new UI(); 
static NovaBot n1 = new NovaBot(); 
static  Userfeelings p1 = new Userfeelings();

	public static void main(String[] args) {

		
		//setting up text to speech api to greet
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

		Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		
		Voice []voicelist = VoiceManager.getInstance().getVoices();
		for(int i =0;i<voicelist.length;i++) {
			System.out.println("# Voice:" + voicelist[i].getName());
		}
		if(voice!=null) {
			voice.allocate();
			boolean status = voice.speak("Hello I am NOVA! Your emotional support chat bot. How are you feeling today?");
			System.out.println("Voice Status:"+status);
			voice.deallocate();
		}else {
			System.out.println("Error");
		}
		
	//program start
	ui.setTextArea(n1.getGreeting());
	
		
	}
}
