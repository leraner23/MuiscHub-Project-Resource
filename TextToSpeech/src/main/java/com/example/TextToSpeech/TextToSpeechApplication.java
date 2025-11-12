package com.example.TextToSpeech;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.internal.DotenvReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class TextToSpeechApplication {
	public static String Transcribe(String audioFilePath){
		Dotenv dotenv = Dotenv.load();
		String apiKey = dotenv.get("ASSEMBLYAI_API_KEY");
		System.out.println("Transcribing:"+ audioFilePath);
		try {
			AssemblyAI aai = AssemblyAI.builder()
					.apiKey(apiKey).build();
			Transcript transcript = aai.transcripts().transcribe(new File(audioFilePath));
			return transcript.getText().orElse("Transcription failed. Starting: "+ transcript.getStatus() );
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public static void main(String[] args) {
		String result = Transcribe("Wiz Khalifa - See You Again ft. Charlie Puth (Lyrics) [NDEWXnMRq3c].mp3");
		System.out.println("Speech to text");

		String[] parts = result.split(",");
		for (String part : parts) {
			System.out.println(part.trim());
		}
	}

}
