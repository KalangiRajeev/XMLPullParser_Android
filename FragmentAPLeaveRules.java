package com.kalangirajeev.ebooks.apgovtebooks;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class FragmentAPLeaveRules extends Fragment {


    ListView listView;
    EditText editText;
    OnDataPass dataPasser;
    HighLightArrayAdapter adapter;

    public FragmentAPLeaveRules() {
    }

    public static FragmentAPLeaveRules newInstance() {
        FragmentAPLeaveRules fragment = new FragmentAPLeaveRules();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apleaverules, container, false);

        editText = (EditText) view.findViewById(R.id.edit_text);
        listView = (ListView) view.findViewById(R.id.list_view);

        InitializeList();


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ReInitializeList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        Toast.makeText(getContext(), "No of Items in this ebook are..." + listView.getCount(), Toast.LENGTH_SHORT).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                dataPasser.onDataPass(adapter.getItem(position));
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dataPasser.onDataPass(adapter.getItem(position));
                view.setSelected(true);

                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", adapter.getItem(position));
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "Text Copied to the Clipboard... ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return view;
    }

    private void ReInitializeList(String string) {
        adapter.getFilter().filter(string);
        adapter.notifyDataSetChanged();
    }

    private void InitializeList() {
        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
            List<String> list = parser.parse(getActivity().getAssets().open("APLeaveRules.xml"));
            String string[] = list.toArray(new String[list.size()]);
            adapter = new HighLightArrayAdapter(getContext(), R.layout.list_item, R.id.list_item, string);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dataPasser = (OnDataPass) activity;
    }
}
