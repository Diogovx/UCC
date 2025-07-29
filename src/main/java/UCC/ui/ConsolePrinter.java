package UCC.ui;

public class ConsolePrinter {
    public static void printWithDelay(String text, int delay){
        System.out.println(text);
        pause(delay);
    }

    public static void typeEffect(String text, int delay){
        for(char c : text.toCharArray()){
            System.out.print(c);
            pause(delay);
        }
        System.out.println();
    }

    public static void divider(){
        System.out.println("---------------------------------------------");
    }

    public static void roundBanner(int round){
        divider();
        printWithDelay(">> Round " + round, 400);
        divider();
    }

    public static void pause(int delay){
        try{
            Thread.sleep(delay);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
