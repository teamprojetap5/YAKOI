package com.example.mbraconnier.yakoi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by mbraconnier on 18/01/2018.
 */

class MyAdapterTheme extends RecyclerView.Adapter<MyAdapterTheme.MyViewHolder> {
    List<theme> list;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterTheme(List<theme> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemrecyclerviewthemebutton,viewGroup,false);
        view.setId(R.id.myadapteurview);
        return new MyViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        theme myObject = list.get(position);
        myViewHolder.bind(myObject);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{



        private  Button Mybouton ;
        //itemView est la vue correspondante à 1 cellule
        public MyViewHolder(View itemView) {
            super(itemView);

            //c'est ici que l'on fait nos findView

            Mybouton = (Button) itemView.findViewById(R.id.buttontheme);

        }

        //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
        public void bind(theme myObject){
            Mybouton.setText(myObject.getLabel());
            Mybouton.setId(myObject.getId());


        }

        public Object getItem(int position) {
            return list.get(position);
        }
    }
}
