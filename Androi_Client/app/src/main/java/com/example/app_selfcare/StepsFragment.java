package com.example.app_selfcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StepsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_steps);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Hardcode dữ liệu các bước từ hình
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("1", "Chủn bị gà: Rửa sạch với nước, cắt dọc theo chiều dọc, ướp với muối, tiêu, bột ngọt trong 10 phút."));
        steps.add(new Step("2", "Chiên gà: Cho dầu vào chảo đun nóng với 4-5 phút, cho gà vào chiên mỗi mặt khoảng 5-7 phút, cho ra đĩa."));
        steps.add(new Step("3", "Chiên gà: Cho dầu vào chảo đun nóng với 4-5 phút, cho gà vào chiên mỗi mặt khoảng 5-7 phút, cho ra đĩa.")); // Lặp để demo

        StepsAdapter adapter = new StepsAdapter(steps);
        recyclerView.setAdapter(adapter);

        return view;
    }

    // Model class đơn giản cho bước
    public static class Step {
        String number;
        String description;

        public Step(String number, String description) {
            this.number = number;
            this.description = description;
        }
    }
}