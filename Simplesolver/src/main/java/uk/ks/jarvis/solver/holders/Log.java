package uk.ks.jarvis.solver.holders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import uk.ks.jarvis.solver.shapes.ShapeList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sayenko on 7/14/13.
 */
public class Log extends View {

    private final Context context;
    private FragmentActivity activity;
    private List<ShapeList> shapes = new ArrayList<ShapeList>();
    private Paint paint = new Paint();


    public Log(Context context) {
        super(context);

        this.context = context;
    }

    public Log(Context context, FragmentActivity activity) {
        super(context);

        this.setHorizontalFadingEdgeEnabled(true);
        this.setVerticalFadingEdgeEnabled(false);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onDraw(Canvas canvas) {
        refresh(canvas, paint);
    }

    private void refresh(Canvas canvas, Paint p) {
        canvas.drawText("I was here", 0, 15, p);
    }


}