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

    public static boolean answerQuestion(String question) {
        System.out.println(question);
        return "Y".equalsIgnoreCase(new Scanner(System.in).nextLine());
    }
}
