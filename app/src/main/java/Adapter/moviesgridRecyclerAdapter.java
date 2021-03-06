package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.fragmentsampleapp.R;
import com.project.fragmentsampleapp.movieDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.MovieCardsData;
import Util.Constants;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class moviesgridRecyclerAdapter extends RecyclerView.Adapter<moviesgridRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<MovieCardsData> cardsDataList;
    public moviesgridRecyclerAdapter(Context context, List<MovieCardsData> cardsDataList) {
        this.context = context;
        this.cardsDataList = cardsDataList;
    }

    @NonNull
    @Override
    public moviesgridRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieCardsData cardsData=cardsDataList.get(position);
        holder.title.setText(cardsData.getMovieTitle());
        holder.rating.setText(cardsData.getRating());
        String posterUrl= Constants.imagePath+cardsData.getPoster();
        Picasso.
                with(context)
                .load(posterUrl)
                .noFade()
                .fit()
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return cardsDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        TextView title,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=(ImageView)itemView.findViewById(R.id.movieCover2);
            title=(TextView)itemView.findViewById(R.id.movieTitle2);
            rating=(TextView)itemView.findViewById(R.id.Rating2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, movieDetails.class);
            MovieCardsData data=cardsDataList.get(getAdapterPosition());
            intent.putExtra("imdbId",data.getImdbId());
            intent.putExtra("choice","moviedata");
            intent.putExtra("ch",2);
            context.startActivity(intent);
        }
    }
}
