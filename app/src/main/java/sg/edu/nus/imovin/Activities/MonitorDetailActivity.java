package sg.edu.nus.imovin.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sg.edu.nus.imovin.Objects.Goal;
import sg.edu.nus.imovin.R;

public class MonitorDetailActivity extends Activity implements View.OnClickListener {

    public static final String MONTH_YEAR = "MONTH_YEAR";
    public static final String GOAL = "GOAL";

    @BindView(R.id.date_display) TextView date_display;
    @BindView(R.id.steps_value) TextView steps_value;
    @BindView(R.id.plan_steps_bar) ProgressBar plan_steps_bar;
    @BindView(R.id.steps_left) TextView steps_left;
    @BindView(R.id.button_close) Button button_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_detail);

        LinkUIById();
        SetFunction();
        Init();
    }

    private void LinkUIById(){
        ButterKnife.bind(this);
    }

    private void SetFunction(){
        button_close.setOnClickListener(this);
    }

    private void Init(){
        String month_year = getIntent().getStringExtra(MONTH_YEAR);
        Goal goal = (Goal) getIntent().getSerializableExtra(GOAL);

        date_display.setText(goal.getDate() + " " + month_year);
        steps_value.setText(String.valueOf(goal.getSteps()));
        plan_steps_bar.setProgress(100 * goal.getSteps() / goal.getGoal());
        steps_left.setText("Steps left : " + (goal.getGoal() - goal.getSteps()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_close:
                finish();
                break;
        }
    }
}
