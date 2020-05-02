package tn.esprit.examengl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tn.esprit.examengl.entity.Recette;

public class RecetteAdapter extends RecyclerView.Adapter<RecetteAdapter.RecetteHolder> {

    private Context mContext;
    private List<Recette> recetteList;

    public RecetteAdapter(Context mContext, List<Recette> recetteList) {
        this.mContext = mContext;
        this.recetteList = recetteList;
    }

    @NonNull
    @Override
    public RecetteAdapter.RecetteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_row, parent, false);
        return new RecetteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetteAdapter.RecetteHolder holder, int position) {
        final Recette singleItem = recetteList.get(position);

        holder.nom.setText(singleItem.getNom());
        holder.description.setText(singleItem.getDescription());

        if(singleItem.getMarque().equals("samsung")){
            holder.imageRecette.setImageResource(R.drawable.ic_samsung);
        }else if(singleItem.getMarque().equals("apple")){
            holder.imageRecette.setImageResource(R.drawable.ic_apple);
        }else if(singleItem.getMarque().equals("huawei")){
            holder.imageRecette.setImageResource(R.drawable.ic_huawei);
        }

        holder.imageRecette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AfficherRecetteActivity.class);
                intent.putExtra("ID_PROD",singleItem.getUid());
                mContext.startActivity(intent);     
            }
        });
    }

    @Override
    public int getItemCount() {
        return recetteList != null ? recetteList.size() : 0;
    }

    public class RecetteHolder extends RecyclerView.ViewHolder {

        private TextView nom, description;
        private ImageView imageRecette;

        public RecetteHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom);
            description = itemView.findViewById(R.id.description);
            imageRecette = itemView.findViewById(R.id.imageRecette);

        }
    }
}
