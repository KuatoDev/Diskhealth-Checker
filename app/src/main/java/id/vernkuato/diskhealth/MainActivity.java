package id.vernkuato.diskhealth;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView result,resultsummary,author,notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.results);
        resultsummary = findViewById(R.id.result_summary);
        author = findViewById(R.id.author);
        author.setText("Created by\nVern Kuato\nt.me/vern_kuato");
        notes = findViewById(R.id.notes);
        notes.setText("eMMC Health calculations is introduced since eMMC 5.0. I am calculating emmc life_time to check your eMMC Health\n\nsource : https://developer.toradex.com/knowledge-base/emmc-linux ");
        Disk();
    }

    private void Disk() {
        if (GetDiskHealthl().toString().contains("0x01")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("90-100 % This phone ia brand new???");
            result.setTextColor(Color.BLUE);
            resultsummary.setTextColor(Color.BLUE);
        }

        if (GetDiskHealthl().toString().contains("0x02")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("80-90 % Super Healthy");
            result.setTextColor(Color.BLUE);
            resultsummary.setTextColor(Color.BLUE);
        }

        if (GetDiskHealthl().toString().contains("0x03")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("70-80 % Super Healthy");
            result.setTextColor(Color.BLUE);
            resultsummary.setTextColor(Color.BLUE);
        }

        if (GetDiskHealthl().toString().contains("0x04")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("60-70 % Disk health is good");
            result.setTextColor(Color.GREEN);
            resultsummary.setTextColor(Color.GREEN);
        }

        if (GetDiskHealthl().toString().contains("0x05")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("50-60 % Disk health is good");
            result.setTextColor(Color.GREEN);
            resultsummary.setTextColor(Color.GREEN);
        }

        if (GetDiskHealthl().toString().contains("0x06")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("40-50 % Disk health is average");
            result.setTextColor(Color.GREEN);
            resultsummary.setTextColor(Color.GREEN);
        }

        if (GetDiskHealthl().toString().contains("0x07")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("30-40 % Disk health is bad");
            result.setTextColor(Color.YELLOW);
            resultsummary.setTextColor(Color.YELLOW);
        }

        if (GetDiskHealthl().toString().contains("0x08")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("20-30 % Disk health is realy bad");
            result.setTextColor(Color.YELLOW);
            resultsummary.setTextColor(Color.YELLOW);
        }

        if (GetDiskHealthl().toString().contains("0x09")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("10-20 % Disk health is nearly dead");
            result.setTextColor(Color.RED);
            resultsummary.setTextColor(Color.RED);
        }

        if (GetDiskHealthl().toString().contains("0x0b")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("0-10 % Disk health is DEAD SOON");
            result.setTextColor(Color.RED);
            resultsummary.setTextColor(Color.RED);
        }

        if (GetDiskHealthl().toString().contains("0x0b")) {
            result.setText(GetDiskHealthl().toString());
            resultsummary.setText("YOUR DISK IS DEAD ALREDY!");
            result.setTextColor(Color.RED);
            resultsummary.setTextColor(Color.RED);
        } 

        if (GetDiskHealthl().isEmpty()) {
            result.setText("Cannot Read Disk Health");
            resultsummary.setText("Unreadable healthy disk");
            result.setTextColor(Color.RED);
            resultsummary.setTextColor(Color.RED);
        } 
    }

    public String GetDiskHealthl() {
        try {
            Process process = Runtime.getRuntime()
                .exec("su -c cat sys/class/mmc_host/mmc0/mmc0:0001/life_time");
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }
            return log.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
