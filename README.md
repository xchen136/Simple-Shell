# Simple-Shell Project (Cs340 - Operating System Project 1a from Professor Vivek Upadhyay)
Meant to be implemented under the Venus LINUX/UNIX System.

I) A simple shell or command line interpreter which serves as a user interface to an operating system that supports the following commands: 
    1. clr - clear the screen
    2. echo <comment> - display <comment> on the display followed by a new line (multiple space and tabs are reduced into a single space
    3. help - displays the user manual
    4. pauce - the shell pauces until "enter" is pressed
    5. exit - quits the shell

II) Other commands aside from the list is interpreted as program invocation that is handled by the shell creating child processes.
III) Background execution of programs is supported. Adding an ampersand (&) at the end of a commmand line means that the shell should 
display the command prompt right after the process is called.


----------------------------------------------User Manual-----------------------------------------
Commands:

> cls 
Clears the screen, but user can still scroll up for past history.

> echo comment
Displays the comment part of the user input where multiple space gap is reduced to a single space.

> help
Displays this manual that guides the user through using this shell.

> pause
The shell is paused, nothing is performed until the user hits the enter key.

> exit
Quits this shell program.

> ........&
The shell supports background execution of programs.
By adding an ampersand at the end of the command line, the prompt will return immediately after the command/program has started executing.

--------------------------------------------------------------------------------------------------
