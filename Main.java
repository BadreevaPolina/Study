package com.company;
import com.company.races.AbstractRace;

import java.util.Objects;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        GameController controller = new GameController();
        Scanner input = new Scanner(System.in);
        System.out.println("Если Вам нужны правила игры - Введите: Правила");
        System.out.println("Если Вам нужно начать игру - Введите: Игра");
        if (input.nextLine().equals("Правила")){
            System.out.println("Каждому игроку дается определенное количество монет(45).Сколько сил у монстра неизвестно(диапозон:30-60).");
            System.out.println("Существует 5 рас, в каждой есть 3 персонажа, у каждого персонажа по 3 способности(которые необходимо покупать).");
            System.out.println("Цель игры: Купить достаточно способностей, чтобы в сумме у обоих игроков было большн сил, чем у монстра. Но потратить меньше монеток чем другой игрок.");
            System.out.println("Если монстр жив после 2 раундов - победа за ним.");
            System.out.println("Выбирать какую способность использовать нужно с помощью цифр(1,2,3),способность персонажа не нужна(0).");
            System.out.println("");
        }
        System.out.println("Выберите режим игры (Игрок и Игрок, ИИ и ИИ или Игрок и ИИ)");
        String gameMode = input.nextLine();
        switch (gameMode) {
            case ("Игрок и Игрок"):
                controller.startNewGame();
                break;
            case ("Игрок и ИИ"):
                controller.startNewAIGame();
                break;
            case ("ИИ и ИИ"):
                controller.startNewAIAIGame();
                break;
            default:
                System.out.println("Все-го хо-ро-ше-го!");
                System.out.println("Вы играете с ИИ");
                controller.startNewAIGame();
                break;
            }
    }

}