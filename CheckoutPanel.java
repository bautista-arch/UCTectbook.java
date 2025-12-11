import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckoutPanel extends JPanel {

    private AfterLoginPage parent;
    private CartItem item;

    public CheckoutPanel(AfterLoginPage parent, CartItem item) {
        this.parent = parent;
        this.item = item;

        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1200, 800));

        createMenuPanel();

        // CHECKOUT title
        JLabel checkoutTitle = new JLabel("CHECKOUT");
        checkoutTitle.setFont(new Font("Arial", Font.BOLD, 32));
        checkoutTitle.setBounds(50, 70, 300, 50);
        add(checkoutTitle);

        // Separator
        JSeparator sep1 = new JSeparator();
        sep1.setBounds(50, 125, 1100, 2);
        add(sep1);

        // Item details section
        ImageIcon raw = new ImageIcon(item.imagePath);
        Image scaled = raw.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        JLabel itemImg = new JLabel(new ImageIcon(scaled));
        itemImg.setBounds(50, 150, 80, 100);
        add(itemImg);

        JLabel itemTitle = new JLabel(item.title);
        itemTitle.setFont(new Font("Arial", Font.BOLD, 18));
        itemTitle.setBounds(150, 150, 600, 30);
        add(itemTitle);

        JLabel itemDesc = new JLabel("A STRUCTURED PROGRAMMING APPROACH IN C 4TH ED");
        itemDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        itemDesc.setForeground(Color.GRAY);
        itemDesc.setBounds(150, 185, 600, 20);
        add(itemDesc);

        JLabel itemPrice = new JLabel(item.price);
        itemPrice.setFont(new Font("Arial", Font.BOLD, 20));
        itemPrice.setForeground(new Color(150, 0, 0));
        itemPrice.setBounds(150, 215, 200, 30);
        add(itemPrice);

        // Separator
        JSeparator sep2 = new JSeparator();
        sep2.setBounds(50, 270, 1100, 2);
        add(sep2);

        // Payment methods section
        JLabel paymentLabel = new JLabel("PAYMENT METHODS");
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        paymentLabel.setBounds(50, 290, 200, 25);
        add(paymentLabel);

        JLabel selectLabel = new JLabel("SELECT A METHOD");
        selectLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        selectLabel.setForeground(Color.GRAY);
        selectLabel.setBounds(50, 315, 200, 20);
        add(selectLabel);

        // Cash payment button
        JButton cashBtn = new JButton("CASH PAYMENT (FACE-TO-FACE PAYMENT)");
        cashBtn.setBounds(80, 345, 350, 50);
        cashBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        cashBtn.setBackground(new Color(100, 150, 200));
        cashBtn.setForeground(Color.WHITE);
        cashBtn.setFocusPainted(false);
        cashBtn.setBorder(new AfterLoginPage.RoundedBorder(25, new Color(100, 150, 200), 2));
        cashBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cashBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cashBtn.setBackground(new Color(70, 120, 170));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                cashBtn.setBackground(new Color(100, 150, 200));
            }
        });
        add(cashBtn);

        // Online payment button
        JButton onlineBtn = new JButton("ONLINE PAYMENT");
        onlineBtn.setBounds(770, 345, 350, 50);
        onlineBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        onlineBtn.setBackground(new Color(100, 180, 150));
        onlineBtn.setForeground(Color.WHITE);
        onlineBtn.setFocusPainted(false);
        onlineBtn.setBorder(new AfterLoginPage.RoundedBorder(25, new Color(100, 180, 150), 2));
        onlineBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        onlineBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onlineBtn.setBackground(new Color(70, 150, 120));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                onlineBtn.setBackground(new Color(100, 180, 150));
            }
        });
        add(onlineBtn);

        // Separator
        JSeparator sep3 = new JSeparator();
        sep3.setBounds(50, 420, 1100, 2);
        add(sep3);

        // Payment details section
        JLabel paymentDetailsLabel = new JLabel("PAYMENT DETAILS");
        paymentDetailsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        paymentDetailsLabel.setBounds(50, 440, 200, 25);
        add(paymentDetailsLabel);

        JLabel itemSubLabel = new JLabel("ITEM SUBTOTAL");
        itemSubLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        itemSubLabel.setBounds(70, 470, 150, 20);
        add(itemSubLabel);

        JLabel subtotalPrice = new JLabel(item.price);
        subtotalPrice.setFont(new Font("Arial", Font.BOLD, 14));
        subtotalPrice.setForeground(new Color(150, 0, 0));
        subtotalPrice.setBounds(800, 470, 250, 20);
        add(subtotalPrice);

        JLabel totalLabel = new JLabel("TOTAL PAYMENT");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBounds(70, 510, 200, 25);
        add(totalLabel);

        JLabel totalPrice = new JLabel(item.price);
        totalPrice.setFont(new Font("Arial", Font.BOLD, 18));
        totalPrice.setForeground(new Color(150, 0, 0));
        totalPrice.setBounds(800, 510, 250, 25);
        add(totalPrice);

        // Separator
        JSeparator sep4 = new JSeparator();
        sep4.setBounds(50, 560, 1100, 2);
        add(sep4);

        // Purchase button
        JButton purchaseBtn = new JButton("PURCHASE");
        purchaseBtn.setBounds(400, 600, 400, 60);
        purchaseBtn.setFont(new Font("Arial", Font.BOLD, 16));
        purchaseBtn.setBackground(new Color(220, 100, 100));
        purchaseBtn.setForeground(Color.WHITE);
        purchaseBtn.setFocusPainted(false);
        purchaseBtn.setBorder(new AfterLoginPage.RoundedBorder(30, new Color(220, 100, 100), 2));
        purchaseBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        purchaseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                purchaseBtn.setBackground(new Color(190, 70, 70));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                purchaseBtn.setBackground(new Color(220, 100, 100));
            }
        });
        purchaseBtn.addActionListener(e -> {
            java.time.LocalDate today = java.time.LocalDate.now();
            GlobalTransactionHistory.transactions.add(new Transaction(item.title, item.price, today.toString()));
            GlobalCartList.cartItems.remove(item);
            JOptionPane.showMessageDialog(this, "Purchase successful!\nThank you for your purchase.");
            parent.loadHomePage();
        });
        add(purchaseBtn);
    }

    private void createMenuPanel() {
        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setBounds(0, 0, 1200, 60);
        menuPanel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK));

        String[] menuItems = {"Home", "Departments", "Recently Added", "New Arrivals"};
        int totalWidth = 1200;
        int itemHeight = 60;
        int slotWidth = totalWidth / menuItems.length;

        for (int i = 0; i < menuItems.length; i++) {
            String item = menuItems[i];
            JPanel slotPanel = new JPanel(null);
            slotPanel.setBounds(i * slotWidth, 0, slotWidth, itemHeight);
            slotPanel.setOpaque(false);

            JLabel menuLabel = new JLabel(item, SwingConstants.CENTER);
            menuLabel.setFont(new Font("Arial", Font.BOLD, 14));
            menuLabel.setForeground(Color.WHITE);
            menuLabel.setBounds(0, 0, slotWidth, itemHeight);
            menuLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            menuLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (item.equals("Home")) {
                        parent.loadHomePage();
                    } else if (item.equals("Departments")) {
                        JPopupMenu deptMenu = new JPopupMenu();
                        deptMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                        String[] departments = {"CBA", "CHM", "CCS", "COF"};

                        for (String dept : departments) {
                            JMenuItem menuItem = new JMenuItem(dept);
                            menuItem.setPreferredSize(new Dimension(150, 40));
                            menuItem.addActionListener(ae -> {
                                parent.loadDepartment(dept);
                            });
                            deptMenu.add(menuItem);
                        }
                        int x = (slotPanel.getWidth() - deptMenu.getPreferredSize().width) / 2;
                        deptMenu.show(menuLabel, x, slotPanel.getHeight());
                    } else {
                        JOptionPane.showMessageDialog(null, item + " clicked!");
                    }
                }
            });

            slotPanel.add(menuLabel);
            menuPanel.add(slotPanel);
        }

        add(menuPanel);
    }
}
