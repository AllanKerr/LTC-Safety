package c371g2.ltc_safety.a_main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import c371g2.ltc_safety.R;
import c371g2.ltc_safety.a_detail.ConcernDetailActivity;
import c371g2.ltc_safety.a_new.NewConcernActivity;

/**
 * This is the main activity, it contains:
 * - A button that brings the user to the NewConcernActivity
 * - A list of all previously submitted concerns that, when pressed, brings the user to the ConcernDetailActivity.
 *
 * Activity: ~ View-Controller
 *
 * @Invariants
 * - The number of rows in the ListView is equal to MainViewModel.concernList.size()
 * @HistoryProperties none
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel.initialize(getBaseContext());
        assert(MainViewModel.getSortedConcernList() != null);
        populateConcernsList();

        Button newConcernButton = (Button) findViewById(R.id.new_concern_button);
        setNewConcernButtonListener(newConcernButton);
        assert(newConcernButton.hasOnClickListeners());
    }

    /**
     * Sets the onClickListener for the newConcernButton.
     * @preconditions newConcernButton is not null.
     * @modifies An onClickListener is assigned to newConcernButton.
     * @param newConcernButton The button that brings the user to the NewConcernActivity.
     */
    private void setNewConcernButtonListener(@NonNull Button newConcernButton) {
        newConcernButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewConcernActivity();
            }
        });
    }

    /**
     * Fills the ListView in MainActivity with all previously submitted concerns, if any exist.
     * If no previous concerns exist then nothing happens.
     * @preconditions MainViewModel.concernList was initialized
     * @modifies The contents of the ListView in MainActivity are populated by the elements in
     * MainViewModel.concernList
     */
    private void populateConcernsList() {
        ConcernListAdapter adapter = new ConcernListAdapter(
                this,
                R.layout.concern_list_item,
                MainViewModel.getSortedConcernList()
        );
        ListView concernList = (ListView) findViewById(R.id.concern_listView);
        assert(concernList != null);
        concernList.setAdapter(adapter);
        concernList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openConcernDetailActivity(position);
                    }
                }
        );
        assert(concernList.hasOnClickListeners());
    }

    /**
     * Switches the activity to the new concern activity. The new concern activity contains fields
     * which when filled specify a concern. The specified concern can then be submitted.
     * @preconditions none
     * @modifies The active activity changes from MainActivity to NewConcernActivity.
     */
    private void openNewConcernActivity() {
        Intent newConcernIntent = new Intent(MainActivity.this, NewConcernActivity.class);
        assert(newConcernIntent != null);
        MainActivity.this.startActivity(newConcernIntent);
    }

    /**
     * Switches the activity to ConcernDetailActivity. The activity contains fields which are filled
     * with data from a Concern object.
     * @preconditions none
     * @modifies The active activity changes from MainActivity to ConcernDetailActivity.
     * @param index The row of the listView pressed and the storage index of the concern whose data
     *              was displayed on that row.
     */
    private void openConcernDetailActivity(int index) {
        Intent concernDetailIntent = new Intent(MainActivity.this, ConcernDetailActivity.class);
        assert(concernDetailIntent != null);
        concernDetailIntent.putExtra("concern-index",index);
        assert(concernDetailIntent.getExtras().getInt("concern-index")==index);
        MainActivity.this.startActivity(concernDetailIntent);
    }
}