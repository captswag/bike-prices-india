package anjithsasindran.bikepricesindia;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;


public class HomeActivity extends AppCompatActivity {

    public static final String BIKE_NAMES = "https://bike-prices.firebaseio.com/bike-names";
    public RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    BikeDetails bikeDetails;
    HomeActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bike_companies);
        Firebase.setAndroidContext(this);
        Firebase reference = new Firebase(BIKE_NAMES);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bike_names);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, 2);
        } else {
            layoutManager = new GridLayoutManager(this, 3);
        }
        recyclerView = (RecyclerView)findViewById(R.id.bike_companies_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bikeDetails = new BikeDetails();
                int positionOfIndex = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Map<?, ?> bikeKeyValue = (Map<?, ?>) child.getValue();
                    bikeDetails.setBikeValues(bikeKeyValue, positionOfIndex);
                    positionOfIndex++;
                }
                adapter = new HomeActivityAdapter(bikeDetails);

                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bike_companies, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.search_companies));
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {

                try {
                    ((HomeActivityAdapter) recyclerView.getAdapter()).setFilter(query);
                } catch (NullPointerException e) {
                    Toast.makeText(getBaseContext(), "Wait for content to load", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return true;
    }
}
