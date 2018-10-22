package org.rkong.games;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            BlackJack blackJack = new BlackJack();
            blackJack.play();

            String question = "Do you want to play again? (Y/N)";
            boolean yes = answerQuestion(question);
            if (!yes) {
                break;
            }
        }
    }

    static boolean answerQuestion(String question) {
        while (true) {
            System.out.println(question);
            String input = new Scanner(System.in).nextLine().trim();
            if (input.length() == 0) {
                continue;
            }
            return "Y".equalsIgnoreCase(input.substring(0, 1));
        }
    }
}
