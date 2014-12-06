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

    public static Texture bgMenu;
    public static Texture bgEquipment;
    public static Texture inventory;
    public static Texture inventoryAccept;

    public static Texture item;

    public static Texture club;
    public static Texture knife;
    public static Texture sword;

    public static Texture descriptionBg;

    public static BitmapFont font12;
    public static BitmapFont font36;

    public static Skin mainMenuSkin;
    public static BitmapFont itemFont;
    public static BitmapFont mplus12;
    public static BitmapFont mplus20;
    public static BitmapFont mplus36;



    public static void init() {
        hero1 = new Texture("tex/hauptcharakter.png");
        hero2 = new Texture("tex/hero2.png");
        hero3 = new Texture("tex/hero3.png");

	club = new Texture("tex/club.png");
	knife = new Texture("tex/knife.png");
	sword = new Texture("tex/sword.png");

        bgMenu = new Texture("tex/bg_menu.png");
        item = new Texture("tex/item.png");
        inventory = new Texture("tex/inventory.png");
        inventoryAccept = new Texture("tex/inventory_accept.png");
        bgEquipment = new Texture("tex/equipment_bg.png");
        descriptionBg = new Texture("tex/description_bg.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Bebas.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 12;

        font12 = generator.generateFont(parameter);

        parameter.size = 36;

        font36 = generator.generateFont(parameter);

        generator.dispose();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/mplus.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 12;

        mplus12 = generator.generateFont(parameter);

        parameter.size = 20;

        mplus20 = generator.generateFont(parameter);

        parameter.size = 36;

        mplus36 = generator.generateFont(parameter);

        generator.dispose();

        itemFont = new BitmapFont(Gdx.files.internal("font/small.fnt"), Gdx.files.internal("font/small.png"), false);
        itemFont.setColor(1f, 1f, 1f, 1f);

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
