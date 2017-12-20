package passit.gania.gimmesome.passit;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    PopupWindow popup;
    String pName;
    String pChances;
    String vChances;
    String hitMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popup = new PopupWindow(this);

        try {
            Intent myIntent = getIntent();
            pName = myIntent.getStringExtra("pName");
            pChances = myIntent.getStringExtra("pChances");
            vChances= myIntent.getStringExtra("vChances");
            hitMax= myIntent.getStringExtra("hitMax");
        } catch(Exception e) {
            e.printStackTrace();
        }

        clickHearer();
        loadExistingButtons();
        addPieceButton(pName);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.addPiece:
                startActivity(new Intent(this, AddPiece.class));
                return true;
            case R.id.removePiece:
                startActivity(new Intent(this, RemovePiece.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadExistingButtons() {
        String filePath = this.getFilesDir() + "/" + "pieces.txt";
        File root = new File(filePath);
        if (root.exists()) {
            StringBuilder stats = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(root));
                String line;

                while ((line = br.readLine()) != null) {
                    stats.append(line);
                    stats.append('\n');
                }
                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }

        }
    }

    private void clickHearer() {
    }

    @Override
    public void onClick(View v) {
//        if(v.getId()==R.id.button_blunt) {
//            recieveHits();
//        } else if (v.getTag()=="popup_blunt") {
//            popup.dismiss();
//        }
    }

    public void addPieceButton(String pieceName) {
        Button button = new Button(MainActivity.this);
        button.setText(pieceName);
        button.setTag(pieceName);

        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_main);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(button, lp);
    }

//    private void recieveHits() {
//        Button btnPiece = (Button) findViewById(R.id.button_blunt);
//        TextView popupText = new TextView(this);
//        Button insidePopupButton = new Button(this);
//        LinearLayout layoutOfPopup = new LinearLayout(this);
//        String person = null;
//
//        int hits[] = new int[4];
//        for(int i=0; i<4; i++) {
//            hits[i] = i+1;
//        }
//        double dblRetrieved = Math.random()*hits.length;
//        int retrieved = (int) dblRetrieved;
//        String hitsToTake = Integer.toString(hits[retrieved]);
//
//        int who[] = new int[2];
//        for(int i=0; i<2; i++) {
//            who[i] = i+1;
//        }
//        double temp = Math.random()*who.length;
//        int peep = who[(int) temp];
//
//        if (peep==1) {
//            person = "You";
//        }else if (peep==2) {
//            person = "Other person";
//        }
//
//        insidePopupButton.setText("OK");
//        popupText.setText(person + " take " + hitsToTake + " hits from Blunt.");
//        popupText.setPadding(0, 0, 0, 20);
//        layoutOfPopup.setOrientation(LinearLayout.HORIZONTAL);
//        layoutOfPopup.addView(popupText);
//        layoutOfPopup.addView(insidePopupButton);
//        insidePopupButton.setOnClickListener(this);
//        insidePopupButton.setTag("popup_blunt");
//        popup = new PopupWindow(layoutOfPopup, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
//        popup.setContentView(layoutOfPopup);
//        popup.showAsDropDown(btnPiece, 0, 0);
//
//    }
}
