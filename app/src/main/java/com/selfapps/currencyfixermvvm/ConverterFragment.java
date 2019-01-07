package com.selfapps.currencyfixermvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConverterFragment extends Fragment {

    private ConverterViewModel mViewModel;
    private LiveData<FullCurrency> fullCurrency;
    private LiveData<Map<String,String>> definitions;


    private Spinner spFrom,spTo;
    ArrayAdapter<String> adapterFrom, adapterTo;
    private TextView convertion;
    private EditText exchangeValue;

    public static ConverterFragment newInstance() {
        return new ConverterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.converter_fragment, container, false);
        convertion = view.findViewById(R.id.tv_convertion);

        initEditText(view);
        spFrom = view.findViewById(R.id.spinner_from);
        spTo = view.findViewById(R.id.spinner_to);

        return view;
    }

    private void initEditText(View view) {
        exchangeValue = view.findViewById(R.id.et_value);
        exchangeValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateUpdates();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initSpinners(Context c) {
        if(definitions != null){
            List<String> keys = new ArrayList<>(definitions.getValue().keySet());
            adapterFrom = new ArrayAdapter<>(c,
                    android.R.layout.simple_spinner_item, keys);
            adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spFrom.setAdapter(adapterFrom);
            spFrom.setSelection(5);

            adapterTo = new ArrayAdapter<>(c,
                    android.R.layout.simple_spinner_item, keys);
            adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTo.setAdapter(adapterTo);
            spTo.setSelection(7);

            AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { calculateUpdates(); }

                @Override
                public void onNothingSelected(AdapterView<?> parent) { }};

            spTo.setOnItemSelectedListener(listener);
            spFrom.setOnItemSelectedListener(listener);
        }

    }

    private void calculateUpdates() {
        double amount = Double.parseDouble(String.valueOf(exchangeValue.getText()));
        double exchangeRateFrom = fullCurrency.getValue().rates.get(spFrom.getSelectedItem().toString());
        double exchangeRateTo = fullCurrency.getValue().rates.get(spTo.getSelectedItem().toString());

        convertion.setText(Util.convertValue(amount,exchangeRateFrom,exchangeRateTo)+"");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConverterViewModel.class);

        fullCurrency = mViewModel.getLatest();
        fullCurrency.observe(this, new Observer<FullCurrency>() {
            @Override
            public void onChanged(@Nullable FullCurrency fullCurrency) {
               // if(fullCurrency!= null) text.setText(fullCurrency.toString());
            }
        });


        definitions = mViewModel.getDefinitions();
        definitions.observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(@Nullable Map<String, String> def) {
                initSpinners(getContext());
//               // adapterFrom.clear();
//                adapterFrom.addAll((String[]) Objects.requireNonNull(def.keySet().toArray()));
//                //adapterTo.clear();
//                adapterTo.addAll((String[]) Objects.requireNonNull(def.keySet().toArray()));
            }
        });

    }

}
