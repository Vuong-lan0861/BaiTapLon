import java.util.Scanner;

public class Product implements IProduct {
    private String id;
    private String name;
    private double importPrice;
    private double exportPrice;
    private double profit;
    private String description;
    private boolean status;
    private int categoryId;

    // Constructors, getters, setters
    public Product(String id, String name, double importPrice, double exportPrice, String description, boolean status, int categoryId) {
    }
    public Product(String id, String name, double importPrice, double exportPrice, double profit, String description, boolean status, int categoryId) {
        this.id = id;
        this.name = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void inputData(Scanner sc) {

        // Nhập thông tin cho đối tượng Product từ người dùng
        System.out.println("Nhập thông tin sản phẩm:");

        // Nhập và kiểm tra ID
        System.out.print("Nhập ID (bắt đầu bằng \"P\" và gồm 4 kí tự): ");
        while (!(id = sc.next()).matches("P\\d{3}")) {
            System.out.println("ID không hợp lệ. Vui lòng nhập lại.");
        }

        sc.nextLine(); // Đọc và bỏ qua dòng còn lại

        // Nhập và kiểm tra tên
        System.out.print("Nhập tên sản phẩm (từ 6-30 kí tự): ");
        while (!(name = sc.nextLine()).matches(".{6,30}")) {
            System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
        }

        // Nhập và kiểm tra giá nhập
        System.out.print("Nhập giá nhập (số thực lớn hơn 0): ");
        while (!sc.hasNextDouble() || (importPrice = sc.nextDouble()) <= 0) {
            System.out.println("Giá nhập không hợp lệ. Vui lòng nhập lại.");
            sc.nextLine();
        }

        // Nhập và kiểm tra giá bán
        System.out.print("Nhập giá bán (số thực lớn hơn 0 và lớn hơn giá nhập ít nhất " + IProduct.MIN_INTEREST_RATE + " lần): ");
        while (!sc.hasNextDouble() || (exportPrice = sc.nextDouble()) <= 0 || exportPrice <= importPrice * IProduct.MIN_INTEREST_RATE) {
            System.out.println("Giá bán không hợp lệ. Vui lòng nhập lại.");
            sc.nextLine();
        }

        // Nhập và kiểm tra mô tả
        sc.nextLine(); // Đọc và bỏ qua dòng còn lại
        System.out.print("Nhập mô tả sản phẩm: ");
        description = sc.nextLine();

        // Nhập và kiểm tra trạng thái
        System.out.print("Nhập trạng thái sản phẩm (true/false): ");
        while (!sc.hasNextBoolean()) {
            System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại.");
            sc.nextLine();
        }
        status = sc.nextBoolean();

        // Nhập và kiểm tra mã danh mục sản phẩm
        System.out.print("Nhập mã danh mục sản phẩm: ");
        while (!sc.hasNextInt()) {
            System.out.println("Mã danh mục không hợp lệ. Vui lòng nhập lại.");
            sc.nextLine();
        }
        categoryId = sc.nextInt();

        System.out.println("Thông tin sản phẩm đã được nhập.");
    }

    @Override
    public void displayData() {

        System.out.println("Thông tin sản phẩm:");
        System.out.println("ID: " + id);
        System.out.println("Tên: " + name);
        System.out.println("Giá nhập: " + importPrice);
        System.out.println("Giá bán: " + exportPrice);
        System.out.println("Lợi nhuận: " +calProfit());
        System.out.println("Mô tả: " + description);
        System.out.println("Trạng thái: " + (status ? "Còn hàng" : "Ngừng kinh doanh"));
        System.out.println("Mã danh mục: " + categoryId);
    }

    @Override
    public double calProfit() {
        return profit = exportPrice - importPrice;
    }
}
