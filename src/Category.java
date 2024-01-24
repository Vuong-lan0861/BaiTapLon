import java.util.Scanner;

public class Category implements ICategory {
    private int id;
    private String name;
    private String description;
    private boolean status;

    // Constructors, getters, setters
    public Category() {
    }
    public Category(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void inputData(Scanner sc) {

        // Nhập thông tin cho đối tượng Category từ người dùng
        System.out.println("Nhập thông tin danh mục sản phẩm:");

        // Nhập và kiểm tra ID
        System.out.print("Nhập ID (số nguyên lớn hơn 0): ");
        while (!sc.hasNextInt() || (id = sc.nextInt()) <= 0) {
            System.out.println("ID không hợp lệ. Vui lòng nhập lại.");
            sc.nextLine();
        }

        sc.nextLine();

        // Nhập và kiểm tra tên
        System.out.print("Nhập tên danh mục (từ 6-30 kí tự): ");
        while (!(name = sc.nextLine()).matches(".{6,30}")) {
            System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
        }

        // Nhập và kiểm tra mô tả
        System.out.print("Nhập mô tả danh mục: ");
        description = sc.nextLine();

        // Nhập và kiểm tra trạng thái
        System.out.print("Nhập trạng thái danh mục (true/false): ");
        while (!sc.hasNextBoolean()) {
            System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại.");
            sc.nextLine();
        }
        status = sc.nextBoolean();

        System.out.println("Thông tin danh mục đã được nhập.");
    }


    @Override
    public void displayData() {
        System.out.println("Thông tin danh mục sản phẩm:");
        System.out.println("ID: " + id);
        System.out.println("Tên: " + name);
        System.out.println("Mô tả: " + description);
        System.out.println("Trạng thái: " + (status ? "Hoạt động" : "Không hoạt động"));
    }
}
