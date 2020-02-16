package com.example.carribeandeveloping.ui.Container;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.carribeandeveloping.MainActivity;
import com.example.carribeandeveloping.R;

public class ContainerFragment extends Fragment {
    private Button addContainer;
    private LinearLayout linearLayout;
    private ContainerViewModel containerViewModel;
    private ScrollView scroll;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        containerViewModel =
                ViewModelProviders.of(this).get(ContainerViewModel.class);

        View root = inflater.inflate(R.layout.fragment_container, container, false);
        linearLayout = root.findViewById(R.id.spaceBox);
        scroll = root.findViewById(R.id.scrollview1);

        addContainer = (Button) root.findViewById(R.id.btn_addContainer);
        //final TextView textView = root.findViewById(R.id.text_home);
        containerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
                // addContainer
            }
        });

        calculate(scroll, addContainer);
        return root;
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    private void calculate(ScrollView scroll,Button button){
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (280 * scale + 0.5f);
        DisplayMetrics metrics = new DisplayMetrics();
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        int screenH= size.y;
        int height = metrics.heightPixels;
        button.getHeight();
        ViewGroup.LayoutParams params = scroll.getLayoutParams();
        params.height= screenH-getStatusBarHeight()-pixels;
    }
    private void cargaHistorial(){
        int last = 0;
        for(int i = 0;i<15;i++){
            int textId = View.generateViewId();
            int nameId = View.generateViewId();
            int dateId = View.generateViewId();
            int costumerID = View.generateViewId();
            int butId = View.generateViewId();
            int butDesactivate = View.generateViewId(); //Boton de desactivar

            ConstraintLayout lay = new ConstraintLayout(getActivity());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            if(i!=0)
                params.addRule(RelativeLayout.BELOW,last);
            lay.setLayoutParams(params);
            ViewGroup.LayoutParams rlp = lay.getLayoutParams();
            last = View.generateViewId();
            lay.setId(last);
            rlp.width = ViewGroup.LayoutParams.MATCH_PARENT;
           // rlp.height = 280;
            //lay.setBackground(ContextCompat.getDrawable(this,R.drawable.small_lay_custom));

            TextView title = new TextView(getActivity());
            title.setId(textId);
            title.setText("Nombre del pedido");
            //Typeface typeface = ResourcesCompat.getFont(this, R.font.pop_med);
            //title.setTypeface(typeface);
            //title.setTextColor(this.getResources().getColor(R.color.offColor));
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            lay.addView(title);

            TextView name = new TextView(getActivity());
            name.setId(nameId);
            name.setText("Nombre del transportista");
            //Typeface typeface2 = ResourcesCompat.getFont(this, R.font.pop_light);
            //name.setTypeface(typeface2);
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            lay.addView(name);

            TextView dateW = new TextView(getActivity());
            dateW.setId(dateId);
            dateW.setText("Fecha del pedido");
            //dateW.setTypeface(typeface2);
            dateW.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            lay.addView(dateW);
            //Costumer
            TextView costumer = new TextView(getActivity());
            costumer.setId(costumerID);
            costumer.setText("Costumer");
            costumer.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            lay.addView(costumer);

            Button butVer = new Button(getActivity());
            butVer.setId(butId);
            butVer.setText("Ver\ndetalles");
            butVer.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
            //butVer.setBackground(ContextCompat.getDrawable(this,R.drawable.small_lay_custom));
            butVer.setWidth(200);
            butVer.setHeight(160);
            //butVer.setTextColor(this.getResources().getColor(R.color.lightColor));
            butVer.setAllCaps(false);
            //butVer.setTypeface(typeface);
            butVer.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            butVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //openInfo();
                }
            });
            lay.addView(butVer);

            //Desactivate boton.
            Button butOff = new Button(getActivity());
            butOff.setId(butDesactivate);
            butOff.setText("Delete");
            butOff.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
            butOff.setWidth(200);
            butOff.setHeight(160);
            butOff.setAllCaps(false);
            butOff.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            butOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //openInfo();
                }
            });
            lay.addView(butOff);

            ConstraintSet cs = new ConstraintSet();
            cs.clone(lay);
            cs.connect(textId,ConstraintSet.TOP,lay.getId(),ConstraintSet.TOP,15);
            cs.connect(textId, ConstraintSet.LEFT,lay.getId(),ConstraintSet.LEFT,15);
            cs.connect(nameId,ConstraintSet.TOP,textId,ConstraintSet.BOTTOM,10);
            cs.connect(nameId,ConstraintSet.LEFT,lay.getId(),ConstraintSet.LEFT,15);
            cs.connect(dateId,ConstraintSet.TOP,nameId,ConstraintSet.BOTTOM,10);
            cs.connect(dateId,ConstraintSet.LEFT,lay.getId(),ConstraintSet.LEFT,15);

            cs.connect(costumerID,ConstraintSet.TOP,dateId,ConstraintSet.BOTTOM,10);
            cs.connect(costumerID,ConstraintSet.LEFT,lay.getId(),ConstraintSet.LEFT,15);

            cs.connect(butId,ConstraintSet.TOP,lay.getId(),ConstraintSet.TOP,50);
            cs.connect(butId,ConstraintSet.RIGHT,lay.getId(),ConstraintSet.RIGHT,15);

            cs.connect(butDesactivate,ConstraintSet.TOP,lay.getId(),ConstraintSet.TOP,50);
            cs.connect(butDesactivate,ConstraintSet.RIGHT,butId,ConstraintSet.RIGHT,15);

            cs.applyTo(lay);

            linearLayout.addView(lay);// aqui va mi linear layout
        }
    }
}