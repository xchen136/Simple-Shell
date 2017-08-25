
//Simple Shell by Xiaomin Chen in Professor Vivek's Cs340 - OS Principle Class

import java.util.Scanner;
import java.io.*;

public class Project1a {
	public static void main(String args[]) throws IOException {
		if(args.length != 0){																		//Checks the number of arguments
			System.err.println("Usage: java OSProcess <command>");
			System.exit(0);
		}
		Scanner input = new Scanner(System.in);														//Declares Scanner for input reading		
		String read;
		read = prompt();																			//Displays prompt and to get a command
		if(read.isEmpty()){																			//If input is empty
			read = " ";																				//Initialize the string variable
		}
		checkCommand(read);																			//Check and execute the command
		input.close();																				//Close the scanner after reading is done
	}
	
	public static void clearScreen() throws IOException{											//Executes the built-in 'clear' command in the shell
		ProcessBuilder pb = new ProcessBuilder("clear");											//Creates a process call 'clear'
		Process process = pb.start();																//Start the process and store it in the process object
		
		InputStream is = process.getInputStream();																			
		InputStreamReader isr = new InputStreamReader(is);											
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		while((line = br.readLine()) != null)
			System.out.println(line);
		
		br.close();
	}
	
	public static void help() throws FileNotFoundException {										//Displays the user manual on the console
		File file = new File("readme.txt");															//Retrieve the user manual text file
		Scanner read = new Scanner(file);															//Opens up the text file			
		while(read.hasNextLine()){																	//While there is another line of text left in the file
			System.out.println(read.nextLine());													//Read and print out the line of text
		}
		read.close();																				//Close the scanner after use
	}
	
	public static void pause() throws IOException{													//Waits for the user to press ENTER so it can display the prompt again
		System.out.print("Press the ENTER key to continue...");										//Output message to direct the user
		Scanner enter = new Scanner(System.in);														
		enter.nextLine();																			//Mimics the blinking cursor when waiting for the user to press the ENTER key to continue
	}
	
	public static void echo(String l){																//Display the echo comment
		String comment, last;
		last = l.substring(l.lastIndexOf(" ") +1);													//Last word is stored to check if it's an ampersand later
		if(last.equals("&")){																		//Check if last word is an ampersand
			comment = l.substring(l.indexOf(" "), l.lastIndexOf(" "));								//Finds the comment part following the echo command word
		}
		else{
			comment = l.substring(l.indexOf(" "), l.length());										//Finds the comment part following the echo command word
		}
		System.out.println(comment.replaceAll("\\s+", " ").trim());									//Reformat the comment and displays with multiple spaces simplified down to a single space
	}
	
	public static void createChild(String c) throws IOException{									//Creates a process and kills it for commands that don't exist in the shell
		ProcessBuilder pb = new ProcessBuilder("clear");											//Uses the built-in 'clear' command to create a process
		Process process = pb.start();																//Starts the process
		System.out.println(c + ": command not found...");											//Outputs error message 
		process.destroy();																			//Destroys the process
	} 
	
	public static String prompt(){																	//Displays the prompt for user to enter a command
		System.out.print("chxi5453> ");
		Scanner input = new Scanner(System.in);														//Declares Scanner for input reading
		String result = input.nextLine();
		return result;
	}
	
	public static void BackExec(){																	//Mimics the background execution message and response
		System.out.println("Program will run in the background.");									//Displays a message for current state																				//Displays the prompt immediately before the command is done
	}
	
	public static void checkCommand(String line) throws IOException {
		String command, lastWord, in;
		if(line.isEmpty() == false){																//If there is user input
			if(line.indexOf(' ') > -1){																//If input is not a single word
				command = line.substring(0, line.indexOf(" "));										//First word of the input is stored as the command
				lastWord = line.substring(line.lastIndexOf(" ") +1);								//Last word is stored to check if it's an ampersand later
			}
			else{																					//If the input is only a single word
				command = line;																		//The command will just be the entire input
				lastWord = line;																	//The last word will also just be the entire input
			}
			//Start checking the command
			if(command.equalsIgnoreCase("cls")){													//Check if command is 'cls'
				if(lastWord.equals("&")){															//Check if lastWord is an ampersand
					BackExec();																		//If so, calls to execute program in the background
					in = prompt();		
					clearScreen();
					checkCommand(in);
				}
				else{
					clearScreen();																	//Execute the command
					in = prompt();
					checkCommand(in);
				}
			}
			else if(command.equalsIgnoreCase("echo")){												//Check if command is 'echo'
				if(lastWord.equals("&")){															//Check if lastWord is an ampersand
					BackExec();																		//If so, calls to execute program in the background
					in = prompt();
					echo(line);	
					checkCommand(in);
				}
				else if(lastWord.equals("echo")){													//Check if last word is also echo, if so we know the input is just a single word command
					System.out.println(" ");														//Then just print a blank line for blank comment
					in = prompt();
					checkCommand(in);
				}
				else{
					echo(line);																		//Executes the command
					in = prompt();
					checkCommand(in);
				}	
			}
			else if(command.equalsIgnoreCase("help")){												//Check if command is 'help'
				if(lastWord.equals("&")){															//Check if lastWord is an ampersand
					BackExec();																		//If so, calls to execute program in the background
					in = prompt();
					help();																			//Executes the command
					checkCommand(in);
				}
				else{
					help();																			//Executes the command
					in = prompt();
					checkCommand(in);
				}
			}
			else if(command.equalsIgnoreCase("pause")){												//Check if command is 'pause'
				if(lastWord.equals("&")){															//Check if lastWord is an ampersand
					BackExec();																		//If so, calls to execute program in the background
					in = prompt();
					pause();																		//Executes the command
					checkCommand(in);
				}
				else{
					pause();																		//Executes the command
					in = prompt();
					checkCommand(in);
				}
			}	
			else if(command.equalsIgnoreCase("exit")){												//Check if command is "exit"
				if(lastWord.equals("&")){															//Check if lastWord is an ampersand
					BackExec();																		//If so, calls to execute program in the background
					in = prompt();
					System.exit(0);
				}
				else
					System.exit(0);
			}
			else{																					//If it's none of the commands
				createChild(command);																//Calls the createChild function
				in = prompt();																		//Prompt and ask for new command if there is no user input							
				checkCommand(in);																	//Check and executes the command
			}
		}
		else{																						//Prompt and ask for new command if there is no user input
			in = prompt();
			checkCommand(in);
		}
	}
}
