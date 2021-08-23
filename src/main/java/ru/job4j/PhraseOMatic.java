package ru.job4j;

public class PhraseOMatic {
    public static void main(String[] args) {
        String[] wordListOne = {"Привет", "Шалом", "Hello", "Hola", "Салам", "Aloha", "Ciao", "Guten Tag",
        "Ola", "Салам алейкум", "Konnichi wa"};

        String[] wordListTwo = {", мой друг.", ", my friend.", ", братанчик.", ", amigo."};
        String[] wordListThree = {"How are you?", "Wie geht es Ihnen?", "Como esta?", "Как ты?", "Nasilsin?"};

        int oneLength = wordListOne.length;
        int twoLength = wordListTwo.length;
        int threeLength = wordListThree.length;

        int rand1 = (int) (Math.random() * oneLength);
        int rand2 = (int) (Math.random() * twoLength);
        int rand3 = (int) (Math.random() * threeLength);

        String phrase = wordListOne[rand1] + "" + wordListTwo[rand2] + " " + wordListThree[rand3];
        System.out.println(phrase);

    }
}
