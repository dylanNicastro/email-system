# Email System
A program to manage an email system, with the ability to create and delete emails and folders, move emails to different folders, sort by various methods, etc.

> [!NOTE]
> There is no functionality implemented to send or receive emails, as this is just the client-side display system. 

## How to Use:
Download the three Java files and compile them with `javac Mailbox.java`, then run with `java Mailbox.java`. 

You'll be greeted with this text:

```
Previous save not found, starting with an empty mailbox.

Mailbox:
--------
Inbox
Trash

A – Add folder
R – Remove folder
C – Compose email
F – Open folder
I – Open Inbox
T – Open Trash
Q – Quit
```

> [!WARNING]
> Keep in mind that an incorrect input will throw an exception and end the program.

From here, the program is self-explanatory. 
- One thing to keep in mind that when the program exits successfully (With the Q command), `mailbox.obj` will be created in the same directory and data will be saved to it. The next time the program is run, the mailbox data will be loaded from this file.

> [!NOTE]
> If you want to reset your mailbox, delete the `mailbox.obj` file and run the program again. This will start a fresh `mailbox.obj` file with the default data.