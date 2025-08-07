package UCC.ui;

import UCC.engine.visual.CommentaryEngine;

public class ConsoleFightListener implements FightEventListener{
    @Override
    public void onPrintWithDelay(String text, int delay){
        ConsolePrinter.printWithDelay(text, delay);
    }
    @Override
    public void onTypeEffect(String text, int delay){
        ConsolePrinter.typeEffect(text, delay);
    }

    @Override
    public void onText(String text) {
        System.out.println(text);
    }
    @Override
    public void onText(){
        System.out.println();
    }

    @Override
    public void showProgressBar(String text, double currentValue, double maxValue, int blocks) {
        System.out.println(text + ConsolePrinter.progressBar(currentValue, maxValue, blocks));
    }

    @Override
    public void onComment(CommentaryEngine.CommentType type) {
        ConsolePrinter.printWithDelay(CommentaryEngine.getComment(type), 800);
    }

    @Override
    public void onNewRound(int round) {
        ConsolePrinter.roundBanner(round);
    }

}
