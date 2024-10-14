/* Dylan Nicastro
ID: 114229557
Recitation: R30 */

import java.util.ArrayList;
import java.io.Serializable;

/**
* The <code>Folder</code> class contains Email objects to be stored and manipulated within an ArrayList.
*
* @author Dylan Nicastro
*    e-mail: dylan.nicastro@stonybrook.edu
*    Stony Brook ID: 114229557
**/
public class Folder implements Serializable {
  private ArrayList<Email> emails;
  private String name;
  private String currentSortingMethod;

  /**
  * Default constructor for the <code>Folder</code> class.
  **/
  public Folder() {
    this.emails = new ArrayList<Email>();
    this.name = null;
    this.currentSortingMethod = "date-desc";
  }

  /**
  * Overloaded constructor for the <code>Folder</code> class.
  *
  * @param name
  *    The String to be set as the name variable.
  *
  **/
  public Folder(String name) {
    this.emails = new ArrayList<Email>();
    this.name = name;
    this.currentSortingMethod = "date-desc";
  }

  /**
  * Accessor method to return the emails ArrayList.
  **/
	public ArrayList<Email> getEmails() {
		return this.emails;
	}
  /**
  * Accessor method to return the name variable.
  **/
	public String getName() {
		return this.name;
	}
  /**
  * Accessor method to return the currentSortingMethod variable.
  **/
	public String getCurrentSortingMethod() {
		return this.currentSortingMethod;
	}

  /**
  * Mutator method to change the name variable.
  *
  * @param name
  *    The String to be set as the new name.
  *
  **/
	public void setName(String name) {
		this.name = name;
	}
  /**
  * Mutator method to change the emails ArrayList.
  *
  * @param emails
  *    The ArrayList to be set as the new emails ArrayList.
  *
  **/
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
  /**
  * Mutator method to change the currentSortingMethod variable.
  *
  * @param currentSortingMethod
  *    The String to be set as the new currentSortingMethod.
  *
  **/
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}

  /**
  * Adds an Email object to the folder and sorts it.
  *
  * @param email
  *    The Email object to be added.
  *
  **/
  public void addEmail(Email email) {
    if (emails.size() == 0) {
      emails.add(email);
    }
    else {
      int index = 0;
      switch (getCurrentSortingMethod()) {
        case "sub-asc":
          this.emails.add(email);
          sortBySubjectAscending();
          break;
        case "sub-desc":
          this.emails.add(email);
          sortBySubjectDescending();
          break;
        case "date-asc":
          this.emails.add(email);
          sortByDateAscending();
          break;
        case "date-desc":
          this.emails.add(email);
          sortByDateDescending();
          break;
      }
    }
  }

  /**
  * Removes the email at the selected index from the ArrayList of emails and returns it.
  *
  * @param index
  *    The index of the email to remove from the ArrayList.
  *
  **/
  public Email removeEmail(int index) {
    Email removedEmail = emails.get(index);
    emails.remove(index);
    return removedEmail;
  }

  /**
  * Sorts the ArrayList of emails by descending subject.
  **/
  public void sortBySubjectDescending() {
    if ("sub-desc".equals(getCurrentSortingMethod())) {
      return;
    }
    ArrayList<Email> newArrayList = new ArrayList<Email>();
    for (int i = 0; i < this.emails.size(); i++) {
      if (newArrayList.size() == 0) {
        newArrayList.add(emails.get(i));
      }
      else {
        for (int k = 0; k < newArrayList.size(); k++) {
          if (k+1 == newArrayList.size() && this.emails.get(i).getSubject().compareTo(newArrayList.get(k).getSubject()) <= 0) {
            newArrayList.add(this.emails.get(i));
            break;
          }
          else if (this.emails.get(i).getSubject().compareTo(newArrayList.get(k).getSubject()) <= 0 && this.emails.get(i).getSubject().compareTo(newArrayList.get(k+1).getSubject()) >= 0) {
            newArrayList.add(k+1, this.emails.get(i));
            break;
          }
          else {
            newArrayList.add(k, this.emails.get(i));
            break;
          }
        }
      }
    }
    setEmails(newArrayList);
    setCurrentSortingMethod("sub-desc");
  }
  
  /**
  * Sorts the ArrayList of emails by ascending subject.
  **/
  public void sortBySubjectAscending() {
    if ("sub-asc".equals(getCurrentSortingMethod())) {
      return;
    }
    sortBySubjectDescending();
    ArrayList<Email> newArrayList = new ArrayList<Email>();
    for (int i = this.emails.size(); i > 0; i--) {
      newArrayList.add(this.emails.get(i-1));
    }
    setEmails(newArrayList);
    setCurrentSortingMethod("sub-asc");
  }

  /**
  * Sorts the ArrayList of emails by descending date.
  **/
  public void sortByDateDescending() {
    if ("date-desc".equals(getCurrentSortingMethod())) {
      return;
    }
    ArrayList<Email> newArrayList = new ArrayList<Email>();
    for (int i = 0; i < this.emails.size(); i++) {
      if (newArrayList.size() == 0) {
        newArrayList.add(emails.get(i));
      }
      else {
        for (int k = 0; k < newArrayList.size(); k++) {
          if (k+1 == newArrayList.size() && this.emails.get(i).getTimestamp().compareTo(newArrayList.get(k).getTimestamp()) <= 0) {
            newArrayList.add(this.emails.get(i));
            break;
          }
          else if (this.emails.get(i).getTimestamp().compareTo(newArrayList.get(k).getTimestamp()) <= 0 && this.emails.get(i).getTimestamp().compareTo(newArrayList.get(k+1).getTimestamp()) >= 0) {
            newArrayList.add(k+1, this.emails.get(i));
            break;
          }
          else {
            newArrayList.add(k, this.emails.get(i));
            break;
          }
        }
      }
    }
    setEmails(newArrayList);
    setCurrentSortingMethod("date-desc");
  }

  /**
  * Sorts the ArrayList of emails by ascending date.
  **/
  public void sortByDateAscending() {
    if ("date-asc".equals(getCurrentSortingMethod())) {
      return;
    }
    sortByDateDescending();
    ArrayList<Email> newArrayList = new ArrayList<Email>();
    for (int i = this.emails.size(); i > 0; i--) {
      newArrayList.add(this.emails.get(i-1));
    }
    setEmails(newArrayList);
    setCurrentSortingMethod("date-asc");
  }


}
