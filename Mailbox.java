/* Dylan Nicastro
ID: 114229557
Recitation: R30 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.io.Serializable;

/**
* The <code>Mailbox</code> class contains the Mailbox system that holds and manipulates all the Folders, as well as the menu loops and <code>main()</code> method.
*
* @author Dylan Nicastro
*    e-mail: dylan.nicastro@stonybrook.edu
*    Stony Brook ID: 114229557
**/
public class Mailbox implements Serializable {
  private Folder inbox;
  private Folder trash;
  private ArrayList<Folder> folders;
  public static Mailbox mailbox;
  private Folder currentFolder;

  /**
  * Default constructor for the <code>Mailbox</code> class.
  **/
  public Mailbox() {
    this.currentFolder = null;
    this.folders = new ArrayList<Folder>();
    this.inbox = new Folder("Inbox");
    this.trash = new Folder("Trash");
    this.folders.add(inbox);
    this.folders.add(trash);
  }

  /**
  * Adds a Folder object to the ArrayList of folders in the mailbox.
  *
  * @param folder
  *    The Folder object to add to the list of folders.
  *
  **/
  public void addFolder(Folder folder) {
    for (int i = 0; i < this.folders.size(); i++) {
      if (this.folders.get(i).getName().equals(folder.getName())) {
        System.out.println("A folder with that name already exists.");
        return;
      }
    }
    this.folders.add(folder);
  }
  
  /**
  * Removes a Folder object from the ArrayList of folders based on its name.
  *
  * @param name
  *    The String that represents the name of the folder to be removed.
  *
  **/
  public void deleteFolder(String name) {
    for (int i = 0; i < this.folders.size(); i++) {
      if (this.folders.get(i).getName().equals(name)) {
        this.folders.remove(i);
        System.out.println("Successfully removed folder.\n");
      }
      else {
        System.out.println("Folder not found.\n");
      }
    }
  }

  /**
  * Takes input to compose an Email object, then passes it to the <code>addEmail()</code> method.
  **/
  public void composeEmail() {
    Scanner ans = new Scanner(System.in);
    System.out.print("\nEnter recipient (To): ");
    String to = ans.nextLine();
    System.out.print("Enter carbon copy recipients (CC): ");
    String cc = ans.nextLine();
    System.out.print("Enter blind carbon copy recipients (BCC): ");
    String bcc = ans.nextLine();
    System.out.print("Enter subject line: ");
    String subject = ans.nextLine();
    System.out.print("Enter body: ");
    String body = ans.nextLine();
    Email email = new Email(to, cc, bcc, subject, body);
    inbox.addEmail(email);
    System.out.println("\nEmail successfully added to Inbox.");
  }

  /**
  * Removes an Email object from the current folder and adds it to the trash folder.
  *
  * @param email
  *    The Email object to be deleted.
  *
  **/
  public void deleteEmail(Email email) {
    Email trashEmail = currentFolder.removeEmail(getEmailIndex(currentFolder, email));
    trash.addEmail(trashEmail);
    System.out.println("\n\"" + trashEmail.getSubject() + "\" has successfully been moved to the trash.\n");
  }

  /**
  * Makes the trash folder empty and prints how many emails were deleted.
  **/
  public void clearTrash() {
    ArrayList<Email> emptiedTrash = new ArrayList<Email>();
    int emailsDeleted = trash.getEmails().size();
    trash.setEmails(emptiedTrash);
    System.out.println("\n" + emailsDeleted + " item(s) successfully deleted.\n");
  }

  /**
  * Moves an email to a different folder.
  *
  * @param email
  *    The Email object to be moved.
  * @param target
  *    The Folder object to move the email to.
  *
  **/
  public void moveEmail(Email email, Folder target) {
    Email movedEmail = currentFolder.removeEmail(getEmailIndex(currentFolder, email));
    target.addEmail(movedEmail);
  }

  /**
  * Returns a Folder object based on its name.
  *
  * @param name
  *    The String that represents the name of the folder being looked for.
  *
  **/
  public Folder getFolder(String name) {
    for (int i = 0; i < this.folders.size(); i++) {
      if (this.folders.get(i).getName().equals(name)) {
        return this.folders.get(i);
      }
    }
    return null;
  }

  /**
  * Returns the index of the given email within the folder.
  *
  * @param folder
  *    The Folder object to look for the email in.
  * @param email
  *    The Email object to find the index of.
  *
  **/
  public int getEmailIndex(Folder folder, Email email) {
    for (int i = 0; i < folder.getEmails().size(); i++) {
      if (folder.getEmails().get(i).equals(email)) {
        return i;
      }
    }
    return -1;
  }

