package com.example.colocataire_app.BudgetPersonnelle;
import com.bumptech.glide.Glide;
import com.example.colocataire_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
public class  MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
         holder.Home.setText(model.getHome());
         holder.prix.setText(model.getPrix());

        Glide.with(holder.img.getContext())
             .load(model.getImg1())
             .placeholder(R.drawable.common_google_signin_btn_icon_dark)
             .circleCrop()
             .error(R.drawable.common_google_signin_btn_icon_dark_normal)
             .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView Home,prix;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

                img=(CircleImageView)itemView.findViewById(R.id.img1);
                Home=(TextView)itemView.findViewById(R.id.home);
                prix=(TextView)itemView.findViewById(R.id.prix);


            }
        }
    }
