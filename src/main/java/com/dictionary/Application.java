package com.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dictionary.service.DictionaryService;

public class Application {

	
	private static final Pattern pattern = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);
	private DictionaryService dictionaryService = new DictionaryService();
	
	
	//A user can enter input for the dictionary and the command will be executed
    public static void main(String[] args) {
    	Application application = new Application();
    	application.processInput();
    }
    
    //takes the user's input and executes the command
    private void processInput() {
    	Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String[] userInput = scanner.nextLine().split(" ");
			dictionaryAction(userInput);
		}
		scanner.close();
    }
    
    //Analyzes the input and executes the correct command or presents a validation message 
    private void dictionaryAction(String[] userInput) {
    	boolean validInput = false;
    	for(Commands value : Commands.values()) {
    		if(value.getCommand().equals(userInput[0])) {
    			validInput = true;
    			break;
    		}
    	}
    	if(validInput){
	    	Commands command = Commands.valueOf(userInput[0]);
	    	if((command.getCommandLength() != userInput.length) && !"REMOVE".equals(command.getCommand())) {
	    		System.out.println(command + " Command requires " + command.getCommandLength() + " values separated by a space.");
	    	} else {
		    	switch (command) {
				case ADD:
					dictionaryService.addMember(userInput[1], userInput[2]);
					break;
				case ALLMEMBERS:
					if(!validateBooleanValue(userInput[1])) {
						System.out.println("The second value must be true or false");
					} else {
						dictionaryService.printAllMembers(userInput[1]);
					}
					break;
				case CLEAR:
					dictionaryService.clear();
					break;
				case ITEMS:
					if(!validateBooleanValue(userInput[1])) {
						System.out.println("The second value must be true or false");
					} else {
						dictionaryService.printItems(userInput[1]);
					}
					break;
				case JSON:
					dictionaryService.getJSON();
					break;
				case KEYS:
					if(!validateBooleanValue(userInput[1])) {
						System.out.println("The second value must be true or false");
					} else {
						dictionaryService.getKeys(userInput[1]);
					}
					break;
				case KEYEXISTS:
					dictionaryService.keyExists(userInput[1]);
					break;
				case MEMBERS:
					dictionaryService.printMembers(userInput[1]);
					break;
				case REMOVE:
					if(userInput.length < 3) {
						dictionaryService.remove(userInput[1],null);
					} else {
						dictionaryService.remove(userInput[1],userInput[2]);
					}
					break;
				case REMOVEALL:
					dictionaryService.removeAll(userInput[1]);
					break;
				case VALUEEXISTS:
					dictionaryService.valueExists(userInput[1], userInput[2]);
					break;
				default:
					break;
				}
	    	}
    	} else {
    		System.out.println("Invalid Command");
    	}
    }
    
    //list of valid commands and corresponding length of the input
    private enum Commands{
    	  ADD ("ADD",3), 
    	  ALLMEMBERS ("ALLMEMBERS",2), 
    	  CLEAR ("CLEAR",1), 
    	  ITEMS ("ITEMS",2),
    	  JSON ("JSON",1),
    	  KEYS ("KEYS",2),
    	  KEYEXISTS("KEYEXISTS",2),
    	  MEMBERS("MEMBERS",2),
    	  REMOVE("REMOVE",3),
    	  REMOVEALL("REMOVEALL",2),
    	  VALUEEXISTS("VALUEEXISTS",3); 

    	  private String command;
    	  private int commandLength;
    		  
    	  Commands(String commandInput, int commandLength) {
    		  this.command = commandInput;
    	      this.commandLength = commandLength;
    	  }
    	  
    	  public String getCommand() {
    		  return this.command;
    	  }
    	  
    	  public int getCommandLength() {
    	      return this.commandLength;
    	  }
    }
    
	
	private boolean validateBooleanValue(String value) {
	      Matcher matcher = pattern.matcher(value);
	      if(matcher.matches()) {
	         return true;
	      } else {
	         return false;
	      }
	}
}

