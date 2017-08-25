# Simple-Shell Project (Cs340 - Operating System under Professor Vivek Upadhyay)
Meant to be implemented under the Venus LINUX/UNIX System.

"Project 1a"
I) A simple shell or command line interpreter which serves as a user interface to an operating system that supports the following commands: 
    1. clr - clear the screen
    2. echo <comment> - display <comment> on the display followed by a new line (multiple space and tabs are reduced into a single space
    3. help - displays the user manual
    4. pauce - the shell pauces until "enter" is pressed
    5. exit - quits the shell

II) Other commands aside from the list is interpreted as program invocation that is handled by the shell creating child processes.
III) Background execution of programs is supported. Adding an ampersand (&) at the end of a commmand line means that the shell should 
display the command prompt right after the process is called.

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

"Project 1b"
I) Allows multiple commands separated by ; character to be run concurrently. The shell only display the prompt to ask for more commands 
after all of these commands have finished executing.

xchen136>
xchen136> echo <helloworld> ; help
