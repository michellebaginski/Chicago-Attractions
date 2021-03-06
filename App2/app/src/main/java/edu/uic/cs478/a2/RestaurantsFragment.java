package edu.uic.cs478.a2;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class RestaurantsFragment extends Fragment {
    private String[] restaurants = {"Alinea", "Roka Akor Sushi", "Yuzu Sushi & Robata Grill",
            "Eataly", "Beatnik on the River", "Green Street Meats"};
    private ListSelectionListener listener = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            // Try casting the containing activity to a ListSelectionListener
            listener = (ListSelectionListener) context;

        } catch (ClassCastException e) {
            // Cast failed: This is not going to work because containing activity may not
            // have implemented onListSelection() method
            throw new ClassCastException(context.toString());
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurants_layout, container, false);

        final ListView list = (ListView) view.findViewById(R.id.restaurants_menu);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, restaurants);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.setItemChecked(position, true);
                listener.onListSelection(position);
            }
        });

        return view;
    }

    public interface ListSelectionListener {
        public void onListSelection(int index);
    }
}
