package UCC.engine.visual;

import UCC.core.enums.CommentType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommentaryEngine {
    private static final Map<CommentType, List<String>> comments = new EnumMap<>(CommentType.class);
    private static final Random random = new Random();

    static {
        comments.put(CommentType.HIT, List.of(
            "That was a solid hit!",
            "Boom! Right on target!",
            "He felt that one!",
            "What a strike!",
            "Precision and power combined!",
            "That's gonna leave a mark!",
            "He shook the whole arena!",
            "You could hear that from the back row!"
        ));
        comments.put(CommentType.MISS, List.of(
            "Swing and a miss!",
            "Too slow!",
            "He totally whiffed it!",
            "Nice try, but no contact!",
            "That punch hit the air!",
            "He telegraphed that one!",
            "No way that was landing!",
            "Missed by a mile!"
        ));
        comments.put(CommentType.BLOCKED, List.of(
                "Solid block!",
                "He saw it coming!",
                "No damage taken!",
                "Deflected perfectly!",
                "The defense was tight!",
                "Not today!",
                "Blocked with ease!",
                "Well-timed guard!"
        ));
        comments.put(CommentType.START, List.of(
                "Let the battle begin!",
                "We’re off to a thrilling start!",
                "Tension is high in the arena!",
                "Who's gonna make the first move?",
                "No holding back now!",
                "Both fighters look ready!",
                "All eyes are on the ring!",
                "This is what we've been waiting for!"
        ));
        comments.put(CommentType.CROWD_REACT, List.of(
                "The crowd is going wild!",
                "You can hear the fans chanting!",
                "The audience loves this fight!",
                "People are on their feet!",
                "They’re eating this up!",
                "Roars fill the arena!",
                "What a spectacle!",
                "Fans can't believe what they're seeing!"
        ));
        comments.put(CommentType.TIRED, List.of(
                "He's starting to slow down!",
                "The fatigue is catching up!",
                "Legs look heavy now!",
                "He's struggling to keep up the pace!",
                "Breathing hard… this is dangerous territory!",
                "Will he recover before it's too late?",
                "That fatigue bar is almost maxed!",
                "You can see it in his movement!"
        ));
        comments.put(CommentType.VICTORY, List.of(
                "That’s it! He’s the winner!",
                "Unstoppable performance!",
                "Victory has a name today!",
                "What a finish!",
                "He came, he saw, he conquered!",
                "Another one bites the dust!",
                "An unforgettable win!",
                "That’s the fight, folks!"
        ));
        comments.put(CommentType.STAGGERED, List.of(
                "He’s wobbling!",
                "That shook his whole body!",
                "He can barely stand!",
                "He’s not looking steady!",
                "This could be it!",
                "What a blow! He’s dazed!",
                "Staggered but still standing!",
                "On the ropes now!"
        ));
        comments.put(CommentType.KNOCKOUT_TEASE, List.of(
                "That’s one more and he’s done!",
                "Any hit now could end it!",
                "He’s hanging by a thread!",
                "One more like that and it's over!",
                "This might be the final blow!",
                "His body can’t take much more!",
                "He’s on the edge!"
        ));
        comments.put(CommentType.COMBO, List.of(
                        "Combo landed beautifully!",
                        "Back to back hits!",
                        "What a flurry of strikes!",
                        "He's not letting up!",
                        "Pressure non-stop!",
                        "Rapid fire attacks!",
                        "He's overwhelming him!"
        ));
        comments.put(CommentType.SURPRISE, List.of(
                        "Nobody saw that coming!",
                        "What a turnaround!",
                        "He dodged that?!",
                        "Out of nowhere!",
                        "The tables have turned!",
                        "He came back from the brink!",
                        "This fight just flipped!"
        ));
        comments.put(CommentType.TAUNT, List.of(
                        "He’s taunting the crowd!",
                        "A little showboating!",
                        "That smirk says it all!",
                        "He knows he’s got this!",
                        "Confidence at its peak!",
                        "That’s pure ego!",
                        "He’s daring him to strike!"
        ));
        comments.put(CommentType.DEFENSE_MASTER, List.of(
                        "Iron wall defense!",
                        "He’s unreadable!",
                        "Flawless defense!",
                        "Not a single hit got through!",
                        "This guy’s a fortress!",
                        "He’s blocking everything!",
                        "What a defensive masterclass!"
        ));
    }


    public static String getComment(CommentType type){
        List<String> commentList;
        commentList = comments.get(type);
        return commentList.get(random.nextInt(commentList.size()));
    }
}
