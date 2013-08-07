package uk.ks.jarvis.solver.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import uk.ks.jarvis.solver.R;

/**
 * Created by sayenko on 7/14/13.
 */
public class LogFragment extends Fragment implements View.OnTouchListener {

    private View view = null;
    private Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.log, container, false);
        assert view != null;
        context = view.getContext();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int k = 5;
        return false;
    }
}

