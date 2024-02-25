package com.vm.brokfree.FirbaseModule;

public class CategoryInformation {
    String category;
    String category_type;
    String sub_category_type;

    public CategoryInformation() {
    }

    public CategoryInformation(String category, String categoryType, String subCategoryType) {
        this.category = category;
        this.category_type = categoryType;
        this.sub_category_type = subCategoryType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public String getSub_category_type() {
        return sub_category_type;
    }

    public void setSub_category_type(String sub_category_type) {
        this.sub_category_type = sub_category_type;
    }
}
