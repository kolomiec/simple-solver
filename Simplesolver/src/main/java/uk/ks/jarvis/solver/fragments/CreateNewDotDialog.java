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
import uk.ks.jarvis.solver.shapes.Dot;
import uk.ks.jarvis.solver.shapes.Shape;
import uk.ks.jarvis.solver.shapes.ShapeList;
import uk.ks.jarvis.solver.utils.ShapeNameGenerator;

import java.util.Calendar;

//import android.widget.NumberPicker;

/**
 * Created by sayenko on 7/14/13.
 */

public class CreateNewDotDialog extends DialogFragment implements View.OnClickListener, TextView.OnEditorActionListener {
    private Calendar mCalendar;
    private TextView mTextView;
    private static Button btnOk, btnCancel;
    private View view;
    //    private NumberPicker xCoordinataNP, yCoordinataNP, radiusNP, tmpNP;
    private final BaseHolder baseHolder;

    public CreateNewDotDialog(BaseHolder baseHolder) {
        super();
        this.baseHolder = baseHolder;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.create_new_dot_fragment, container);
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
        getDialog().setTitle("Set Coordinate");

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnOk.getId()) {
//            xCoordinataNP.clearFocus();
//            Float xCoordinate = new Float(xCoordinataNP.getValue());
//            yCoordinataNP.clearFocus();
//            Float yCoordinate = new Float(yCoordinataNP.getValue());
            Shape dot = new Dot(new Point(100f, 100f), ShapeNameGenerator.getInstance().getNextName());
            ShapeList listOfShapes = new ShapeList(dot);
            baseHolder.addShape(listOfShapes);
            baseHolder.invalidate();
            this.dismiss();
        } else if (v.getId() == btnCancel.getId()) {
            this.dismiss();
        }
    }
}


