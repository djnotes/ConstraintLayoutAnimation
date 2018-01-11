package mehdi.constimation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button mButton; //Start button
    private ConstraintSet mConstraintSet;
    private ConstraintLayout mConstraintLayout; //Our root layout
    private Context mContext = this;
    private static final int [] mLayouts = {R.layout.summary, R.layout.detail};
    public static int mArrayIndex = 0; //This keeps track of selected layout
    private TextView mTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        //Find the root constraint layout
        mConstraintLayout = findViewById(R.id.constraint_layout);

        mButton = findViewById(R.id.start);

        mTitleTV = findViewById(R.id.title);

        //Load a ConstraintSet and load it from our second layout
        mConstraintSet = new ConstraintSet();



        mButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //change layouts
                mArrayIndex = mArrayIndex >= mLayouts.length - 1 ? 0 : mArrayIndex + 1;
                mConstraintSet.clone(mContext, mLayouts[mArrayIndex]);

                mConstraintSet.applyTo(mConstraintLayout);

                //Use a different interpolator here
                Transition transition = new ChangeBounds(mContext, null);

                //Add tension to the animation
                transition.setInterpolator( new AnticipateOvershootInterpolator(1.0f));
                //Show to animation
                TransitionManager.beginDelayedTransition(mConstraintLayout, transition);

            }
        });


    }

}
