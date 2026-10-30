package Task3_AIChatbot;

import java.util.Scanner;

public class AIChatbot {

    public static String getResponse(String input) {

        String message = input.toLowerCase().trim();

        if (message.contains("hello")
                || message.contains("hi")
                || message.contains("hey")) {

            return "Hello! How can I help you?";

        } else if (message.contains("your name")
                || message.contains("who are you")) {

            return "I am CodeAlpha Bot, your virtual assistant.";

        } else if (message.contains("how are you")) {

            return "I am doing great! Thank you for asking.";

        } else if (message.contains("java")) {

            return "Java is an object-oriented programming language.";

        } else if (message.contains("python")) {

            return "Python is a popular programming language.";

        } else if (message.contains("internship")) {

            return "An internship helps you gain practical work experience.";

        } else if (message.contains("help")) {

            return "You can ask me about Java, Python, or internships.";

        } else if (message.contains("thank")) {

            return "You are welcome!";

        } else if (message.contains("bye")
                || message.contains("exit")) {

            return "Goodbye! Have a great day.";

        } else {

            return "Sorry, I do not understand that question yet.";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       CODEALPHA CHATBOT");
        System.out.println("================================");
        System.out.println("Type 'bye' or 'exit' to stop.");

        while (true) {

            System.out.print("\nYou: ");

            String input = sc.nextLine();

            String response = getResponse(input);

            System.out.println("Bot: " + response);

            String message = input.toLowerCase().trim();

            if (message.contains("bye") || message.contains("exit")) {
                break;
            }
        }

        sc.close();
    }
}