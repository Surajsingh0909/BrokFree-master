package com.vm.brokfree.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.vm.brokfree.R;

import java.util.ArrayList;
import java.util.List;

public class CreatePostPageOne extends AppCompatActivity {
    MaterialButton NextPage;
    TextView textView;
    FrameLayout frameLayout;
    ChipGroup category,categoryType;
    ChipGroup categorySubTypeCommercial,categorySubTypeResidential;
    int selectedCategorySubType; // sekection category id

    List<Integer> ids = new ArrayList<>(); // to store  temparvary ids

    String selectedCategory = "flag";
    String selectedCategoryType = "flag";
    String selectedSubCategoryType ="flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_page_one);
        settingUpIds();
        // next button
        NextPage.setOnClickListener(v -> getSelectedCategory());
        //chaking and setting sub cattegary
        categoryType.setOnCheckedChangeListener((group, checkedId) -> setFrameLayout( checkedId));

    }
    private void settingUpIds() {
        //grrup
        category = findViewById(R.id.create_post_category_group);
        categoryType = findViewById(R.id.create_post_category_Type_group);
        categorySubTypeCommercial = findViewById(R.id.create_post_category_sub_type_commercial_group);
        categorySubTypeResidential = findViewById(R.id.create_post_category_sub_type_residential_group);
        //frame
        frameLayout = findViewById(R.id.create_post_frame_sub_category_type);
//button
        NextPage = findViewById(R.id.next_page_one);
        textView = findViewById(R.id.create_post_sub_category_text_view);
    }
    private void setFrameLayout(int id) {
       ////set frame
        Chip chip = categoryType.findViewById(id);

            if(id == R.id.create_post_category_Residential_type)
            {

                categorySubTypeResidential.setVisibility(View.VISIBLE);
                categorySubTypeCommercial.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                selectedCategorySubType = R.layout.residential_sub_category;
           }
        if(id == R.id.create_post_category_commercial_type)
        {
            categorySubTypeCommercial.setVisibility(View.VISIBLE);
            categorySubTypeResidential.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            selectedCategorySubType = R.layout.commercial_sub_category;
        }

        // sub category
    }
    private void getSelectedCategory() {
         selectedCategory = "flag";
         selectedCategoryType = "flag";
         selectedSubCategoryType ="flag";

        //selected category
        ids.clear();
        ids = category.getCheckedChipIds();
        for (Integer id:ids)
        {
            Chip chip = category.findViewById(id);
            if(chip.isChecked()) {
                //Toast.makeText(CreatePostPageOne.this,  chip.getText(), Toast.LENGTH_SHORT).show();
                selectedCategory = chip.getText().toString();
                break;
            }
        }
        // selected category type
        ids.clear();
        ids = categoryType.getCheckedChipIds();
        for (Integer id:ids)
        {

            Chip chip = categoryType.findViewById(id);
            if(chip.isChecked()) {
              //  Toast.makeText(CreatePostPageOne.this,  chip.getText(), Toast.LENGTH_SHORT).show();
                selectedCategoryType = chip.getText().toString();
                break;
            }
        }

        /// selected residential data
        if(selectedCategorySubType == R.layout.residential_sub_category)
        {
            ids.clear();
            ids = categorySubTypeResidential.getCheckedChipIds();
            for (Integer id:ids)
            {

                Chip chip = categorySubTypeResidential.findViewById(id);
                if(chip.isChecked()) {
                  //  Toast.makeText(CreatePostPageOne.this,  chip.getText(), Toast.LENGTH_SHORT).show();
                    selectedSubCategoryType = chip.getText().toString();
                    break;
                }
            }
        }
        /////selected commercial data
        if(selectedCategorySubType == R.layout.commercial_sub_category)
        {
            ids.clear();
            ids = categorySubTypeCommercial.getCheckedChipIds();
            for (Integer id:ids)
            {

                Chip chip = categorySubTypeCommercial.findViewById(id);
                if(chip.isChecked()) {
                  //  Toast.makeText(CreatePostPageOne.this,  chip.getText(), Toast.LENGTH_SHORT).show();
                    selectedSubCategoryType = chip.getText().toString();
                    break;
                }
            }
        }
        virifyAllCategory();


    }

    private void virifyAllCategory() {
        if (selectedCategory.equals( "flag"))
        {
            Toast.makeText(this, "Please selected the category", Toast.LENGTH_SHORT).show();

        }
        else if(selectedCategoryType.equals("flag"))
        {
            Toast.makeText(this, "Please selected the category Type", Toast.LENGTH_SHORT).show();

        }
        else if(selectedSubCategoryType.equals("flag"))
        {
            Toast.makeText(this, "Please selected the sub category type", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, selectedCategory +"\n"+selectedCategoryType+"\n"+selectedSubCategoryType, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreatePostPageOne.this, CreatePostPageTwo.class);
            intent.putExtra("SELECTED_CATEGORY",selectedCategory);
            intent.putExtra("SELECTED_CATEGORY_TYPE",selectedCategoryType);
            intent.putExtra("SELECTED_SUB_CATEGORY_TYPE",selectedSubCategoryType);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }



}