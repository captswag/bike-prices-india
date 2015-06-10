package anjithsasindran.bikepricesindia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.squareup.picasso.Picasso;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class HomeActivityAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    BikeDetails bikeDetails;
    BikeDetails afterQueryBikeDetails;
    public Context context;

    public HomeActivityAdapter(BikeDetails bikeDetails) {
        this.bikeDetails = bikeDetails;
        this.afterQueryBikeDetails = bikeDetails;
    }

    @Override
    public int getItemCount() {
        return afterQueryBikeDetails.getLength();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_bike_companies, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {
        holder.textView.setText(afterQueryBikeDetails.getBikeCompanyByIndex(position));

        ViewTreeObserver widthListener = holder.imageView.getViewTreeObserver();
        widthListener.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                holder.imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                holder.imageView.requestLayout();
                holder.imageView.getLayoutParams().height = holder.imageView.getMeasuredWidth();
                return true;
            }
        });

        Picasso.with(context).load(afterQueryBikeDetails.getBikeUrlByIndex(position))
                .into(holder.imageView);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bikeNames = new Intent(context, BikeNamesActivity.class);
                bikeNames.putExtra("bike_name", afterQueryBikeDetails.getBikeCompanyByIndex(position));
                bikeNames.putExtra("position", bikeDetails.getPosition(afterQueryBikeDetails.getBikeCompanyByIndex(position))+"");
                context.startActivity(bikeNames);
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(HomeViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    public void setFilter(String query) {
        afterQueryBikeDetails = new BikeDetails();
        query = query.toLowerCase();
        for (int i = 0 ; i < bikeDetails.getLength() ; i++ ) {
            if (bikeDetails.getBikeCompanyByIndex(i).toLowerCase().contains(query)) {
                afterQueryBikeDetails.setBikeValues(
                        bikeDetails.getBikeCompanyByIndex(i), bikeDetails.getBikeUrlByIndex(i)
                );
            }
        }
        notifyDataSetChanged();
    }
}
