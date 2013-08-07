package uk.ks.jarvis.solver.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import uk.ks.jarvis.solver.R;
import uk.ks.jarvis.solver.holders.BaseHolder;

/**
 * Created by sayenko on 7/14/13.
 */
public class BaseHolderFragment extends Fragment {

    private View view, baseHolder;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_holder, container, false);
        assert view != null;
        context = view.getContext();
        baseHolder = (View) view.findViewById(R.id.baseHolder);
        baseHolder = new BaseHolder(context, getActivity());
        return baseHolder;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Dot:
                CreateNewDotDialog createNewDotDialog = new CreateNewDotDialog((BaseHolder) baseHolder);
                createNewDotDialog.show(this.getActivity().getSupportFragmentManager(), "");
                return true;
            case R.id.Circle:
                CreateNewCircleDialog customDialogFragment = new CreateNewCircleDialog((BaseHolder) baseHolder);
                customDialogFragment.show(this.getActivity().getSupportFragmentManager(), "");
                return true;
            case R.id.Line:
                CreateNewLineDialog createNewLineDialog = new CreateNewLineDialog((BaseHolder) baseHolder);
                createNewLineDialog.show(this.getActivity().getSupportFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}