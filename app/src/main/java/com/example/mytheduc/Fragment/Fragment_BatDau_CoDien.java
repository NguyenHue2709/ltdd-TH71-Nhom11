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

public class Fragment_BatDau_CoDien extends Fragment {
    private BatDauAdapter batDauAdapter;
    private ArrayList<BatDau_Model> listBaiTap = new ArrayList<>();
    ListView list;
    private int [] imgArray = {R.drawable.codien1, R.drawable.codien2, R.drawable.codien3, R.drawable.codien4,
            R.drawable.codien5, R.drawable.codien6, R.drawable.codien7, R.drawable.codien8};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batdau_codien, container, false);

        list = (ListView) view.findViewById(R.id.listCoDien);
        setList();
        batDauAdapter = new BatDauAdapter(listBaiTap);
        list.setAdapter(batDauAdapter);
        return view;
    }

    public void setList () {
        listBaiTap.add(new BatDau_Model("CHỐNG ĐẨY"));
        listBaiTap.add(new BatDau_Model("NGỒI DỰA TƯỜNG"));
        listBaiTap.add(new BatDau_Model("ĐO SÀN"));
        listBaiTap.add(new BatDau_Model("CHỐNG ĐẨY VÀ XOAY NGƯỜI"));
        listBaiTap.add(new BatDau_Model("BƯỚC LÊN GHẾ"));
        listBaiTap.add(new BatDau_Model("TẬP CƠ BỤNG"));
        listBaiTap.add(new BatDau_Model("TẬP CƠ TAY SAU TRÊN GHẾ"));
        listBaiTap.add(new BatDau_Model("BƯỚC GẬP GỐI"));
    }

    public int[] getImgArray() {
        return imgArray;
    }
    public ArrayList getArrayList() {
        return listBaiTap;
    }
}
