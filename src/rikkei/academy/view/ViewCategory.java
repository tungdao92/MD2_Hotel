package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.model.Category;

import java.util.List;

public class ViewCategory {
    private CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.showListCategory();

    public void menuCategory() {
        System.out.println("MENU Category");
        System.out.println("1: Create category");
        System.out.println("2: Show list category");
        System.out.println("3: Detail category");
        System.out.println("4: Edit category");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                createCategory();
                break;
            case 2:
                showListCategory();
                break;
            case 3:
                detailCategory();
                break;
            case 4:
                editCategory();
                break;
        }
    }

    private void detailCategory() {
        System.out.println("Enter id category");
        int idDetail = Integer.parseInt(Config.scanner().nextLine());
        if (categoryController.detailCategory(idDetail) == null){
            System.out.println(" ID category not existed");
        } else {
            Category category = categoryController.detailCategory(idDetail);
            System.out.println(category.getId() + " " + category.getNameCategory() + " " + category.getUser().getUsername());
        }
        System.out.println("Enter 'back' to back Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ViewCategory().menuCategory();
        }
    }

    private void editCategory() {
        System.out.println("Enter id category to edit");
        int idEdit = Integer.parseInt(Config.scanner().nextLine());
        if (categoryController.detailCategory(idEdit) == null){
            System.out.println(" ID category not existed");
        } else {
            Category category = categoryController.detailCategory(idEdit);
            System.out.println("OLD category: " + category.getNameCategory());
            System.out.println("Enter new category");
            String newNameCategory = Config.scanner().nextLine();
            if (newNameCategory.trim().equals("")) {
                newNameCategory = category.getNameCategory();
            }
            Category newCategory = new Category(newNameCategory);
            categoryController.editCategory(idEdit, newCategory);
            System.out.println("Edit successful");
            categoryController.showListCategory();
        }
        System.out.println("Nhập vào phím bất kì để tiếp tục - Nhập vào back để thoát: ");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("back")){
            new ViewCategory().menuCategory();
        }

    }


    public void createCategory() {
        int id;
        if (categoryList.size() == 0) {
            id = 1;
        } else {
            id = categoryList.get(categoryList.size()-1).getId() + 1;
        }
        String name;

        while (true) {
            System.out.println("Enter the name category");
            name = Config.scanner().nextLine();
            if (!name.trim().equals("")){
                break;
            }
        }
        Category category = new Category(id, name);
        categoryController.createCategory(category);
        categoryController.showListCategory();
//        System.out.println(categoryList);
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryList.get(i).getId() + categoryList.get(i).getNameCategory() + categoryList.get(i).getUser().getName());

        }
        System.out.println("Enter 'back' to back Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ViewCategory().menuCategory();
        }
    }

    public void showListCategory() {
        System.out.println("-----ID-----Name-----Name-----User-----");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println("-----" + categoryList.get(i).getId() + "-----" + categoryList.get(i).getNameCategory() + "-----" +  categoryList.get(i).getUser().getUsername() + "-----");
        }
        new ViewCategory().menuCategory();
    }

}
