package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    public static PlaceholderFragment newInstance() {
        return new PlaceholderFragment();
    }
    private RecyclerView recycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view =  inflater.inflate(R.layout.fragment_placeholder, container, false);
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(new PlaceholderFragment.ItemAdapter());
        //recycler.setVisibility(View.INVISIBLE);
        recycler.setAlpha(0);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(recycler,"Alpha",0, (float) 1);
                animator1.setDuration(200);
                animator1.setRepeatCount(0);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(getView().findViewById(R.id.animation_view),"Alpha",1, (float) 0);
                animator2.setDuration(200);
                animator2.setRepeatCount(0);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1,animator2);
                animatorSet.start();

                LottieAnimationView animation = getView().findViewById(R.id.animation_view);
                animation.cancelAnimation();
            }
        }, 5000);
    }

    private static class NumItemHolder extends RecyclerView.ViewHolder {
        public TextView tv_item;
        public NumItemHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
        }
    }

    private static class ItemAdapter extends RecyclerView.Adapter<PlaceholderFragment.NumItemHolder> {
        private int TOTAL_SIZE=10;
        @NonNull
        @Override
        public PlaceholderFragment.NumItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new PlaceholderFragment.NumItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_friends, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PlaceholderFragment.NumItemHolder colorViewHolder, int i) {
            colorViewHolder.tv_item.setText(String.format("Item %d", i));
        }

        @Override
        public int getItemCount() {
            return TOTAL_SIZE;
        }
    }
}
