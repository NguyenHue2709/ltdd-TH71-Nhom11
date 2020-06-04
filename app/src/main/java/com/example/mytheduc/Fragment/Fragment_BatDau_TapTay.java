package com.example.mytheduc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytheduc.Adapter.BatDauAdapter;
import com.example.mytheduc.Model.BatDau_Model;
import com.example.mytheduc.R;

import java.util.ArrayList;

public class Fragment_BatDau_TapTay extends Fragment {
    private BatDauAdapter batDauAdapter;
    private ArrayList<BatDau_Model> listBaiTap = new ArrayList<>();
    ListView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batdau_taptay, container, false);

        list = (ListView) view.findViewById(R.id.listTay);
        setList(listBaiTap);
        batDauAdapter = new BatDauAdapter(listBaiTap);
        list.setAdapter(batDauAdapter);
        return view;
    }

    public void setList (ArrayList arr) {
        arr.add(new BatDau_Model("CHỐNG ĐẨY"));
        arr.add(new BatDau_Model("TẬP CƠ TAY SAU TRÊN GHẾ"));
        arr.add(new BatDau_Model("ĐẤM"));
        arr.add(new BatDau_Model("PLANK LÊN VÀ XUỐNG"));
        arr.add(new BatDau_Model("CĂNG VAI"));
        arr.add(new BatDau_Model("XOAY TAY"));
        arr.add(new BatDau_Model("CHỐNG ĐẨY NGƯỢC"));
        arr.add(new BatDau_Model("CHỐNG ĐẨY MỘT CHÂN"));
        arr.add(new BatDau_Model("HÍT ĐẤT VỖ VAI"));
        arr.add(new BatDau_Model("CĂNG CƠ TAY TRÁI"));
    }

}