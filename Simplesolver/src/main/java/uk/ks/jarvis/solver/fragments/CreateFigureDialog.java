package uk.ks.jarvis.solver.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import uk.ks.jarvis.solver.CoordinatePlane.SystemInformation;
import uk.ks.jarvis.solver.R;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.holders.BaseHolder;
import uk.ks.jarvis.solver.shapes.Circle;
import uk.ks.jarvis.solver.shapes.Dot;
import uk.ks.jarvis.solver.shapes.EndlessLine;
import uk.ks.jarvis.solver.shapes.Line;
import uk.ks.jarvis.solver.shapes.Shape;
import uk.ks.jarvis.solver.utils.LowCaseLettersGenerator;
import uk.ks.jarvis.solver.utils.UpCaseLettersGenerator;

/**
 * Created by root on 7/28/13.
 */
public class CreateFigureDialog extends DialogFragment implements View.OnClickListener {

    private static Button btnCancel;
    private final BaseHolder baseHolder;
    private View view;
    private TextView dotButton;
    private TextView circleButton;
    private TextView lineButton;
    private TextView endlessLineButton;

    public CreateFigureDialog(BaseHolder baseHolder) {
        super();
        this.baseHolder = baseHolder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_figure_dialog, container);

        getDialog().setTitle("Select the figure to create");
        setupButtons();
        return view;
    }

    private void setupButtons() {
        dotButton = (TextView) view.findViewById(R.id.create_dot);
        dotButton.setOnClickListener(this);

        circleButton = (TextView) view.findViewById(R.id.create_circle);
        circleButton.setOnClickListener(this);

        lineButton = (TextView) view.findViewById(R.id.create_short_line);
        lineButton.setOnClickListener(this);

        endlessLineButton = (TextView) view.findViewById(R.id.create_line);
        endlessLineButton.setOnClickListener(this);

        btnCancel = (Button) view.findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (dotButton.getId() == view.getId()) {
            Shape dot = new Dot(new Point(0f, 0f), UpCaseLettersGenerator.getInstance().getNextName());
            baseHolder.setCreateFigureMode(dot);
            Toast.makeText(baseHolder.getContext(), "Touch the screen to draw a dot.", 50).show();
            this.dismiss();
        } else if (circleButton.getId() == view.getId()) {
            Shape circle = new Circle(1f, new Point(0f, 0f), UpCaseLettersGenerator.getInstance().getNextName());
            baseHolder.setCreateFigureMode(circle);
            Toast.makeText(baseHolder.getContext(), "Drag your finger across the screen to draw a circle.", 50).show();
            this.dismiss();
        } else if (lineButton.getId() == view.getId()) {
            Shape line = new Line(new Point(0f, 0f), new Point(0f, 0f), UpCaseLettersGenerator.getInstance().getNextName(), UpCaseLettersGenerator.getInstance().getNextName());
            baseHolder.setCreateFigureMode(line);
            Toast.makeText(baseHolder.getContext(), "Drag your finger across the screen to draw a line.", 50).show();
            this.dismiss();
        } else if (endlessLineButton.getId() == view.getId()) {
            Shape endlessLine = new EndlessLine(new Point(0f, 0f), new Point((float) SystemInformation.DISPLAY_WIDTH, (float) SystemInformation.DISPLAY_HEIGHT), LowCaseLettersGenerator.getInstance().getNextName(), baseHolder);
            baseHolder.setCreateFigureMode(endlessLine);
//            Toast.makeText(baseHolder.getContext(), "Drag your finger across the screen to draw a пряма.", 50).show();
            this.dismiss();

        } else if (view.getId() == btnCancel.getId()) {
            this.dismiss();
        }
    }
}