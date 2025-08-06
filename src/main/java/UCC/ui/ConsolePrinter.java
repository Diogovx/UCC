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

    public static void progressBar(double currentValue, double maxValue){
        double currentPercentage = (currentValue * 100) / maxValue;
        if(currentPercentage > 100)
            System.out.print("100%");
        else
            System.out.print(Math.round(currentPercentage) + "%");
        System.out.print(" [");
        for(int i = 0; i < 10; i++){
            if (currentValue > maxValue * 0.1){
                System.out.print("-");
                currentValue = currentValue - (maxValue * 0.1);
            } else{
                System.out.print(" ");
            }
        }
        System.out.print("] ");
        System.out.println("100%");
    }
}
