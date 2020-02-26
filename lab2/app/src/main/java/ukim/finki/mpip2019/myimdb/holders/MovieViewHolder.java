package ukim.finki.mpip2019.myimdb.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import ukim.finki.mpip2019.myimdb.R;
import ukim.finki.mpip2019.myimdb.models.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvYear;
    private View parent;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
        this.tvYear = (TextView) itemView.findViewById(R.id.tvYear);
        this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        this.parent = itemView;
    }

    public View getParent() {
        return parent;
    }

    public void bind(final Movie entity) {
        Glide.with(itemView.getContext())
                .load(entity.getImgUrl())
                .centerCrop()
//                .placeholder(R.drawable.ic_launcher_background)
                .crossFade()
                .into(ivPoster);
        tvTitle.setText(entity.getTitle());
        tvYear.setText(entity.getYear());
    }

}