package uk.ks.jarvis.solver.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import uk.ks.jarvis.solver.R;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.holders.BaseHolder;
import uk.ks.jarvis.solver.shapes.Line;
import uk.ks.jarvis.solver.shapes.Shape;
import uk.ks.jarvis.solver.shapes.ShapeList;
import uk.ks.jarvis.solver.utils.ShapeNameGenerator;

/**
 * Created by sayenko on 7/17/13.
 */
public class CreateNewLineDialog extends DialogFragment implements View.OnClickListener, TextView.OnEditorActionListener {
    private static Button btnOk, btnCancel;
    private View view;
    private final BaseHolder baseHolder;

    public CreateNewLineDialog(BaseHolder baseHolder) {
        this.baseHolder = baseHolder;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.create_new_line_fragment, container);
//        xCoordinataNP = (NumberPicker) view.findViewById(R.id.xCoordinataNP);
//        xCoordinataNP.setMaxValue(600);
//        xCoordinataNP.setMinValue(0);
//        yCoordinataNP = (NumberPicker) view.findViewById(R.id.yCoordinataNP);
//        yCoordinataNP.setMaxValue(300);
//        yCoordinataNP.setMinValue(0);
        assert view != null;
        btnOk = (Button) view.findViewById(R.id.okButton);
        btnOk.setOnClickListener(this);
        btnCancel = (Button) view.findViewById(R.id.cancelButton);
        btnCancel.setOnClickListener(this);
        getDialog().setTitle("Set Coordinates");

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btnOk.getId()) {
//            radiusNP.clearFocus();
//            Float radius = new Float(radiusNP.getValue());
//            xCoordinataNP.clearFocus();
//            Float xCoordinate = new Float(xCoordinataNP.getValue());
//            yCoordinataNP.clearFocus();
//            Float yCoordinate = new Float(yCoordinataNP.getValue());
//            Shape circle = new Circle(radius,new Point(xCoordinate, yCoordinate),
//                                ShapeNameGenerator.getInstance().getNextName());
            Shape line = new Line(new Point(10f, 20f), new Point(150f, 300f), ShapeNameGenerator.getInstance().getNextName(),
                    ShapeNameGenerator.getInstance().getNextName());

            ShapeList listOfShapes = new ShapeList(line);
            baseHolder.addShape(listOfShapes);
            baseHolder.invalidate();
            this.dismiss();
        } else if (view.getId() == btnCancel.getId()) {
            this.dismiss();
        }

    }
}