import java.util.Scanner;
public class GradeStudent {
    int[] weight = new int[3]; // Tạo mảng để lưu các giá trị trọng số
    double[] weightedScore = new double[3]; // Tạo mảng để lưu giá trị điểm sau khi đã tính trọng số
    Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        begin();
        GradeStudent student = new GradeStudent();
        student.midTerm();
        student.finalTerm();
        student.homeWork();
        student.report();
    }

    // Tạo phương thức hiển thị thông điệp chào mừng
    static void begin() {
        System.out.println("This program processes the scores of your exam and homework, \nthen reports back on your overall course grade.\n");
    }
    // Tạo hàm làm tròn trả về số thực đã được làm tròn đến một chữ số thập phân
    double round10(double x) {
        return Math.round(x * 10) / 10.0;
    }
    // Tạo phương thức nhập và tính toán điểm thi giữa kì
    void midTerm() {
        System.out.println("* MIDTERM:");
        System.out.print("\tWeight of score (0-100): ");
        weight[0] = input.nextInt();
        // Tạo vòng lặp nếu nhập trọng số không hợp lệ
        while (weight[0] < 1 || weight[0] >= 100) {
            System.out.println("(!)The weight is invalid, please try again.");
            System.out.print("\tWeight of score (0-100): ");
            weight[0] = input.nextInt();
        }
        System.out.print("\tYour Score: ");
        int score = input.nextInt(); // Khai báo biến lấy điểm giữa kì, do dùng thang điểm 100 nên khai báo kiểu int
        System.out.print("\tDo you have any extra points? [Yes(1) || No(0)] ");
        int isPlus = input.nextInt();
        int extra = 0; // Khai báo biến để lấy điểm cộng thêm (nếu có), do dùng thang điểm 100 nên khai báo kiểu int
        if (isPlus == 1) {
            System.out.print("\tExtra points: ");
            extra = input.nextInt();
        }
        int total = Math.min(score + extra, 100); // Tổng điểm giữa kì tối đa là 100
        System.out.println("\tTotal points = " + total + " (out of 100)");
        weightedScore[0] = total / 100.0 * weight[0];
        System.out.println("\tWeighted score = " + round10(weightedScore[0]) + " (out of " + weight[0] + ")\n");
    }
    // Tạo phương thức nhập và tính toán điểm thi cuối kì
    void finalTerm() {
        System.out.println("* FINAL:");
        System.out.print("\tWeight of score (0-100): ");
        weight[1] = input.nextInt();
        // Tạo vòng lặp nếu nhập trọng số không hợp lệ
        while (weight[1] < 1 || weight[0] + weight[1] >= 100) {
            System.out.println("(!)The weight is invalid, please try again.");
            System.out.print("\tWeight of score (0-100): ");
            weight[1] = input.nextInt();
        }
        System.out.print("\tYour Score: ");
        int score = input.nextInt(); // Khai báo biến lấy điểm cuối kì, do dùng thang điểm 100 nên khai báo kiểu int
        System.out.print("\tDo you have any extra points? [Yes(1) || No(0)] ");
        int isPlus = input.nextInt();
        int extra = 0; // Khai báo biến để lấy điểm cộng thêm (nếu có), do dùng thang điểm 100 nên khai báo kiểu int
        if (isPlus == 1) {
            System.out.print("\tExtra points: ");
            extra = input.nextInt();
        }
        int total = Math.min(score + extra , 100); // Tổng điểm cuối kì tối đa là 100
        System.out.println("\tTotal points = " + total + " (out of 100)");
        weightedScore[1] = total / 100.0 * weight[1];
        System.out.println("\tWeighted score = " + round10(weightedScore[1]) + " (out of " + weight[1] + ")\n");
    }
    // Tạo phương thức nhập và tính toán điểm bài tập và chuyên cần
    void homeWork() {
        System.out.println("* HOMEWORK:");
        System.out.print("\tWeight of score (0-100): ");
        weight[2] = input.nextInt();
        // Tạo vòng lặp nếu nhập trọng số không hợp lệ
        while (weight[0] + weight[1] + weight[2] != 100) {
            System.out.println("(!)The sum of all weights is not equal to 100, please try again.");
            System.out.print("\tWeight of score (0-100): ");
            weight[2] = input.nextInt();
        }
        System.out.print("\tNumber of assignments: ");
        int numAsm = input.nextInt(); // Số bài Assignment cần làm
        int[][] asm = new int[numAsm][2]; // Tạo mảng hai chiều để lưu điểm cho các bài Assignment, do dùng thang điểm 100 nên khai báo kiểu int
        int sumScore = 0, sumMax = 0;
        for (int i = 0; i < numAsm; i++) {
            System.out.print("\tAssignment " + (i+1) + " score and max: ");
            asm[i][0] = input.nextInt();
            sumScore += asm[i][0];
            asm[i][1] = input.nextInt();
            sumMax += asm[i][1];
        }
        sumScore = Math.min(sumScore, 150); // Điểm tối đa của phần Assignment là 150
        System.out.print("\tNumber of sessions you attended: ");
        int attendance = Math.min(input.nextInt() * 5, 30); // Điểm tối đa của phần Attendance là 30
        System.out.println("\tAttendance points = " + attendance + " (out of 30)");
        int total = sumScore + attendance;
        int maximum = sumMax + 30;
        System.out.println("\tTotal points = " + total + " (out of " + maximum + ")");
        weightedScore[2] = (double) total / maximum * weight[2];
        System.out.println("\tWeighted score = " + round10(weightedScore[2]) + " (out of " + weight[2] + ")\n");
    }
    // Tạo phương thức để tổng kết điểm và in nhận xét tương ứng
    void report() {
        double overall = round10(weightedScore[0] + weightedScore[1] + weightedScore[2]);
        System.out.println("-> SUMMARY:");
        System.out.println("\tOverall percentage = " + overall);
        if (overall < 60) {
            System.out.println("\tYour Grade Point Average (GPA) will be at least: " + 0.0);
            System.out.println("\tYou should review from the beginning, try more and more.");
        } else if (overall < 75) {
            System.out.println("\tYour Grade Point Average (GPA) will be at least: " + 0.7);
            System.out.println("You don't do very well, please try harder");
        } else if (overall < 85) {
            System.out.println("\tYour Grade Point Average (GPA) will be at least: " + 2.0);
            System.out.println("\tYou do pretty well, but let's try a little harder.");
        } else {
            System.out.println("\tYour Grade Point Average (GPA) will be at least: " + 3.0);
            System.out.println("\tYou do a great job, keep up the good work.");
        }
    }
}
