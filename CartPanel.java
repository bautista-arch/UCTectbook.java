import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CartPanel extends JPanel {

    private AfterLoginPage parent;

    public CartPanel(AfterLoginPage parent) {
        this.parent = parent;

        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 800));

        createMenuPanel();

        JLabel title = new JLabel("Your Cart");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(50, 70, 400, 40);
        add(title);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.WHITE);

        if (GlobalCartList.cartItems.isEmpty()) {

            JLabel empty = new JLabel("Your cart is empty.");
            empty.setFont(new Font("Arial", Font.BOLD, 20));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);

            itemsPanel.add(Box.createVerticalStrut(30));
            itemsPanel.add(empty);

        } else {
            for (CartItem item : GlobalCartList.cartItems) {
                itemsPanel.add(createItemPanel(item));
                itemsPanel.add(Box.createVerticalStrut(20));
            }
        }

        JScrollPane scroll = new JScrollPane(itemsPanel);
        scroll.setBounds(50, 120, 1100, 540);
        scroll.setBorder(null);

        add(scroll);

        JButton checkoutBtn = new JButton("Check Out");
        checkoutBtn.setBounds(450, 680, 250, 50);
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 18));
        checkoutBtn.setBackground(new Color(100, 160, 200));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBorder(new AfterLoginPage.RoundedBorder(25, new Color(100, 160, 200), 2));
        checkoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkoutBtn.setBackground(new Color(70, 130, 170));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                checkoutBtn.setBackground(new Color(100, 160, 200));
            }
        });
        checkoutBtn.addActionListener(e -> {
            if (!GlobalCartList.cartItems.isEmpty()) {
                parent.loadCheckout(GlobalCartList.cartItems.get(0));
            } else {
                JOptionPane.showMessageDialog(this, "Your cart is empty!");
            }
        });
        add(checkoutBtn);
    }

    private void createMenuPanel() {
        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setBounds(0, 0, 1200, 60);
        menuPanel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK));

        String[] menuItems = {"Home", "Departments", "Recently Added", "New Arrivals"};
        int totalWidth = 1100;
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

    private JPanel createItemPanel(CartItem item) {

        JPanel box = new JPanel(null);
        box.setPreferredSize(new Dimension(1000, 180));
        box.setBackground(new Color(245, 245, 245));
        box.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        ImageIcon raw = new ImageIcon(item.imagePath);
        Image scaled = raw.getImage().getScaledInstance(120, 130, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled));
        img.setBounds(30, 20, 120, 130);
        box.add(img);

        JLabel title = new JLabel(item.title);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(180, 20, 600, 30);
        box.add(title);

        JLabel price = new JLabel(item.price);
        price.setFont(new Font("Arial", Font.BOLD, 20));
        price.setForeground(new Color(160, 0, 0));
        price.setBounds(180, 60, 200, 30);
        box.add(price);

        JButton remove = new JButton(new ImageIcon("c:\\Users\\ASUS\\Documents\\ImageFile\\trash.png"));
        remove.setBounds(900, 50, 40, 40);
        remove.setBorder(null);
        remove.setContentAreaFilled(false);
        remove.setCursor(new Cursor(Cursor.HAND_CURSOR));

        remove.addActionListener(e -> {
            GlobalCartList.cartItems.remove(item);
            SwingUtilities.getWindowAncestor(box).dispose();
        });

        box.add(remove);

        return box;
    }
}
