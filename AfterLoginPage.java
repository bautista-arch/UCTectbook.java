import java.awt.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class AfterLoginPage extends JFrame {

    static class RoundedBorder extends AbstractBorder {
        private int radius;
        private Color color;
        private int thickness;

        public RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x + thickness / 2, y + thickness / 2, width - thickness, height - thickness, radius, radius);
        }
    }

    public AfterLoginPage() {

        setTitle("UC Textbook Portal - Home");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1200, 800)); 

        JPanel topNav = new JPanel();
        topNav.setLayout(null);
        topNav.setBackground(Color.WHITE);
        topNav.setBounds(0, 0, 1200, 70);

        JLabel ucLogo = new JLabel(new ImageIcon("c:\\Users\\ASUS\\Documents\\UcSmallLogo.png"));
        ucLogo.setBounds(10, 10, 60, 50);
        topNav.add(ucLogo);

        JTextField searchBar = new JTextField("Search bar");
        searchBar.setBounds(200, 20, 400, 30);
        searchBar.setHorizontalAlignment(JTextField.CENTER);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        searchBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        topNav.add(searchBar);

        JLabel cartIcon = new JLabel(new ImageIcon("c:\\Users\\ASUS\\Documents\\cart.png"));
        cartIcon.setBounds(650, 10, 50, 50);
        topNav.add(cartIcon);

        JButton userButton = new JButton("User");
        userButton.setBounds(720, 20, 120, 30);
        userButton.setFont(new Font("Arial", Font.BOLD, 14));
        userButton.setBackground(Color.WHITE);
        userButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        topNav.add(userButton);

        mainPanel.add(topNav);

        JPanel banner = new JPanel();
        banner.setLayout(null);
        banner.setBackground(Color.BLACK);
        banner.setBounds(40, 100, 1100, 220);
        banner.setBorder(new RoundedBorder(30, Color.BLACK, 4));

        JLabel bannerText = new JLabel("BANNER");
        bannerText.setForeground(Color.WHITE);
        bannerText.setFont(new Font("Arial", Font.BOLD, 36));
        bannerText.setBounds(480, 80, 300, 50);
        banner.add(bannerText);

        JLabel dots = new JLabel("● ● ●");
        dots.setFont(new Font("Arial", Font.BOLD, 18));
        dots.setForeground(Color.WHITE);
        dots.setBounds(540, 155, 80, 30);
        banner.add(dots);

        mainPanel.add(banner);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBounds(0, 320, 1200, 50);

        String[] menuItems = {"Home", "Departments", "Recently Added", "New Arrivals"};

        for (String item : menuItems) {
            JLabel menuLabel = new JLabel(item, SwingConstants.CENTER);
            menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
            menuLabel.setForeground(new Color(20, 40, 70));
            menuLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            menuLabel.setPreferredSize(new Dimension(150, 30));
            menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
            menuPanel.add(menuLabel);

            if (item.equals("Departments")) {
                JPopupMenu deptMenu = new JPopupMenu();
                String[] departments = {"BASIC ED", "CBA", "CHM", "CCS", "COF"};

                for (String dept : departments) {
                    JMenuItem menuItem = new JMenuItem(dept);
                    menuItem.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "You selected: " + dept);
                    });
                    deptMenu.add(menuItem);
                }

                menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        deptMenu.show(menuLabel, 0, menuLabel.getHeight());
                    }
                });
            } else {
                menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        JOptionPane.showMessageDialog(null, item + " clicked!");
                    }
                });
            }
        }

        mainPanel.add(menuPanel);
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(null);
            categoryPanel.setBackground(new Color(233, 236, 239));
            categoryPanel.setBounds(50, 370, 1100, 320);
            categoryPanel.setBorder(new RoundedBorder(30, Color.GRAY, 4));

            JLabel ccsTitle = new JLabel("CCS", SwingConstants.CENTER);
            ccsTitle.setFont(new Font("Arial", Font.BOLD, 28));
            ccsTitle.setForeground(new Color(20, 40, 100));
            ccsTitle.setBounds(0, 10, 1100, 40);
            categoryPanel.add(ccsTitle);

            JLabel subtitle = new JLabel("COLLEGE OF COMPUTER STUDIES", SwingConstants.CENTER);
            subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
            subtitle.setBounds(0, 45, 1100, 30);
            categoryPanel.add(subtitle);


            String[] images = {
            "c:\\Users\\ASUS\\Documents\\CCS_Image1.png",
            "c:\\Users\\ASUS\\Documents\\CCS_Image2.png",
            "c:\\Users\\ASUS\\Documents\\CCS_Image3.png",
            "c:\\Users\\ASUS\\Documents\\CCS_Image4.png"
            };

            for (int i = 0; i < 4; i++) {
            JPanel block = new JPanel();
            block.setBackground(Color.WHITE);
            block.setBounds(70 + (i * 250), 100, 200, 180);
            block.setBorder(new RoundedBorder(20, Color.GRAY, 3));
            block.setLayout(new BorderLayout());

            ImageIcon icon = new ImageIcon(images[i]);
            Image img = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            block.add(imgLabel);

            categoryPanel.add(block);
        }

        mainPanel.add(categoryPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AfterLoginPage());
    }
}
