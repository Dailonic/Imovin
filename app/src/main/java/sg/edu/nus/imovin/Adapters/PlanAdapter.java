package sg.edu.nus.imovin.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import java.util.List;

import sg.edu.nus.imovin.Objects.PlanDataCategory;
import sg.edu.nus.imovin.R;
import sg.edu.nus.imovin.Retrofit.Object.PlanData;
import sg.edu.nus.imovin.System.ImovinApplication;

public class PlanAdapter extends ExpandableRecyclerAdapter<PlanDataCategory, PlanData, PlanAdapter.PlanDataCategoryViewHolder, PlanAdapter.PlanDataViewHolder> {
    private LayoutInflater mInflater;

    public PlanAdapter(Context context, @NonNull List<PlanDataCategory> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
    }

    class PlanDataCategoryViewHolder extends ParentViewHolder {

        private TextView plan_category_title;

        public PlanDataCategoryViewHolder(View itemView) {
            super(itemView);
            plan_category_title = itemView.findViewById(R.id.plan_category_title);
        }

        public void bind(PlanDataCategory planDataCategory) {
            plan_category_title.setText(planDataCategory.getCategoryTitle());
        }
    }

    class PlanDataViewHolder extends ChildViewHolder {

        private TextView plan_title;

        public PlanDataViewHolder(View itemView) {
            super(itemView);
            plan_title = itemView.findViewById(R.id.plan_title);
        }

        public void bind(PlanData planData) {
            plan_title.setText(planData.getName());
        }
    }

    @NonNull
    @Override
    public PlanDataCategoryViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View parentView = mInflater.inflate(R.layout.item_plan_category, parentViewGroup, false);

        return new PlanDataCategoryViewHolder(parentView);
    }

    @NonNull
    @Override
    public PlanDataViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View childView = mInflater.inflate(R.layout.item_plan, childViewGroup, false);

        return new PlanDataViewHolder(childView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull PlanDataCategoryViewHolder parentViewHolder, int parentPosition, @NonNull PlanDataCategory parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull PlanDataViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull final PlanData child) {
        childViewHolder.bind(child);

        childViewHolder.plan_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImovinApplication.getInstance(), child.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
