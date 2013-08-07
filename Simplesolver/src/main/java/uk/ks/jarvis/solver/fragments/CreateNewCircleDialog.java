package uk.ks.jarvis.solver.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import uk.ks.jarvis.solver.R;
import uk.ks.jarvis.solver.beans.Point;
import uk.ks.jarvis.solver.holders.BaseHolder;
import uk.ks.jarvis.solver.shapes.Circle;
import uk.ks.jarvis.solver.shapes.Shape;
import uk.ks.jarvis.solver.shapes.ShapeList;
import uk.ks.jarvis.solver.utils.ShapeNameGenerator;

import java.util.Calendar;
//import uk.ks.jarvis.solver.NumberPicker.NumberPicker;

/**
 * Created with IntelliJ IDEA.
 * User: ksk
 * Date: 17.03.13
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class CreateNewCircleDialog extends DialogFragment implements OnClickListener, OnEditorActionListener {
    private static Button btnOk, btnCancel;
    //    private NumberPicker xCoordinataNP, yCoordinataNP, radiusNP, tmpNP;
    private final BaseHolder baseHolder;
    private Calendar mCalendar;
    private TextView mTextView;
    private View view;

    public CreateNewCircleDialog(BaseHolder baseHolder) {
        super();
        this.baseHolder = baseHolder;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.create_new_circle_fragment, container);
//        xCoordinataNP = (NumberPicker) view.findViewById(R.id.xCoordinataNP);
////        xCoordinataNP.setMaxValue(600);
////        xCoordinataNP.setMinValue(0);
//        yCoordinataNP = (NumberPicker) view.findViewById(R.id.yCoordinataNP);
////        yCoordinataNP.setMaxValue(300);
////        yCoordinataNP.setMinValue(0);
//        radiusNP = (NumberPicker) view.findViewById(R.id.radiusNP);
////        radiusNP.setMaxValue(50);
////        radiusNP.setMinValue(0);
        assert view != null;
        btnOk = (Button) view.findViewById(R.id.okButton);
        btnOk.setOnClickListener(this);
        btnCancel = (Button) view.findViewById(R.id.cancelButton);
        btnCancel.setOnClickListener(this);
        getDialog().setTitle("Set Coordinate");
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnOk.getId()) {
//            radiusNP.clearFocus();
//            Float radius = new Float(radiusNP.getCurrent());
//            xCoordinataNP.clearFocus();
//            Float xCoordinate = new Float(xCoordinataNP.getCurrent());
//            yCoordinataNP.clearFocus();
//            Float yCoordinate = new Float(yCoordinataNP.getCurrent());
//            Shape circle = new Circle(radius,new Point(xCoordinate, yCoordinate),
//                                ShapeNameGenerator.getInstance().getNextName());
            Shape circle = new Circle(100f, new Point(80f, 80f), ShapeNameGenerator.getInstance().getNextName());

            ShapeList listOfShapes = new ShapeList(circle);
            baseHolder.addShape(listOfShapes);
            baseHolder.invalidate();
            this.dismiss();
        } else if (view.getId() == btnCancel.getId()) {
            this.dismiss();
        }
    }
}