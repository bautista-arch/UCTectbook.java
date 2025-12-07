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
            g2.drawRoundRect(
                x + thickness / 2,
                y + thickness / 2,
                width - thickness,
                height - thickness,
                radius, radius
            );
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
        mainPanel.setPreferredSize(new Dimension(1200, 900));

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
    }

    private void createBanner(String titleText, String subtitleText) {
        if (banner != null) mainPanel.remove(banner); // remove old banner

        banner = new JPanel();
        banner.setLayout(null);
        banner.setBackground(Color.BLACK);
        banner.setBounds(40, 100, 1100, 220);
        banner.setBorder(new RoundedBorder(30, Color.BLACK, 4));

        JLabel bannerText = new JLabel(titleText, SwingConstants.CENTER);
        bannerText.setForeground(Color.WHITE);
        bannerText.setFont(new Font("Arial", Font.BOLD, 36));
        bannerText.setBounds(0, 80, 1100, 50);
        banner.add(bannerText);

        if (!subtitleText.isEmpty()) {
            JLabel subtitle = new JLabel(subtitleText, SwingConstants.CENTER);
            subtitle.setForeground(Color.WHITE);
            subtitle.setFont(new Font("Arial", Font.PLAIN, 20));
            subtitle.setBounds(0, 140, 1100, 40);
            banner.add(subtitle);
        }

        mainPanel.add(banner);
        mainPanel.repaint();
    }

    private void createMenuPanel() {
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

            if (item.equals("Home")) {
                menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        createBanner("BANNER", "");
                        createCategoryPanel("CCS");
                    }
                });
            } else if (item.equals("Departments")) {

                JPopupMenu deptMenu = new JPopupMenu();
                deptMenu.setBorder(new RoundedBorder(20, Color.GRAY, 2));

                String[] departments = {"CBA", "CHM", "CCS", "COF"};
                int fixedWidth = menuLabel.getPreferredSize().width;

                for (String dept : departments) {
                    JMenuItem menuItem = new JMenuItem(dept);
                    menuItem.setPreferredSize(new Dimension(fixedWidth, 30));

                    menuItem.addActionListener(e -> {
                        switch (dept) {
                            case "CBA":
                                createBanner("CBA", "COLLEGE OF BUSINESS ADMINISTRATION");
                                createCategoryPanel("CBA");
                                break;
                            case "CHM":
                                createBanner("CHM", "COLLEGE OF HOSPITALITY MANAGEMENT");
                                createCategoryPanel("CHM");
                                break;
                            case "CCS":
                                createBanner("CCS", "COLLEGE OF COMPUTER STUDIES");
                                createCategoryPanel("CCS");
                                break;
                            case "COF":
                                createBanner("COF", "COLLEGE OF FINE ARTS");
                                createCategoryPanel("COF");
                                break;
                        }
                    });

                    deptMenu.add(menuItem);
                }

                menuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        deptMenu.pack();
                        int labelWidth = menuLabel.getWidth();
                        int popupWidth = deptMenu.getPreferredSize().width;
                        int x = (labelWidth - popupWidth) / 2;
                        int y = menuLabel.getHeight();
                        deptMenu.show(menuLabel, x, y);
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
    }

    private void createCategoryPanel(String department) {
        if (categoryPanel != null) mainPanel.remove(categoryPanel);

        categoryPanel = new JPanel();
        categoryPanel.setLayout(null);
        categoryPanel.setBackground(new Color(233, 236, 239));
        categoryPanel.setBounds(50, 370, 1100, 320);
        categoryPanel.setBorder(new RoundedBorder(30, Color.GRAY, 4));

        JLabel title = new JLabel(department, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(20, 40, 100));
        title.setBounds(0, 10, 1100, 40);
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
                        "c:\\Users\\ASUS\\Documents\\CBA_Image4.png"
                };
                break;
            case "CHM":
                subtitle.setText("COLLEGE OF HOSPITALITY MANAGEMENT");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CHM_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image4.png"
                };
                break;
            case "COF":
                subtitle.setText("COLLEGE OF FINE ARTS");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\COF_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image4.png"
                };
                break;
            default: // CCS
                subtitle.setText("COLLEGE OF COMPUTER STUDIES");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CCS_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image4.png"
                };
                break;
        }

        for (int i = 0; i < 4; i++) {
            JPanel block = new JPanel(new BorderLayout());
            block.setBackground(Color.WHITE);
            block.setBounds(70 + (i * 250), 100, 200, 180);
            block.setBorder(new RoundedBorder(20, Color.GRAY, 3));

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
