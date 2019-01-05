package com.selfapps.currencyfixermvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;

public class ConverterFragment extends Fragment {

    private ConverterViewModel mViewModel;
    private LiveData<FullCurrency> fullCurrency;
    private TextView text;
    public static ConverterFragment newInstance() {
        return new ConverterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.converter_fragment, container, false);

        text = view.findViewById(R.id.fragment_text);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConverterViewModel.class);

        fullCurrency = mViewModel.getLatest();
        fullCurrency.observe(this, new Observer<FullCurrency>() {
            @Override
            public void onChanged(@Nullable FullCurrency fullCurrency) {
                if(fullCurrency!= null) text.setText(fullCurrency.toString());
            }
        });

    }

}
