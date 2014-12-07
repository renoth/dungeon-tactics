package de.renoth.dt.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.res.Resources;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class HighScoreLabel extends Actor {
    public static final int SMALL_ROW_HEIGHT = 16;
    private List<PositionedLabel> labels;

    public HighScoreLabel(int x, int y, int width, int height) {
        setPosition(x,y);
        setSize(width, height);

        FileHandle scoreFile = Gdx.files.local("dungeonTacticsHighScores.txt");

        List<String> entries = new ArrayList<>();
        entries.addAll(Arrays.asList(scoreFile.readString().split("\n")));

        entries.sort(new ExperienceSorter());

        entries.add(0, "NAME;XP;KILLS;DAMAGE");

        Label.LabelStyle style = new Label.LabelStyle(Resources.mplus12, Color.WHITE);

        labels = new ArrayList<>();

        for (String entry : entries) {
            StringJoiner joiner = new StringJoiner(" ");
            for (String s : entry.split(";")) {
                joiner.add(padLeft(s, 8));
            }

            PositionedLabel label = new PositionedLabel(joiner.toString(), style, new Point2D(10, 170 - SMALL_ROW_HEIGHT * entries.indexOf(entry)));
            label.setPosition(x,y);
            labels.add(label);
            if (entries.indexOf(entry) > 10) break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for (PositionedLabel l : labels) {
            l.getFont().draw(batch, l.getText().toString(), l.getX(), l.getY());
        }
    }

    private String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    private class ExperienceSorter implements java.util.Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int score1 = Integer.parseInt(o1.split(";")[1]);
            int score2 = Integer.parseInt(o2.split(";")[1]);
            if (score1 < score2) {
                return 1;
            }
            if (score2 < score1) {
                return -1;
            }
            return 0;
        }
    }
}
