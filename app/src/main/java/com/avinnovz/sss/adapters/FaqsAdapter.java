package com.avinnovz.sss.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.helpers.ViewHelper;
import com.avinnovz.sss.models.Faq;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayan on 8/29/2016.
 */
public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.ViewHolder> {

    Activity mActivity;
    List<Faq> faqs;

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rootView) LinearLayout rootView;
        @BindView(R.id.tvFaqNumber) TextView tvFaqNumber;
        @BindView(R.id.tvFaqQuestion) TextView tvFaqQuestion;
        @BindView(R.id.tvFaqAnswer) TextView tvFaqAnswer;
        @BindView(R.id.lltFaqAnswer) LinearLayout lltFaqAnswer;
        @BindView(R.id.ivExpandcollapse) ImageView ivExpandcollapse;

        @BindColor(R.color.colorPrimaryDark)
        int colorPrimaryDark;

        boolean isExpanded = false;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public FaqsAdapter(Activity activity, List<Faq> faqs) {
        this.mActivity = activity;
        this.faqs = faqs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_faq, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder != null) {

            //SET NUMBER
            holder.tvFaqNumber.setText(String.valueOf(position + 1) + ".");

            //SET QUESTION
            holder.tvFaqQuestion.setText(faqs.get(position).getQuestion());

            //SET ANSWER
            Spanned answer;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                answer = Html.fromHtml(faqs.get(position).getAnswer(),Html.FROM_HTML_MODE_LEGACY);
            } else {
                answer = Html.fromHtml(faqs.get(position).getAnswer());
            }
            holder.tvFaqAnswer.setText(answer);

            //EXPAND VIEW
            holder.rootView.setOnClickListener(view -> {
                if (holder.isExpanded) {
                    ViewHelper.collapse(holder.lltFaqAnswer);
                    holder.ivExpandcollapse.setImageResource(R.drawable.ic_expand_more);
                    holder.isExpanded = false;
                } else {
                    ViewHelper.expand(holder.lltFaqAnswer);
                    holder.ivExpandcollapse.setImageResource(R.drawable.ic_expand_less);
                    holder.isExpanded = true;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }
}
