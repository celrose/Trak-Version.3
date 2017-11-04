package subai.trak2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SOSTab extends Fragment {
    private static final String TAG = "@string/sos_tag";
    private ImageButton roadAccident, emerStop, engineFail;
    private Bus bus;

    public void setBus(Bus bus){
        this.bus = bus;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sos_tab , container, false);

        final Animation animAlpha = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha);

        this.roadAccident = (ImageButton) v.findViewById(R.id.roadAcc);
        this.emerStop = (ImageButton) v.findViewById(R.id.emerStop);
        this.engineFail = (ImageButton) v.findViewById(R.id.engFail);

        roadAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                roadAccClick();
                sendBus();
            }
        });

        emerStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                emerStopClick();
                sendBus();
            }
        });

        engineFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                engineFailCLick();
                sendBus();
            }
        });

        return v;
    }

    public void roadAccClick() {
        bus.setStatus("RoadAccident");
    }

    public void emerStopClick() {
        bus.setStatus("Emergency Stop");
    }

    public void engineFailCLick() {
        bus.setStatus("Engine Failure");
    }

    public void sendBus(){
        DatabaseReference pushRef = FirebaseDatabase.getInstance().getReference().child("Bus").child(LoginActivity.getBusNumber());
        pushRef.setValue(bus);
    }

}
