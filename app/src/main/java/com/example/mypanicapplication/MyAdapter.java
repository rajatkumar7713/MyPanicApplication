package com.example.mypanicapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<People> arrayList;
    TextView t1_id,t2_name,t3_number;
    public MyAdapter(Context context, ArrayList<People> arrayList  ) {
        this.context=context;
        this.arrayList=arrayList;

    }
    @Override
    public long getItemId( int position ) {
        return position;
    }

    @Override
    public Object getItem( int position ) {
        return arrayList.get(position);

    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.list_layout,null);
         t1_id=(TextView)convertView.findViewById(R.id.id_txt);
         t2_name=(TextView)convertView.findViewById(R.id.name_txt);
         t3_number=(TextView)convertView.findViewById(R.id.num_txt);
         People people =arrayList.get(position);
         t1_id.setText(String.valueOf(people.getId()));
         t2_name.setText(people.getName());
         t3_number.setText(people.getNumber());
         return convertView;
    }

    @Override
    public int getCount() {
        return  this.arrayList.size();
    }
}
