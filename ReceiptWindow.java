import java.awt.*;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class ReceiptWindow extends JFrame {

    public ReceiptWindow(List<CartItem> items) {
        this(items, "FIRST NAME, M.I., LAST NAME", "BSIT");
    }

    public ReceiptWindow(List<CartItem> items, String studentName, String course) {
        setTitle("Official Receipt");
        setSize(920, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Outer white panel with margin
        JPanel outer = new JPanel(null);
        outer.setBackground(Color.WHITE);
        outer.setBounds(8, 8, 880, 700);
        outer.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        add(outer);

        // Header area with light-blue gradient panel
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(190,215,255),
                        0, getHeight(), new Color(230,245,255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setBounds(0, 0, 880, 120);
        header.setLayout(null);
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        outer.add(header);

        // UC logo placeholder (left)
        JLabel logo = new JLabel("UC", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 36));
        logo.setForeground(new Color(10,40,90));
        logo.setBounds(12, 18, 90, 80);
        logo.setBorder(BorderFactory.createLineBorder(new Color(170,170,170)));
        header.add(logo);

        // University info
        JLabel uni = new JLabel("UNIVERSITY OF CEBU - MAIN CAMPUS");
        uni.setFont(new Font("Arial", Font.BOLD, 18));
        uni.setBounds(120, 16, 540, 26);
        header.add(uni);

        JLabel addr = new JLabel("OSMENA BLVD. COR. SANCIANGKO ST., CEBU CITY 6000, CEBU, PHILIPPINES");
        addr.setFont(new Font("Arial", Font.PLAIN, 11));
        addr.setBounds(120, 40, 700, 18);
        header.add(addr);

        JLabel tel = new JLabel("TEL. NO. (63) (32) 255-7777");
        tel.setFont(new Font("Arial", Font.PLAIN, 11));
        tel.setBounds(120, 58, 300, 16);
        header.add(tel);

        // Semester box (right)
        JTextField semField = new JTextField("1ST SEMESTER S.Y. 2025-2026");
        semField.setEditable(false);
        semField.setHorizontalAlignment(JTextField.CENTER);
        semField.setBounds(620, 18, 240, 26);
        header.add(semField);

        // Priority (right below semester)
        JTextField priorityField = new JTextField(generatePriority());
        priorityField.setEditable(false);
        priorityField.setHorizontalAlignment(JTextField.CENTER);
        priorityField.setBounds(620, 58, 240, 26);
        header.add(priorityField);

        // Student name row (below header)
        JTextField studentField = new JTextField(studentName);
        studentField.setEditable(false);
        studentField.setBounds(20, 130, 620, 28);
        outer.add(studentField);

        JTextField courseField = new JTextField(course);
        courseField.setEditable(false);
        courseField.setBounds(660, 130, 180, 28);
        outer.add(courseField);

        // Table area
        JPanel table = new JPanel(null);
        table.setBackground(Color.WHITE);
        table.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        table.setBounds(20, 170, 820, 420);
        outer.add(table);

        // Column labels
        JLabel colEdp = new JLabel("EDP CODE");
        colEdp.setFont(new Font("Arial", Font.BOLD, 13));
        colEdp.setBounds(20, 10, 120, 20);
        table.add(colEdp);

        JLabel colSub = new JLabel("SUBJECT");
        colSub.setFont(new Font("Arial", Font.BOLD, 13));
        colSub.setBounds(140, 10, 150, 20);
        table.add(colSub);

        JLabel colTitle = new JLabel("BOOK TITLE");
        colTitle.setFont(new Font("Arial", Font.BOLD, 13));
        colTitle.setBounds(300, 10, 360, 20);
        table.add(colTitle);

        JLabel colPrice = new JLabel("PRICE");
        colPrice.setFont(new Font("Arial", Font.BOLD, 13));
        colPrice.setBounds(680, 10, 120, 20);
        table.add(colPrice);

        // Add items
        int y = 40;
        double subtotal = 0.0;
        for (CartItem it : items) {
            JLabel edp = new JLabel(it.edpCode != null ? it.edpCode : "");
            edp.setBounds(20, y, 120, 18);
            table.add(edp);

            JLabel sub = new JLabel(it.subjectCode != null ? it.subjectCode : "");
            sub.setBounds(140, y, 150, 18);
            table.add(sub);

            JLabel tt = new JLabel(it.title);
            tt.setBounds(300, y, 360, 18);
            table.add(tt);

            JLabel pr = new JLabel(it.price);
            pr.setHorizontalAlignment(SwingConstants.RIGHT);
            pr.setBounds(680, y, 120, 18);
            table.add(pr);

            subtotal += parsePrice(it.price);
            y += 28;
            // draw thin line
            JSeparator s = new JSeparator();
            s.setBounds(10, y - 8, 800, 1);
            table.add(s);
        }

        // Subtotal and total area (below table)
        JLabel lblSubtotal = new JLabel("ITEM SUBTOTAL");
        lblSubtotal.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSubtotal.setBounds(20, 360, 200, 30);
        table.add(lblSubtotal);

        JLabel valSubtotal = new JLabel(String.format("₱%,.2f", subtotal));
        valSubtotal.setBounds(680, 360, 120, 30);
        valSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
        valSubtotal.setFont(new Font("Arial", Font.PLAIN, 13));
        table.add(valSubtotal);

        JLabel lblTotal = new JLabel("TOTAL PAYMENT");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setBounds(20, 392, 200, 30);
        table.add(lblTotal);

        JLabel valTotal = new JLabel(String.format("₱%,.2f", subtotal));
        valTotal.setBounds(680, 392, 120, 30);
        valTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        valTotal.setFont(new Font("Arial", Font.BOLD, 14));
        table.add(valTotal);

        // Done button
        JButton done = new JButton("DONE");
        done.setBounds(380, 605, 120, 36);
        done.addActionListener(e -> dispose());
        outer.add(done);

        setVisible(true);
    }

    private String generatePriority() {
        Random r = new Random();
        int num = 100000000 + r.nextInt(900000000);
        return Integer.toString(num);
    }

    private double parsePrice(String priceStr) {
        try {
            String cleaned = priceStr.replaceAll("[^0-9.]", "");
            if (cleaned.isEmpty()) return 0.0;
            return Double.parseDouble(cleaned);
        } catch (Exception ex) {
            return 0.0;
        }
    }
}
