package com.revature.hangman;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		//welcome them to game
		System.out.println("Welcome to HangMan");
		Scanner myScanner = new Scanner(System.in);
		//every wrong guess loses a bodyPart
		int bodyParts = 6;
		
		//guessing part of game
		System.out.println("Enter word to be guessed: ");
		String mysteryWord = myScanner.nextLine();
		List<String>  correctlyGuessedLetters= new ArrayList<String>();
		String originalWord = mysteryWord;
		
		while(bodyParts > 0) {
			System.out.println("Guess a letter in the word: ");			
			String guessedLetter = myScanner.nextLine();
			
			if (mysteryWord.contains(guessedLetter)) {
				System.out.println("the word contains "+ guessedLetter);
				mysteryWord = mysteryWord.replaceAll(guessedLetter, "");
				correctlyGuessedLetters.add(guessedLetter);
			}else {
				System.out.println("the word does not contain "+ guessedLetter);
				bodyParts--;
			}
			for(Character letter:originalWord.toCharArray()) {
				if(correctlyGuessedLetters.contains(letter.toString())) {
					System.out.print(letter);
				}else {
					System.out.print("#");
				}
			}
			System.out.println();
			if(mysteryWord.length() == 0) {
				System.out.println("Congratulations you've guessed all the letters in the word");
				break;
			}
		}
		if(bodyParts == 0) {
			System.out.println("Better luck next time");
		}
		}
	}
