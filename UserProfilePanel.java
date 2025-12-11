import java.awt.*;
import javax.swing.*;

public class UserProfilePanel extends JPanel {

    private AfterLoginPage parent;

    public UserProfilePanel(AfterLoginPage parent) {
        this.parent = parent;

        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1200, 800));

        // User Icon (centered at top)
        JLabel userIcon = new JLabel("👤");
        userIcon.setFont(new Font("Arial", Font.PLAIN, 80));
        userIcon.setHorizontalAlignment(SwingConstants.CENTER);
        userIcon.setBounds(400, 30, 400, 100);
        add(userIcon);

        // USER label
        JLabel userLabel = new JLabel("USER");
        userLabel.setFont(new Font("Arial", Font.BOLD, 28));
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setBounds(400, 130, 400, 40);
        add(userLabel);

        // Separator line
        JSeparator sep1 = new JSeparator();
        sep1.setBounds(350, 175, 500, 2);
        add(sep1);

        // TRANSACTION HISTORY label
        JLabel transLabel = new JLabel("TRANSACTION HISTORY");
        transLabel.setFont(new Font("Arial", Font.BOLD, 24));
        transLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transLabel.setBounds(350, 200, 500, 40);
        add(transLabel);

        // Transaction items panel
        JPanel transPanel = new JPanel();
        transPanel.setLayout(new BoxLayout(transPanel, BoxLayout.Y_AXIS));
        transPanel.setBackground(Color.WHITE);

        if (GlobalTransactionHistory.transactions.isEmpty()) {
            JLabel empty = new JLabel("No transactions yet");
            empty.setFont(new Font("Arial", Font.PLAIN, 16));
            empty.setForeground(Color.GRAY);
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            transPanel.add(Box.createVerticalStrut(30));
            transPanel.add(empty);
        } else {
            for (Transaction trans : GlobalTransactionHistory.transactions) {
                transPanel.add(createTransactionItem(trans));
                transPanel.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane scroll = new JScrollPane(transPanel);
        scroll.setBounds(250, 260, 700, 350);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        add(scroll);

        // LOG OUT button
        JButton logoutBtn = new JButton("LOG OUT");
        logoutBtn.setBounds(400, 650, 400, 50);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 18));
        logoutBtn.setBackground(new Color(10, 40, 90));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder());
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(e -> {
            // Close the application
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        add(logoutBtn);
    }

    private JPanel createTransactionItem(Transaction trans) {
        JPanel box = new JPanel(null);
        box.setPreferredSize(new Dimension(650, 60));
        box.setBackground(new Color(240, 240, 240));
        box.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        box.setMaximumSize(new Dimension(650, 60));

        JLabel titleLabel = new JLabel(trans.title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(15, 5, 400, 20);
        box.add(titleLabel);

        JLabel priceLabel = new JLabel(trans.price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(150, 0, 0));
        priceLabel.setBounds(15, 30, 150, 20);
        box.add(priceLabel);

        JLabel dateLabel = new JLabel(trans.date);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setForeground(Color.GRAY);
        dateLabel.setBounds(500, 30, 130, 20);
        box.add(dateLabel);

        return box;
    }
}
