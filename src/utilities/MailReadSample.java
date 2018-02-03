package utilities;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public class MailReadSample {

    Folder inbox;
    // SearchUrl searchUrl = new SearchUrl();
    String activationUrlCode;
    String expectedSubject;
    int count;

    // Constructor of the class.
    public String FetchMails(String host, String username, String password, String sub) {

        expectedSubject = sub;
		/* Set the mail properties */
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
			/* Create the session and get the store for read the mail. */
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);

			/* Mention the folder name which you want to read. */
            inbox = store.getFolder("Inbox");
            System.out.println("No of Unread Messages :" + inbox.getUnreadMessageCount());

			/* Open the inbox using store. */
            inbox.open(Folder.READ_ONLY);

			/* Get the messages which is unread in the Inbox */
            Message messages[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			/* Use a suitable FetchProfile */
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            fp.add(FetchProfile.Item.CONTENT_INFO);
            inbox.fetch(messages, fp);

            try {
                printAllMessages(messages);
                inbox.close(true);
                store.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return activationUrlCode;
    }

    public void printAllMessages(Message[] msgs) throws Exception {
        for (int i = 0; i < msgs.length; i++) {
            System.out.println("MESSAGE #" + (i + 1) + ":");
            printEnvelope(msgs[i]);
        }
    }

    /* Print the envelope(FromAddress,ReceivedDate,Subject) */
    public void printEnvelope(Message message) throws Exception {
        Address[] a;
        // FROM
        if ((a = message.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("FROM: " + a[j].toString());
            }
        }
        // TO
        if ((a = message.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("TO: " + a[j].toString());
            }
        }

        String subject = message.getSubject();
        if (subject.equalsIgnoreCase(expectedSubject)) {
            String content = message.getContent().toString();
            System.out.println(content);
            activationUrlCode = content.substring(
                    content.indexOf("https://devso.mediaferry.com/mf-s40qa/login/activateUserNow/"),
                    content.indexOf("'>"));
        }
    }

    public void getContent(Part p) {
        try {
            String contentType = p.getContentType();
            // System.out.println("Content Type : " + contentType);
            Multipart mp = (Multipart) p.getContent();

            int count = mp.getCount();
            for (int i = 0; i < count; i++) {
                dumpPart(mp.getBodyPart(i));
            }
        } catch (Exception ex) {
            // System.out.println("Exception arise at get Content");
            ex.printStackTrace();
        }
    }

    public void dumpPart(Part p) throws Exception {
        // Dump input stream ..
        InputStream is = p.getInputStream();
        // If "is" is not already buffered, wrap a BufferedInputStream
        // around it.
        if (!(is instanceof BufferedInputStream)) {
            is = new BufferedInputStream(is);
        }

        int c;
        System.out.println("Message : ");
        while ((c = is.read()) != -1) {
            // System.out.write(c);

        }

        String currentUsersDir = System.getProperty("user.dir");
        String filepath = currentUsersDir + "\\temporary\\out.txt";
        PrintStream myconsole = new PrintStream(new File(filepath));
        System.setOut(myconsole);
        myconsole.print(p.getContent());
    }

    public static void main(String args[]) {
        MailReadSample mr = new MailReadSample();
        String url = mr.FetchMails("imap.gmail.com", "qa.gaurav239@gmail.com", "result1029", "[FromName] EKCS has a query My Test Project  412_2017-10-12");
	    System.out.println(url);
    }
}
