package UCC.ui;

import UCC.engine.visual.CommentaryEngine;

public interface FightEventListener {
    void onNewRound(int round);
    void onComment(CommentaryEngine.CommentType type);
    void showProgressBar(String text,double currentValue, double maxValue, int blocks);
    void onText(String text);
    void onText();
    void onPrintWithDelay(String text, int delay);
    void onTypeEffect(String text, int delay);
}
