package assignment7;


import java.util.Scanner;

interface Document {
    String getId();
    String getTitle();
    String getContent();
    String getAuthor();
}

class RealDocument implements Document {
    private final String id;
    private final String title;
    private final String content;
    private final String author;

    public RealDocument(String id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

}

class DocumentProxy implements Document {
    private final Document document;
    private final User currentUser;

    public DocumentProxy(Document document, User currentUser) {
        this.document = document;
        this.currentUser = currentUser;
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public String getTitle() {
        return document.getTitle();
    }

    @Override
    public String getContent() {
        return document.getContent();
    }

    @Override
    public String getAuthor() {
        return document.getAuthor();
    }
}

class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}



public class Assignment7Ex2{
    public static void main(String[] args){

        // for the sake of the example
        Document realDocument = new RealDocument("1", "Document Title", "Document Content", "Arthur");

        User user = new User("john.doe", "password123");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter username and password: ");
        String username = in.nextLine();
        String password = in.nextLine();

        int maxTries = 0;

        if(!username.equals(user.getUsername()) || !password.equals(user.getPassword())){
            while(maxTries<=3){
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                    break;
                }
                System.out.println("Username or password is not correct, try again ");
                username = in.nextLine();
                password = in.nextLine();
                maxTries++;
            }
        }

        if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
            System.out.println("Successfull authentication.");

            Document documentProxy = new DocumentProxy(realDocument, user);

            System.out.println("Document Title: " + documentProxy.getTitle());
            System.out.println("Document Content: " + documentProxy.getContent());
            System.out.println("Document Author: " + documentProxy.getAuthor());

        }
    }

}