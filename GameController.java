package com.company;

import com.company.characters.AbstractCharacter;
import com.company.races.*;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    private final Scanner input = new Scanner(System.in);
    private final Random random = new Random();
    private int monsterHp = 0;
    private int roundNumber = 0;
    private AbstractRace race1;
    private AbstractRace race2;

    public GameController() {}

    public void startNewAIAIGame() {
        monsterHp = generateMonsterHp();
        System.out.println("Искусственный интеллект1: Человеки");
        race1 = new HumanRace();
        System.out.println("Искусственный интеллект2: Тьма");
        race2 = new DarkRace();
        while (monsterHp > 0 && race1.getMoney() > 0 && race2.getMoney() > 0 && roundNumber != 2) {
            roundNumber++;
            System.out.println("Сила монстра: " + monsterHp);

            System.out.println("Ходит ИИ1");
            int aiDamage = (random.nextInt(3) + 1) * 5 * (random.nextInt(3) + 1);
            if (race1.subtractMoney(aiDamage) != -1)
                monsterHp -= aiDamage;
            else {
                aiDamage = race1.getMoney();
                race1.subtractMoney(aiDamage);
                monsterHp -= aiDamage;
            }
            System.out.printf("ИИ1 потратил %d монет\n",aiDamage);

            System.out.println("Ходит ИИ2");
            int aiDamage2 = (random.nextInt(3) + 1) * 5 * (random.nextInt(3) + 1);
            if (race2.subtractMoney(aiDamage2) != -1)
                monsterHp -= aiDamage2;
            else {
                aiDamage2 = race2.getMoney();
                race2.subtractMoney(aiDamage2);
                monsterHp -= aiDamage2;
            }
            System.out.printf("ИИ1 потратил %d монет\n",aiDamage2);
        }

        calcVictory();

    }
    public void startNewAIGame() {
        monsterHp = generateMonsterHp();

        race1 = pickRace();
        System.out.println("Игрок:");
        race1.print();

        System.out.println("Искусственный интеллект:");
        race2 = new HumanRace();

        while (monsterHp > 0 && race1.getMoney() > 0 && race2.getMoney() > 0 && roundNumber != 2) {
            roundNumber++;
            System.out.println("Сила монстра: " + monsterHp);

            System.out.println("Ходит первый игрок");
            makeTurn(race1);

            System.out.println("Ходит ИИ");
            int aiDamage = (random.nextInt(3) + 1) * 5 * (random.nextInt(3) + 1);
            if (race2.subtractMoney(aiDamage) != -1)
                monsterHp -= aiDamage;
            else {
                aiDamage = race2.getMoney();
                race2.subtractMoney(aiDamage);
                monsterHp -= aiDamage;
            }
        }

        calcVictory();
    }

    public void startNewGame() {
        // Начало игры
        monsterHp = generateMonsterHp();

        race1 = pickRace();
        System.out.println("Игрок 1:");
        race1.print();

        race2 = pickRace();
        System.out.println("Игрок 2:");
        race2.print();

        // Игра
        while (monsterHp > 0 && race1.getMoney() > 0 && race2.getMoney() > 0 && roundNumber != 2) {
            roundNumber++;
        //    System.out.println("Сила монстра: " + monsterHp);

            System.out.println("Ходит первый игрок");
            makeTurn(race1);

            System.out.println("Ходит второй игрок");
            makeTurn(race2);
        }

        // Конец игры
        calcVictory();
    }

    private void calcVictory() { // определяем победителя
        if (monsterHp > 0)
            System.out.println("Монстр выиграл");
        else {
            if (race1.getMoney() > race2.getMoney()) {
                System.out.println("Игрок 1 победил");
            }
            else if (race1.getMoney() < race2.getMoney())
                System.out.println("Игрок 2 победил");
            else
                System.out.println("Ничья");
        }
    }

    private void makeTurn(AbstractRace race) {
        System.out.println("Выберите способность персонажа " + race.getCharacter1().getName());
        useSkill(race,race.getCharacter1());
        System.out.println("Выберите способность персонажа " + race.getCharacter2().getName());
        useSkill(race,race.getCharacter2());
        System.out.println("Выберите способность персонажа " + race.getCharacter3().getName());
        useSkill(race,race.getCharacter3());
    }

    private void useSkill(AbstractRace race,AbstractCharacter character) {
        int num = input.nextInt();
        switch (num) {
            case(0):
                System.out.printf("Способность персонажа %s не выбрана\n ", character.getName());
                break;
            case (1):
                if (race.subtractMoney(character.getFirstSkill().getCost()) != -1)
                    monsterHp -= character.getFirstSkill().getDamage();
                else
                    System.out.println("Ой, не хватает денег");
                break;
            case (2):
                if (race.subtractMoney(character.getSecondSkill().getCost()) != -1)
                    monsterHp -= character.getSecondSkill().getDamage();
                else
                    System.out.println("Ой, не хватает денег");
                break;
            case (3):
                if (race.subtractMoney(character.getThirdSkill().getCost()) != -1)
                    monsterHp -= character.getThirdSkill().getDamage();
                else
                    System.out.println("Ой, не хватает денег");
                break;
            default:
                System.out.println("Неверно введена цифра. Возможный ввод:0,1,2,3");
                System.out.printf("Способность персонажа %s не выбрана\n ", character.getName());
        }
    }

    private AbstractRace pickRace() { // выбрать персонажа
        System.out.println("Выбери из: Человеки, Тьма, Свет, Животные, Нежить");
        String raceName = input.nextLine();
        switch (raceName) {
            case("Человеки"):
                return new HumanRace();
            case("Тьма"):
                return new DarkRace();
            case ("Свет"):
                return new LightRace();
            case("Животные"):
                return  new AnimalRace();
            case("Нежить"):
                return  new UndeadRace();
        }

        return new HumanRace();
    }

    private int generateMonsterHp() {
        return 30 + (random.nextInt(6) + 1) * 5;
    }
}
