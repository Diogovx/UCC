package UCC.ui;

import UCC.core.enums.CommentType;
import UCC.engine.visual.CommentaryEngine;

public interface FightEventListener {
    void onNewRound(int round);
    void onComment(CommentType type);
    void showProgressBar(String text,double currentValue, double maxValue, int blocks);
    void onText(String text);
    void onText();
    void onPrintWithDelay(String text, int delay);
    void onTypeEffect(String text, int delay);
    void showDivider();
}
