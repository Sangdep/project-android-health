package com.example.app_selfcare.Data;

import com.example.app_selfcare.R;
import com.example.app_selfcare.Data.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeData {

    public static List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        // Món ăn sáng
        recipes.add(new Recipe(
                1,
                "Bánh mì trứng ốp la",
                "Bữa sáng nhanh gọn, đầy đủ dinh dưỡng với trứng, bánh mì và rau xanh",
                R.drawable.ic_salad,
                15,
                350,
                "Dễ",
                "BREAKFAST"
        ));

        recipes.add(new Recipe(
                2,
                "Yến mạch chuối",
                "Giàu chất xơ, tốt cho tim mạch và hệ tiêu hóa",
                R.drawable.ic_cupcake,
                10,
                280,
                "Dễ",
                "BREAKFAST"
        ));

        recipes.add(new Recipe(
                3,
                "Phở bò",
                "Món phở truyền thống Việt Nam, thơm ngon bổ dưỡng",
                R.drawable.ic_salad,
                30,
                450,
                "Trung bình",
                "BREAKFAST"
        ));

        recipes.add(new Recipe(
                4,
                "Smoothie bowl",
                "Bát smoothie tươi mát với trái cây và hạt dinh dưỡng",
                R.drawable.ic_cupcake,
                10,
                320,
                "Dễ",
                "BREAKFAST"
        ));

        // Món ăn trưa
        recipes.add(new Recipe(
                5,
                "Cơm gà luộc",
                "Món ăn trưa nhẹ nhàng, dễ tiêu hóa với thịt gà mềm",
                R.drawable.ic_steak_tomato,
                35,
                480,
                "Trung bình",
                "LUNCH"
        ));

        recipes.add(new Recipe(
                6,
                "Salad cá ngừ",
                "Tươi mát, giàu protein và omega-3 tốt cho sức khỏe",
                R.drawable.ic_salad,
                15,
                320,
                "Dễ",
                "LUNCH"
        ));

        recipes.add(new Recipe(
                7,
                "Bún chả Hà Nội",
                "Món bún chả đặc trưng của Hà Nội với thịt nướng thơm",
                R.drawable.ic_steak_tomato,
                40,
                520,
                "Trung bình",
                "LUNCH"
        ));

        recipes.add(new Recipe(
                8,
                "Cơm chiên dương châu",
                "Cơm chiên đầy màu sắc với tôm, trứng và rau củ",
                R.drawable.ic_cupcake,
                25,
                450,
                "Trung bình",
                "LUNCH"
        ));

        // Món ăn tối
        recipes.add(new Recipe(
                9,
                "Canh chua cá",
                "Thanh mát, kích thích vị giác với vị chua dịu",
                R.drawable.ic_salad,
                35,
                280,
                "Trung bình",
                "DINNER"
        ));

        recipes.add(new Recipe(
                10,
                "Thịt kho tàu",
                "Món ăn truyền thống Việt Nam với thịt kho đậm đà",
                R.drawable.ic_steak_tomato,
                60,
                550,
                "Trung bình",
                "DINNER"
        ));

        recipes.add(new Recipe(
                11,
                "Cá hồi nướng",
                "Cá hồi giàu omega-3, nướng vàng ươm thơm ngon",
                R.drawable.ic_steak_tomato,
                30,
                380,
                "Dễ",
                "DINNER"
        ));

        recipes.add(new Recipe(
                12,
                "Lẩu thái",
                "Lẩu chua cay đặc trưng Thái Lan với hải sản tươi",
                R.drawable.ic_salad,
                45,
                420,
                "Khó",
                "DINNER"
        ));

        recipes.add(new Recipe(
                13,
                "Bò xào rau củ",
                "Thịt bò mềm xào cùng rau củ tươi ngon bổ dưỡng",
                R.drawable.ic_steak_tomato,
                25,
                460,
                "Trung bình",
                "DINNER"
        ));

        recipes.add(new Recipe(
                14,
                "Súp bí đỏ",
                "Súp mịn màng, ngọt thanh từ bí đỏ tự nhiên",
                R.drawable.ic_cupcake,
                30,
                220,
                "Dễ",
                "DINNER"
        ));

        return recipes;
    }

    public static List<Recipe> getRecipesByMealType(String mealType) {
        if ("ALL".equals(mealType)) {
            return getAllRecipes();
        }

        List<Recipe> allRecipes = getAllRecipes();
        List<Recipe> filtered = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            if (mealType.equals(recipe.getMealType())) {
                filtered.add(recipe);
            }
        }

        return filtered;
    }
}