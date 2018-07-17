# Simple-Shell Project (Cs340 - Operating System, Professor Vivek Upadhyay, Spring 2017) 
Implement under the Venus LINUX/UNIX System.

"Project 1a" /n
I) A simple shell or command line interpreter which serves as a user interface to an operating system that supports the following commands: 
    1. clr - clear the screen
    2. echo <comment> - display <comment> on the screen followed by a new line (multiple space and tabs are reduced into a single space
    3. help - displays the user manual
    4. pause - the shell pauses until "enter" is pressed
    5. exit - quits the shell

II) Other commands aside from the list is interpreted as program invocation, which is handled by the shell creating child processes.
III) The shell must support background execution of programs. Adding an ampersand (&) at the end of a commmand line means that the shell should jump to the command prompt right after the process is launched.
IV) The command prompt must contain your venus username with '>' at the end. Example: "upvu1234>"

-------------------------------------------------------------------------------------------------------------------------------------------

"Project 1b" - Enhance the shell from part 1a
I) Allows multiple commands separated by ; character to be run concurrently. The shell only displays the prompt and ask for new commands 
after all of these commands have finished executing.

xchen136> echo <helloworld> ; help     //two commands at a time

-------------------------------------------------------------------------------------------------------------------------------------------

Objectives
    1. To familiarize with the UNIX/LINUX programming environment
    2. Develop Java's multi-thread programming skills
    3. To learn how threads are handled (i.e. starting and waiting for their termination)
    4. Create child processes 
 
    
    
    
