/* Dylan Nicastro
ID: 114229557
Recitation: R30 */

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
* The <code>Email</code> class contains information and methods concerning each email stored in the system.
*
* @author Dylan Nicastro
*    e-mail: dylan.nicastro@stonybrook.edu
*    Stony Brook ID: 114229557
**/
public class Email implements Serializable {
  private String to;
  private String cc;
  private String bcc;
  private String subject;
  private String body;
  private GregorianCalendar timestamp;


  /**
  * Default constructor for the <code>Email</code> class.
  **/
  public Email() {
    this.to = null;
    this.cc = null;
    this.bcc = null;
    this.subject = null;
    this.body = null;
    this.timestamp = (GregorianCalendar)GregorianCalendar.getInstance(TimeZone.getTimeZone("EST5EDT"));
  }

  /**
  * Overloaded constructor for the <code>Email</code> class.
  *
  * @param to
  *    The String to be set as the to variable.
  * @param cc
  *    The String to be set as the cc variable.
  * @param bcc
  *    The String to be set as the bcc variable.
  * @param subject
  *    The String to be set as the subject variable.
  * @param body
  *    The String to be set as the body variable.
  *
  **/
  public Email(String to, String cc, String bcc, String subject, String body) {
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.body = body;
    this.timestamp = (GregorianCalendar)GregorianCalendar.getInstance(TimeZone.getTimeZone("EST5EDT"));
  }

  /**
  * Accessor method to return the to variable.
  **/
	public String getTo() {
		return this.to;
	}
  /**
  * Accessor method to return the cc variable.
  **/
	public String getCc() {
		return this.cc;
	}
  /**
  * Accessor method to return the bcc variable.
  **/
	public String getBcc() {
		return this.bcc;
	}
  /**
  * Accessor method to return the subject variable.
  **/
	public String getSubject() {
		return this.subject;
	}
  /**
  * Accessor method to return the body variable.
  **/
	public String getBody() {
		return this.body;
	}
  /**
  * Accessor method to return the timestamp variable.
  **/
	public GregorianCalendar getTimestamp() {
		return this.timestamp;
	}

  /**
  * Mutator method to change the to variable.
  *
  * @param to
  *    The String to be set as the new to.
  *
  **/
	public void setTo(String to) {
		this.to = to;
	}

  /**
  * Mutator method to change the cc variable.
  *
  * @param cc
  *    The String to be set as the new cc.
  *
  **/
	public void setCc(String cc) {
		this.cc = cc;
	}

  /**
  * Mutator method to change the bcc variable.
  *
  * @param bcc
  *    The String to be set as the new bcc.
  *
  **/
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

  /**
  * Mutator method to change the subject variable.
  *
  * @param subject
  *    The String to be set as the new subject.
  *
  **/
	public void setSubject(String subject) {
		this.subject = subject;
	}

  /**
  * Mutator method to change the body variable.
  *
  * @param body
  *    The String to be set as the new body.
  *
  **/
	public void setBody(String body) {
		this.body = body;
	}

  /**
  * Mutator method to change the timestamp variable.
  *
  * @param timestamp
  *    The GregorianCalendar object to be set as the new timestamp.
  *
  **/
	public void setTimestamp(GregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}

  /**
  * Prints the email as nicely formatted text.
  **/
  public void printEmail() {
    System.out.printf("\nTo: %s\nCC: %s\nBCC: %s\nSubject: %s\n%s\n", this.to, this.cc, this.bcc, this.subject, this.body);
  }
}