  /**
  * Prints the main menu and handles input options.
  *
  * @param mailbox
  *    The static Mailbox object to be used.
  *
  **/
  public void mainMenu(Mailbox mailbox) throws Exception {
    boolean quit = false;
    while (quit == false) {
      System.out.println("\nMailbox:\n--------");
      for (int i = 0; i < this.folders.size(); i++) {
        System.out.println(this.folders.get(i).getName());
      }
      System.out.println("\nA - Add folder\nR - Remove folder\nC - Compose email\nF - Open folder\nI - Open Inbox\nT - Open Trash\nE - Empty Trash\nQ - Quit\n\n");
      Scanner input = new Scanner(System.in);
      String response;
      Folder folder;
      System.out.print("Enter a user option: ");
      String choice = input.nextLine();
      switch (choice.toLowerCase()) {
        case "a":
          System.out.print("\nEnter folder name: ");
          response = input.nextLine();
          folder = new Folder();
          folder.setName(response);
          mailbox.addFolder(folder);
          break;
        case "r":
          System.out.print("\nEnter folder name: ");
          response = input.nextLine();
          mailbox.deleteFolder(response);
          break;
        case "c":
          mailbox.composeEmail();
          break;
        case "f":
          System.out.print("\nEnter folder name: ");
          response = input.nextLine();
          if (mailbox.getFolder(response) == null) {
            System.out.println("No folder found with that name.");
          }
          else {
            currentFolder = mailbox.getFolder(response);
            mailbox.folderMenu(mailbox);
          }
          break;
        case "i":
          currentFolder = inbox;
          mailbox.folderMenu(mailbox);
          break;
        case "t":
          currentFolder = trash;
          mailbox.folderMenu(mailbox);
          break;
        case "e":
          mailbox.clearTrash();
          break;
        case "q":
          try {
            FileOutputStream file = new FileOutputStream("mailbox.obj");
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(mailbox);
            fout.close();
          }
          catch (Exception e) {
            throw e;
          }
          System.out.println("Program successfully exited and mailbox saved.");
          quit = true;
          break;
        default:
          System.out.println("\nInvalid input. Please try again.\n");
          break;
      }
    }
  }

  /**
  * Prints the folder menu and handles input options.
  *
  * @param mailbox
  *    The static Mailbox object to be used.
  *
  **/
  public void folderMenu(Mailbox mailbox) throws Exception {
    boolean quit = false;
    while (quit == false) {
      System.out.println("\n" + currentFolder.getName());
      if (currentFolder.getEmails().size() != 0) {
        Email currentEmail;
        String time;
        String amOrPm;
        int hour;
        System.out.println("\nIndex |        Time       | Subject");
        System.out.println("-----------------------------------");
        for (int i = 0; i < currentFolder.getEmails().size(); i++) {
          currentEmail = currentFolder.getEmails().get(i);
          if (currentEmail.getTimestamp().get(Calendar.AM_PM) == 1) {
            amOrPm = "PM";
          }
          else {
            amOrPm = "AM";
          }
          if (currentEmail.getTimestamp().get(Calendar.HOUR) == 0) {
            hour = 12;
          }
          else {
            hour = currentEmail.getTimestamp().get(Calendar.HOUR)%12;
          }
          time = String.format("%02d:%02d%s %s/%s/%s", hour, currentEmail.getTimestamp().get(Calendar.MINUTE), amOrPm, currentEmail.getTimestamp().get(Calendar.MONTH)+1, currentEmail.getTimestamp().get(Calendar.DAY_OF_MONTH), currentEmail.getTimestamp().get(Calendar.YEAR));
          System.out.printf("%4d  | %17s | %s\n", i+1, time, currentEmail.getSubject());
        }
      }
      else {
        System.out.println("\n" + currentFolder.getName() + " is empty.");
      }
      System.out.println("\nM – Move email\nD – Delete email\nV – View email contents\nSA – Sort by subject line in ascending order\nSD – Sort by subject line in descending order\nDA – Sort by date in ascending order\nDD – Sort by date in descending order\nR – Return to mailbox\n");
      Scanner input = new Scanner(System.in);
      int index;
      String inputFolder;
      System.out.print("Enter a user option: ");
      String choice = input.nextLine();
      switch (choice.toLowerCase()) {
        case "m":
          System.out.print("\nEnter the index of the email to move: ");
          index = input.nextInt();
          System.out.println("\nFolders:");
          for (int i = 0; i < this.folders.size(); i++) {
            System.out.println(this.folders.get(i).getName());
          }
          System.out.print("\nSelect a folder to move \"" + currentFolder.getEmails().get(index-1).getSubject() + "\" to: ");
          input.nextLine();
          inputFolder = input.nextLine();
          try {
            mailbox.moveEmail(currentFolder.getEmails().get(index-1), getFolder(inputFolder));
          }
          catch (Exception e) {
            System.out.println("Invalid input.");
          }
          break;
        case "d":
          System.out.print("\nEnter email index: ");
          index = input.nextInt();
          try {
            deleteEmail(currentFolder.getEmails().get(index-1));
          }
          catch (Exception e) {
            System.out.println("Invalid input.");
          }
          break;
        case "v":
          System.out.print("\nEnter email index: ");
          try {
            index = input.nextInt();
            currentFolder.getEmails().get(index-1).printEmail();
          }
          catch (Exception e) {
            System.out.println("\nInvalid input.\n");
            throw e;
          }
          break;
        case "sa":
          currentFolder.sortBySubjectAscending();
          System.out.println("Successfully sorted by ascending subject.");
          break;
        case "sd":
          currentFolder.sortBySubjectDescending();
          System.out.println("Successfully sorted by descending subject.");
          break;
        case "da":
          currentFolder.sortByDateAscending();
          System.out.println("Successfully sorted by ascending date.");
          break;
        case "dd":
          currentFolder.sortByDateDescending();
          System.out.println("Successfully sorted by descending date.");
          break;
        case "r": 
          quit = true;
          break;
      }
    }
  }

  /**
  * Main method for the <code>Mailbox</code> class. Initializes the program by searching for a serialized Mailbox object in the directory (otherwise creates a new object), then runs the main menu.
  **/
  public static void main(String[] args) throws Exception {
    try {
      FileInputStream file = new FileInputStream("mailbox.obj");
      ObjectInputStream object = new ObjectInputStream(file);
      Mailbox mailbox = (Mailbox)object.readObject();
      System.out.println("Previous save found and loaded.");
      file.close();
      mailbox.mainMenu(mailbox);
    }
    catch (Exception e) {
      System.out.println("Previous save not found, starting with an empty mailbox.");
      Mailbox mailbox = new Mailbox();
      mailbox.mainMenu(mailbox);
    }
  }
}
