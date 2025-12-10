import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class AfterLoginPage extends JFrame {

    // Top-only border for menu panel
    static class TopBorder extends AbstractBorder {
        private int thickness;
        private Color color;

        public TopBorder(int thickness, Color color) {
            this.thickness = thickness;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.fillRect(x, y, width, thickness);
        }
    }

    // NEW: Rounded border for search bar and User button
    static class RoundedFieldBorder extends AbstractBorder {
        private int radius;

        public RoundedFieldBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x + 1, y + 1, w - 3, h - 3, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 12);
        }
    }

    // Rounded border for category blocks
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
            g2.drawRoundRect(x + thickness / 2, y + thickness / 2, width - thickness, height - thickness,
                    radius, radius);
        }
    }

    private JPanel mainPanel;
    private JPanel categoryPanel;
    private JPanel banner;
    private JScrollPane scrollPane;

    public AfterLoginPage() {
        setTitle("UC Textbook Portal - Home");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1200, 1000));

        createTopNavigation();
        createBanner("BANNER", "");
        createMenuPanel();
        createCategoryPanel("CCS");

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setContentPane(scrollPane);
        setVisible(true);
    }

    private void createTopNavigation() {
        JPanel topNav = new JPanel(null);
        topNav.setBackground(Color.WHITE);
        topNav.setBounds(0, 0, 1200, 70);

        // UC Logo
        JLabel ucLogo = new JLabel(new ImageIcon("c:\\Users\\ASUS\\Documents\\ImageFile\\UcSmallLogo.png"));
        ucLogo.setBounds(20, 10, 60, 50);
        topNav.add(ucLogo);

        // Search bar (FULLY ROUNDED)
        JTextField searchBar = new JTextField("Search bar");
        searchBar.setBounds(120, 20, 700, 32);
        searchBar.setHorizontalAlignment(JTextField.CENTER);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        searchBar.setOpaque(false);
        searchBar.setBorder(new RoundedFieldBorder(20));  // ⭐ rounded border
        topNav.add(searchBar);

        // Cart icon
        JLabel cartIcon = new JLabel(new ImageIcon("c:\\Users\\ASUS\\Documents\\cart.png"));
        cartIcon.setBounds(850, 15, 40, 40);
        cartIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topNav.add(cartIcon);

        // User Button (ROUNDED)
        JButton userButton = new JButton("User");
        userButton.setBounds(900, 20, 120, 32);
        userButton.setFont(new Font("Arial", Font.BOLD, 14));
        userButton.setBackground(Color.WHITE);
        userButton.setOpaque(false);
        userButton.setBorder(new RoundedFieldBorder(20)); // ⭐ rounded button
        topNav.add(userButton);

        mainPanel.add(topNav);
    }

    private void createBanner(String titleText, String subtitleText) {
        if (banner != null)
            mainPanel.remove(banner);

        String[] bannerPaths = {
                "c:\\Users\\ASUS\\Documents\\Banner1.png",
                "c:\\Users\\ASUS\\Documents\\Banner2.png",
                "c:\\Users\\ASUS\\Documents\\Banner3.png"
        };

        ArrayList<ImageIcon> imageList = new ArrayList<>();
        for (String path : bannerPaths) {
            ImageIcon raw = new ImageIcon(path);
            Image scaled = raw.getImage().getScaledInstance(1100, 220, Image.SCALE_SMOOTH);
            imageList.add(new ImageIcon(scaled));
        }

        JLabel imageLabel = new JLabel(imageList.get(0));
        imageLabel.setBounds(0, 0, 1100, 220);

        banner = new JPanel(null);
        banner.setBounds(40, 100, 1100, 220);
        banner.setOpaque(false);
        banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        banner.add(imageLabel);

        JLabel bannerText = new JLabel(titleText, SwingConstants.CENTER);
        bannerText.setForeground(Color.WHITE);
        bannerText.setFont(new Font("Arial", Font.BOLD, 36));
        bannerText.setBounds(0, 60, 1100, 50);
        banner.add(bannerText);

        if (!subtitleText.isEmpty()) {
            JLabel subtitle = new JLabel(subtitleText, SwingConstants.CENTER);
            subtitle.setForeground(Color.WHITE);
            subtitle.setFont(new Font("Arial", Font.PLAIN, 20));
            subtitle.setBounds(0, 120, 1100, 40);
            banner.add(subtitle);
        }

        final int[] index = {0};
        final int[] startX = {0};

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX[0] = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int endX = e.getX();
                if (startX[0] - endX > 40)
                    index[0] = (index[0] + 1) % imageList.size();
                else if (endX - startX[0] > 40)
                    index[0] = (index[0] - 1 + imageList.size()) % imageList.size();
                imageLabel.setIcon(imageList.get(index[0]));
            }
        });

        Timer autoSlide = new Timer(4000, e -> {
            index[0] = (index[0] + 1) % imageList.size();
            imageLabel.setIcon(imageList.get(index[0]));
        });
        autoSlide.start();

        mainPanel.add(banner);
        mainPanel.repaint();
    }

    private void createMenuPanel() {
        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setBounds(50, 340, 1100, 60);
        menuPanel.setBorder(new TopBorder(4, Color.BLACK));

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
                        createBanner("BANNER", "");
                        createCategoryPanel("CCS");
                    } else if (item.equals("Departments")) {
                        JPopupMenu deptMenu = new JPopupMenu();
                        deptMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                        String[] departments = {"CBA", "CHM", "CCS", "COF"};

                        for (String dept : departments) {
                            JMenuItem menuItem = new JMenuItem(dept);
                            menuItem.setPreferredSize(new Dimension(150, 40));
                            menuItem.addActionListener(ae -> {
                                createBanner(dept, "");
                                createCategoryPanel(dept);
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

        mainPanel.add(menuPanel);
    }

    private void createCategoryPanel(String department) {
        if (categoryPanel != null)
            mainPanel.remove(categoryPanel);

        categoryPanel = new JPanel(null);
        categoryPanel.setBackground(new Color(233, 236, 239));
        categoryPanel.setBounds(50, 420, 1100, 320);
        categoryPanel.setBorder(new RoundedBorder(30, Color.GRAY, 2));

        JLabel title = new JLabel(department, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(20, 40, 100));
        title.setBounds(10, 10, 1100, 40);
        categoryPanel.add(title);

        JLabel subtitle = new JLabel("", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setBounds(0, 45, 1100, 30);
        categoryPanel.add(subtitle);

        String[] images;
        switch (department) {
            case "CBA":
                subtitle.setText("COLLEGE OF BUSINESS ADMINISTRATION");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CBA_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CBA_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CBA_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CBA_Image4.png"};
                break;
            case "CHM":
                subtitle.setText("COLLEGE OF HOSPITALITY MANAGEMENT");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CHM_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image4.png"};
                break;
            case "COF":
                subtitle.setText("COLLEGE OF FINE ARTS");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\COF_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image4.png"};
                break;
            default:
                subtitle.setText("COLLEGE OF COMPUTER STUDIES");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CCS_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image4.png"};
                break;
        }

        for (int i = 0; i < 4; i++) {
            JPanel block = new JPanel(new BorderLayout());
            block.setBackground(Color.WHITE);
            block.setBounds(70 + (i * 250), 100, 200, 180);
            block.setBorder(new RoundedBorder(20, Color.GRAY, 2));

            ImageIcon icon = new ImageIcon(images[i]);
            Image img = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            block.add(imgLabel);

            categoryPanel.add(block);
        }

        mainPanel.add(categoryPanel);
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AfterLoginPage::new);
    }
}
