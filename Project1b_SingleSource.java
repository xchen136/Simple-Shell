

//Simple Shell by Xiaomin Chen (14145453) in Professor Vivek's Cs340 - OS Principle Class

import java.util.Scanner;
import java.io.*;

class clearScreen implements Runnable {
	clearScreen(){}
	
	public void run(){
		try{
			ProcessBuilder pb = new ProcessBuilder("clear");											//Creates a built-in process call 'clear'
			Process process = pb.start();																//Start the process and store it in the process object
			
			InputStream is = process.getInputStream();																			
			InputStreamReader isr = new InputStreamReader(is);											
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			while((line = br.readLine()) != null)
				System.out.println(line);
			
			br.close();
		}
		catch(IOException e){
				System.err.println("Error");
		}
	}
}

class echo implements Runnable {
	private String line;
	
	echo(String l){
		line = l;
	}
	
	public void run(){
		String comment;
		if(line.indexOf(' ') > -1){																//If input is not a single word
			comment = line.substring(line.indexOf(" "), line.length());							//Finds the comment part following the echo command word
			System.out.println(comment.replaceAll("\\s+", " ").trim());							//replace one/more spaces with single space
		}
		else{																					//If the input is only a single word
			comment = line;																		//The command will just be the entire input
			System.out.println("");
		}
	}
	
}

class exit implements Runnable{
	exit(){}
	
	public void run(){
		System.exit(0);
	}
}

class help implements Runnable{
	help(){}
	
	public void run(){
		try{
			File file = new File("readme.txt");															//Retrieve the user manual text file
			Scanner read = new Scanner(file);															//Opens up the text file			
			while(read.hasNextLine()){																	//While there is another line of text left in the file
				System.out.println(read.nextLine());													//Read and print out the line of text
			}
			read.close();																				//Close the scanner after use
		}
		catch(FileNotFoundException e){
			System.err.println("Error");
		}
	}
}

class pause implements Runnable{
	pause(){}
	
	public void run(){
		System.out.print("Press the ENTER key to continue...");										//Output message to direct the user
		Scanner enter = new Scanner(System.in);														
		enter.nextLine();																			//Mimics the blinking cursor when waiting for the user to press the ENTER key to continue
	}

}

class createChild implements Runnable {								//Creates a process and kills it for commands that don't exist in the shell
	private String c = "";
	
	createChild(String l){
		c = l;
	}
	
	public void run(){
		try{
			System.out.println(c + ": command not found...");											//Outputs error message 
			ProcessBuilder pb = new ProcessBuilder("clear");											//Uses the built-in 'clear' command to create a process
			Process process = pb.start();																//Starts the process
			process.destroy();																			//Destroys the process
		}catch(IOException e){
			System.err.println("Error");
		}
	}
} 

public class Project1b_SingleSource {
	public static void main(String args[]) throws IOException {
		if(args.length != 0){																		//Checks the number of arguments
			System.err.println("Usage: java OSProcess <command>");
			System.exit(0);
		}

		String[] commands;																			//store different commands
		String token;																				//each command
		String line = "";																			//a string of commands
		Thread[] exec;
		
		while(true){																				//loop until "exit"
			Scanner input = new Scanner(prompt());													//asks for input command
			if(input.hasNext()){                                                                    //if there is an input
				line = input.nextLine();															//take the string command(s)
				commands = line.split("\\s*;\\s*");                                                 //split it into individual commands and store into string array
				exec = new Thread[commands.length];
				for(int i=0; i<commands.length; i++){												//traverse through the list of commands
					token = commands[i];                                                            //take each string of command
					exec[i] = checkCommand(token); 
				}
				for(int t=0; t<commands.length; t++){												//start all threads
					exec[t].start();
				}
				for(int t=0; t<commands.length; t++){												//wait for threads to finish
					try{
						exec[t].join();
					}catch(InterruptedException e){
						System.err.println("Error");
					}
				}
			}
		}//asks for new command once the requested commands have all executed
		
	}
	
	public static String prompt(){																	//Displays the prompt for user to enter a command
		System.out.print("chxi5453> ");
		Scanner input = new Scanner(System.in);														//Declares Scanner for input reading
		return input.nextLine();
	}
	
	public static Thread checkCommand(String line) {
		String command;
		
		if(line.indexOf(' ') > -1){																//If input is not a single word
			command = line.substring(0, line.indexOf(" "));										//First word of the input is stored as the command
		}
		else{																					//If the input is only a single word
			command = line;																		//The command will just be the entire input																	//The last word will also just be the entire input
		}
		
		//Start identifying the command for the right constructor
		if(command.equalsIgnoreCase("cls")){													//Check if command is 'cls'
			Thread thread1 = new Thread(new clearScreen());								 	 	//Execute the command
			return thread1;
		}
		else if(command.equalsIgnoreCase("echo")){												//Check if command is 'echo'
			Thread thread2 = new Thread(new echo(line));
			return thread2;
		}
		else if(command.equalsIgnoreCase("help")){												//Check if command is 'help'
			Thread thread3 = new Thread(new help());
			return thread3;
		}
		else if(command.equalsIgnoreCase("pause")){												//Check if command is 'pause'
			Thread thread4 = new Thread(new pause());
			return thread4;
		}	
		else if(command.equalsIgnoreCase("exit")){												//Check if command is "exit"
			Thread thread5 = new Thread(new exit());
			return thread5;
		}
		else{																					//If it's none of the commands
			Thread threadx = new Thread(new createChild(command));						
			return threadx;                                                                 	//Calls the createChild function
		}
	}
}
