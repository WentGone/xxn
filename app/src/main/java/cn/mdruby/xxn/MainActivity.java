package cn.mdruby.xxn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Side;

public class MainActivity extends AppCompatActivity {
    private TextSurface textSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSurface = (TextSurface) findViewById(R.id.act_main_TextSurface);
        Text textDaai = TextBuilder
                .create("小仙")
                .setSize(64)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.SURFACE_CENTER).build();
        textSurface.play(
                new Sequential(
                        Slide.showFrom(Side.TOP, textDaai, 500),
                        Delay.duration(500),
                        Alpha.hide(textDaai, 1500)
                )
        );
    }
}
