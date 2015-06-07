package anjithsasindran.bikepricesindia;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeNamesActivity extends AppCompatActivity {

    public String bike_names = "https://bike-prices.firebaseio.com/bike-details/";
    RecyclerView recyclerView;
    BikeNames bikeNames;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bike_names);
        setTitle(getIntent().getStringExtra("bike_name"));

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final int height = ((getWindowManager().getDefaultDisplay().getWidth() -
                (Math.round(getResources().getDisplayMetrics().density) * 2 * 2)) * 3)/4;

        bike_names += getIntent().getStringExtra("position")+"/"
                +getIntent().getStringExtra("bike_name");
        Firebase reference = new Firebase(bike_names);

        recyclerView = (RecyclerView) findViewById(R.id.bikename_recycler);
        recyclerView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(this);
        } else {
            layoutManager = new GridLayoutManager(this, 2);
        }
        recyclerView.setLayoutManager(layoutManager);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bikeNames = new BikeNames();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<?, ?> bikeKeyPair = (Map<?, ?>) child.getValue();
                    bikeNames.setBikeValues(bikeKeyPair, child.getKey());
                }

                BikeNamesAdapter adapter = new BikeNamesAdapter(bikeNames, height);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home :
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
