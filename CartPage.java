import java.awt.*;
import javax.swing.*;

public class CartPage extends JFrame {

    public CartPage() {
        setTitle("User's Cart");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        if (GlobalCartList.cartItems.isEmpty()) {
            JLabel empty = new JLabel("Your cart is empty.");
            empty.setFont(new Font("Arial", Font.BOLD, 18));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalStrut(20));
            panel.add(empty);
        } else {
            for (CartItem item : GlobalCartList.cartItems) {
                panel.add(createItemPanel(item));
                panel.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }

    private JPanel createItemPanel(CartItem item) {
        JPanel itemPanel = new JPanel(null);
        itemPanel.setPreferredSize(new Dimension(650, 150));
        itemPanel.setBackground(new Color(245, 245, 245));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        ImageIcon raw = new ImageIcon(item.imagePath);
        Image scaled = raw.getImage().getScaledInstance(90, 110, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled));
        img.setBounds(20, 20, 90, 110);
        itemPanel.add(img);

        JLabel title = new JLabel(item.title);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(130, 20, 300, 25);
        itemPanel.add(title);

        JLabel price = new JLabel(item.price);
        price.setFont(new Font("Arial", Font.BOLD, 18));
        price.setForeground(new Color(150, 0, 0));
        price.setBounds(130, 55, 200, 30);
        itemPanel.add(price);

        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(500, 55, 100, 30);
        removeBtn.addActionListener(e -> {
            GlobalCartList.cartItems.remove(item);
            dispose();
            new CartPage(); // refresh cart
        });
        itemPanel.add(removeBtn);

        return itemPanel;
    }
}
