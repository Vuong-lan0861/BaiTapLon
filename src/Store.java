import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Store {
    private static ArrayList<Category> categories;
    private static ArrayList<Product> products;
    private static Scanner scanner;

    public Store() {
        categories = new ArrayList<>();
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Store store = new Store();
        int choice;
        do {
            store.displayMainMenu();
            System.out.print("Chọn chức năng (1-3): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số.");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    store.manageCategories();
                    break;
                case 2:
                    store.manageProducts();
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 3);
    }


    private void displayMainMenu() {
        System.out.println("===== QUẢN LÝ KHO =====");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát");
    }

    private void handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                manageCategories();
                break;
            case 2:
                manageProducts();
                break;
            case 3:
                loadCategoriesFromFile();
                loadProductsFromFile();
                System.out.println("Dữ liệu đã được đọc từ file.");
                break;
            case 4:
                System.out.println("Kết thúc chương trình. Tạm biệt!");
                break;
            default:
                System.out.println("Chọn không hợp lệ. Vui lòng chọn lại.");
                break;
        }
    }

    private void loadCategoriesFromFile() {
        // Triển khai logic để đọc dữ liệu từ file categories.txt và thêm vào danh sách categories
        try (BufferedReader reader = new BufferedReader(new FileReader("categories.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String description = data[2];
                    boolean status = Boolean.parseBoolean(data[3]);

                    Category category = new Category(id, name, description, status);
                    categories.add(category);
                } else {
                    System.out.println("Dữ liệu không đúng định dạng trong file categories.txt");
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Xử lý lỗi khi đọc file hoặc chuyển đổi dữ liệu
            e.printStackTrace();
            System.out.println("Lỗi khi đọc từ file categories.txt");
        }
    }

    private void loadProductsFromFile() {
        // Triển khai logic để đọc dữ liệu từ file products.txt và thêm vào danh sách products
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    String id = data[0];
                    String name = data[1];
                    double importPrice = Double.parseDouble(data[2]);
                    double exportPrice = Double.parseDouble(data[3]);
                    String description = data[4];
                    boolean status = Boolean.parseBoolean(data[5]);
                    int categoryId = Integer.parseInt(data[6]);

                    Product product = new Product(id, name, importPrice, exportPrice, description, status, categoryId);
                    products.add(product);
                } else {
                    // Xử lý dữ liệu không đúng định dạng
                    System.out.println("Dữ liệu không đúng định dạng trong file products.txt");
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Xử lý lỗi khi đọc file hoặc chuyển đổi dữ liệu
            e.printStackTrace();
            System.out.println("Lỗi khi đọc từ file products.txt");
        }
    }

    private void manageCategories() {
        int choice;
        do {
            displayCategoryMenu();
            System.out.print("Chọn chức năng quản lý danh mục (1-6): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số.");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewCategory();
                    break;
                case 2:
                    updateCategory();
                    break;
                case 3:
                    deleteCategory();
                    break;
                case 4:
                    searchCategory();
                    break;
                case 5:
                    displayCategoryStatistics();
                    break;
                case 6:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 6);
    }

    private void displayCategoryMenu() {
        System.out.println("===== QUẢN LÝ DANH MỤC =====");
        System.out.println("1. Thêm mới danh mục");
        System.out.println("2. Cập nhật danh mục");
        System.out.println("3. Xóa danh mục");
        System.out.println("4. Tìm kiếm danh mục theo tên");
        System.out.println("5. Thống kê số lượng sản phẩm trong danh mục");
        System.out.println("6. Quay lại");
    }
    private void addNewCategory() {
        Category newCategory = new Category();
        newCategory.inputData(scanner);
        categories.add(newCategory);
        saveCategoriesToFile();
        System.out.println("Danh mục đã được thêm mới.");
    }

    private void updateCategory() {
        System.out.print("Nhập ID của danh mục cần cập nhật: ");
        int categoryIdToUpdate = scanner.nextInt();
        scanner.nextLine();

        Category existingCategory = findCategoryById(categoryIdToUpdate);

        if (existingCategory != null) {
            existingCategory.inputData(scanner);
            saveCategoriesToFile();
            System.out.println("Danh mục đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy danh mục có ID " + categoryIdToUpdate);
        }
    }

    private void deleteCategory() {
        System.out.print("Nhập ID của danh mục cần xóa: ");
        int categoryIdToDelete = scanner.nextInt();
        scanner.nextLine();

        Category existingCategory = findCategoryById(categoryIdToDelete);

        if (existingCategory != null) {
            if (isCategoryEmpty(existingCategory)) {
                categories.remove(existingCategory);
                saveCategoriesToFile();
                System.out.println("Danh mục đã được xóa.");
            } else {
                System.out.println("Danh mục đang có sản phẩm tham chiếu. Không thể xóa.");
            }
        } else {
            System.out.println("Không tìm thấy danh mục có ID " + categoryIdToDelete);
        }
    }

    private void searchCategory() {
        System.out.print("Nhập tên danh mục cần tìm kiếm: ");
        String categoryName = scanner.nextLine();
        boolean found = false;

        for (Category category : categories) {
            if (category.getName().toLowerCase().contains(categoryName.toLowerCase())) {
                category.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy danh mục nào có tên chứa \"" + categoryName + "\".");
        }
    }

    private void displayCategoryStatistics() {
        System.out.print("Nhập ID của danh mục cần thống kê: ");
        int categoryIdToStatistics = scanner.nextInt();
        scanner.nextLine();

        Category existingCategory = findCategoryById(categoryIdToStatistics);

        if (existingCategory != null) {
            int productCount = countProductsInCategory(existingCategory);
            System.out.println("Số lượng sản phẩm trong danh mục \"" + existingCategory.getName() + "\": " + productCount);
        } else {
            System.out.println("Không tìm thấy danh mục có ID " + categoryIdToStatistics);
        }
    }

    private Category findCategoryById(int categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    private boolean isCategoryEmpty(Category category) {
        for (Product product : products) {
            if (product.getCategoryId() == category.getId()) {
                return false;
            }
        }
        return true;
    }

    private int countProductsInCategory(Category category) {
        int count = 0;
        for (Product product : products) {
            if (product.getCategoryId() == category.getId()) {
                count++;
            }
        }
        return count;
    }

    private void saveCategoriesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categories.txt"))) {
            for (Category category : categories) {
                // Ghi thông tin của từng đối tượng category vào file
                writer.write(category.getId() + "," + category.getName() + "," + category.getDescription() + "," + category.isStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi ghi vào file categories.txt");
        }
    }

    private void manageProducts() {
        int choice;
        do {
            displayProductMenu();
            System.out.print("Chọn chức năng quản lý sản phẩm (1-6): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số.");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    displayProductsByNameAZ();
                    break;
                case 5:
                    displayProductsByProfit();
                    break;
                case 6:
                    searchProduct();
                    break;
                case 7:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 7);
    }

    private void displayProductMenu() {
        System.out.println("===== QUẢN LÝ SẢN PHẨM =====");
        System.out.println("1. Thêm mới sản phẩm");
        System.out.println("2. Cập nhật sản phẩm");
        System.out.println("3. Xóa sản phẩm");
        System.out.println("4. Hiển thị sản phẩm theo tên (A-Z)");
        System.out.println("5. Hiển thị sản phẩm theo lợi nhuận (cao-thấp)");
        System.out.println("6. Tìm kiếm sản phẩm");
        System.out.println("7. Quay lại");
    }


    private void addNewProduct() {
        // Nhập giá trị cho các tham số của Product từ bất kỳ nguồn dữ liệu nào bạn muốn
//        System.out.print("Nhập ID sản phẩm: ");
//        String id = scanner.next();
//        scanner.nextLine();
//        System.out.print("Nhập tên sản phẩm: ");
//        String name = scanner.nextLine();
//
//        System.out.print("Nhập giá nhập: ");
//        double importPrice = scanner.nextDouble();
//
//        System.out.print("Nhập giá bán: ");
//        double exportPrice = scanner.nextDouble();
//
//        System.out.print("Nhập mô tả sản phẩm: ");
//        String description = scanner.nextLine();
//
//        System.out.print("Nhập trạng thái sản phẩm (true/false): ");
//        boolean status = scanner.nextBoolean();
//
//        System.out.print("Nhập mã danh mục sản phẩm: ");
//        int categoryId = scanner.nextInt();
//        Product newProduct = new Product(id, name, importPrice, exportPrice, description, status, categoryId);
        Product newProduct = new Product();
        newProduct.inputData(scanner);
        products.add(newProduct);
        saveProductsToFile();
        System.out.println("Sản phẩm đã được thêm mới.");
    }

    private void updateProduct() {
        System.out.print("Nhập ID của sản phẩm cần cập nhật: ");
        String productIdToUpdate = scanner.next();
        scanner.nextLine();

        Product existingProduct = findProductById(productIdToUpdate);

        if (existingProduct != null) {

            existingProduct.calProfit();
            saveProductsToFile();
            System.out.println("Sản phẩm đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID " + productIdToUpdate);
        }
    }


    private void deleteProduct() {
        System.out.print("Nhập ID của sản phẩm cần xóa: ");
        String productIdToDelete = scanner.next();
        scanner.nextLine();

        Product existingProduct = findProductById(productIdToDelete);

        if (existingProduct != null) {
            products.remove(existingProduct);
            saveProductsToFile();
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID " + productIdToDelete);
        }
    }

    private void displayProductsByNameAZ() {
        products.sort(Comparator.comparing(Product::getName));
        displayAllProducts();
    }

    private void displayProductsByProfit() {
        products.sort(Comparator.comparing(Product::getProfit).reversed());
        displayAllProducts();
    }

    private void searchProduct() {
        System.out.print("Nhập từ khóa tìm kiếm cho sản phẩm: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword) ||
                    String.valueOf(product.getImportPrice()).contains(keyword) ||
                    String.valueOf(product.getExportPrice()).contains(keyword)) {
                product.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm nào chứa từ khóa \"" + keyword + "\".");
        }
    }

    private Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private void displayAllProducts() {
        for (Product product : products) {
            product.displayData();
        }
    }

    private void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (Product product : products) {
                // Ghi thông tin của từng đối tượng product vào file
                writer.write(product.getId() + "," + product.getName() + "," + product.getImportPrice() +
                        "," + product.getExportPrice() + "," + product.getDescription() + "," + product.isStatus() +
                        "," + product.getCategoryId());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi ghi vào file products.txt");
        }
    }
}

