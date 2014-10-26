package de.renoth.dt.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Resources {
    public static Texture hero1;
    public static Texture hero2;
    public static Texture hero3;

    public static BitmapFont font12;
    public static BitmapFont font36;

    public static Skin mainMenuSkin;

    public static void init() {
        hero1 = new Texture("hero1.png");
        hero2 = new Texture("hero2.png");
        hero3 = new Texture("hero3.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Bebas.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 12;

        font12 = generator.generateFont(parameter);

        parameter.size = 36;

        font36 = generator.generateFont(parameter);

        generator.dispose();

        mainMenuSkin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        mainMenuSkin.add("white", new Texture(pixmap));

        mainMenuSkin.add("default", font12);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = mainMenuSkin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = mainMenuSkin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = mainMenuSkin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = mainMenuSkin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = mainMenuSkin.getFont("default");
        textButtonStyle.pressedOffsetY = -2F;
        mainMenuSkin.add("default", textButtonStyle);
    }
}
