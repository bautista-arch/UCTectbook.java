import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class AfterLoginPage extends JFrame {

    // Top-only border for menu panel
    static class TopBorder extends AbstractBorder {
        private final int thickness;
        private final Color color;

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

    // Rounded border for search bar and User button
    static class RoundedFieldBorder extends AbstractBorder {
        private final int radius;

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
        private final int radius;
        private final Color color;
        private final int thickness;

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

    private final JPanel mainPanel;
    private JPanel categoryPanel;
    private JPanel banner;
    private final JScrollPane scrollPane;

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

        // Search bar
        JTextField searchBar = new JTextField("Search bar");
        searchBar.setBounds(120, 20, 700, 32);
        searchBar.setHorizontalAlignment(JTextField.CENTER);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        searchBar.setOpaque(false);
        searchBar.setBorder(new RoundedFieldBorder(20));
        topNav.add(searchBar);

        // Cart icon
        JLabel cartIcon = new JLabel(new ImageIcon("c:\\Users\\ASUS\\Documents\\ImageFile\\cart.png"));
        cartIcon.setBounds(850, 15, 40, 40);
        cartIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topNav.add(cartIcon);
        cartIcon.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        new CartPage();  // Open the cart window
    }
});

        // User Button
        JButton userButton = new JButton("User");
        userButton.setBounds(900, 20, 120, 32);
        userButton.setFont(new Font("Arial", Font.BOLD, 14));
        userButton.setBackground(Color.WHITE);
        userButton.setOpaque(false);
        userButton.setBorder(new RoundedFieldBorder(20));
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

        // Arrays for department info
        String[] images;
        String[] titles;
        String[] authors;
        String[] prices;
        String[] descriptions;

        switch (department) {
            case "CBA":
                subtitle.setText("COLLEGE OF BUSINESS ADMINISTRATION");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CBA_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CBA_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CBA_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CBA_Image4.png"};
                titles = new String[]{"CBA-FOUND01","CBA-MARK02","CBA-LAW03","CBA-ACC04"};
                authors = new String[]{"Author A","Author B","Author C","Author D"};
                prices = new String[]{"₱3500.00","₱4200.00","₱3800.00","₱4500.00"};
                descriptions = new String[]{
                        "Intro to Business fundamentals...",
                        "Marketing Management text...",
                        "Business Law overview...",
                        "Accounting & Finance guide..."
                };
                break;
            case "CHM":
                subtitle.setText("COLLEGE OF HOSPITALITY MANAGEMENT");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CHM_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CHM_Image4.png"};
                titles = new String[]{"CHM-FOP01","CHM-FNB02","CHM-HSK03","CHM-HTG04"};
                authors = new String[]{"Auth E","Auth F","Auth G","Auth H"};
                prices = new String[]{"₱2800.00","₱3200.00","₱3000.00","₱2700.00"};
                descriptions = new String[]{
                        "Front Office procedures...",
                        "Food & Beverage services...",
                        "Housekeeping operations guide...",
                        "Hospitality & tourism guide..."
                };
                break;
            case "COF":
                subtitle.setText("COLLEGE OF FINE ARTS");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\COF_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\COF_Image4.png"};
                titles = new String[]{"COF-DRW01","COF-CLR02","COF-ILL03","COF-DES04"};
                authors = new String[]{"Artist A","Artist B","Artist C","Artist D"};
                prices = new String[]{"₱2200.00","₱2500.00","₱2600.00","₱3000.00"};
                descriptions = new String[]{
                        "Fundamentals of Drawing...",
                        "Color Theory Essentials...",
                        "Modern illustration guide...",
                        "Creative visual design approaches..."
                };
                break;
            default: // CCS
                subtitle.setText("COLLEGE OF COMPUTER STUDIES");
                images = new String[]{
                        "c:\\Users\\ASUS\\Documents\\CCS_Image1.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image2.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image3.png",
                        "c:\\Users\\ASUS\\Documents\\CCS_Image4.png"};
                titles = new String[]{"CC-COMPROG11","CC-COMRPOG12/IT-OOPROG21","IT-SAD21","CCS-DS04"};
                authors = new String[]{
                        "Hassan Afyouni, Ed.D.","Author Java","System Author","Data Author"};
                prices = new String[]{"₱5000.00","₱5000.00","₱5000.00","₱4500.00"};
                descriptions = new String[]{
                        "A Structured Programming Approach in C...",
                        "Java Programming 8th Ed...",
                        "Systems Analysis & Design...",
                        "Data Structures Overview..."
                };
                break;
        }

        // Preload all images for smooth click
        ImageIcon[] cachedImages = new ImageIcon[4];
        for (int i = 0; i < 4; i++) {
            ImageIcon raw = new ImageIcon(images[i]);
            Image scaled = raw.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
            cachedImages[i] = new ImageIcon(scaled);
        }

        // Create clickable image blocks
        for (int i = 0; i < 4; i++) {
            String t = titles[i], a = authors[i], p = prices[i], d = descriptions[i];
            String imgPath = images[i];
            JPanel block = new JPanel(new BorderLayout());
            block.setBackground(Color.WHITE);
            block.setBounds(70 + (i * 250), 100, 200, 180);
            block.setBorder(new RoundedBorder(20, Color.GRAY, 2));

            JLabel imgLabel = new JLabel(cachedImages[i]);
            imgLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            imgLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showBookPopup(imgPath, t, a, p, d);
                }
            });

            block.add(imgLabel);
            categoryPanel.add(block);
        }

        mainPanel.add(categoryPanel);
        mainPanel.repaint();
    }

    private void showBookPopup(String imagePath, String title, String author, String price, String description) {
        final JDialog popup = new JDialog(this, true);
        popup.setUndecorated(true); 
        popup.setSize(1000, 520);
        popup.setLayout(null);
        popup.setLocationRelativeTo(this);

        JPanel outer = new JPanel(null);
        outer.setBackground(Color.WHITE);
        outer.setBorder(new RoundedBorder(18, new Color(30, 60, 110), 2));
        outer.setBounds(0, 0, 1000, 520);
        popup.add(outer);

        JLabel closeX = new JLabel("✕", SwingConstants.CENTER);
        closeX.setFont(new Font("Arial", Font.BOLD, 22));
        closeX.setBounds(960, 10, 30, 30);
        closeX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeX.setForeground(Color.DARK_GRAY);
        closeX.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { popup.dispose(); }
            @Override public void mouseEntered(MouseEvent e) { closeX.setForeground(Color.RED); }
            @Override public void mouseExited(MouseEvent e) { closeX.setForeground(Color.DARK_GRAY); }
        });
        outer.add(closeX);

        JPanel imgContainer = new JPanel(null);
        imgContainer.setBackground(new Color(245, 245, 245));
        imgContainer.setBounds(30, 30, 340, 460);
        imgContainer.setBorder(new RoundedBorder(12, new Color(130, 90, 220), 3));
        outer.add(imgContainer);

        ImageIcon raw = new ImageIcon(imagePath);
        Image scaled = raw.getImage().getScaledInstance(280, 380, Image.SCALE_SMOOTH);
        JLabel cover = new JLabel(new ImageIcon(scaled));
        cover.setBounds(30, 30, 280, 380);
        imgContainer.add(cover);

        JPanel details = new JPanel(null);
        details.setBackground(Color.WHITE);
        details.setBounds(390, 30, 560, 460);
        outer.add(details);

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font("Arial", Font.BOLD, 28));
        titleLbl.setBounds(10, 10, 540, 40);
        details.add(titleLbl);

        JLabel authorLbl = new JLabel("AUTHOR: " + author);
        authorLbl.setFont(new Font("Arial", Font.BOLD, 14));
        authorLbl.setBounds(10, 80, 540, 22);
        details.add(authorLbl);

        JLabel priceLbl = new JLabel(price);
        priceLbl.setFont(new Font("Arial", Font.BOLD, 26));
        priceLbl.setForeground(new Color(150, 10, 10));
        priceLbl.setBounds(10, 115, 200, 30);
        details.add(priceLbl);

        JSeparator sep = new JSeparator();
        sep.setBounds(10, 160, 540, 2);
        details.add(sep);

        JLabel about = new JLabel("ABOUT THIS ITEM");
        about.setFont(new Font("Arial", Font.BOLD, 14));
        about.setBounds(10, 170, 200, 20);
        details.add(about);

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Arial", Font.PLAIN, 13));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setBounds(10, 200, 540, 160);
        descScroll.setBorder(BorderFactory.createEmptyBorder());
        details.add(descScroll);

        JButton addToCart = new JButton("ADD TO CART");
        addToCart.setFont(new Font("Arial", Font.BOLD, 14));
        addToCart.setBounds(40, 380, 220, 50);
        addToCart.setFocusPainted(false);
        addToCart.setBorder(new RoundedFieldBorder(25));
        addToCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addToCart.addActionListener(e -> {
    GlobalCartList.cartItems.add(new CartItem(title, price, imagePath));
    JOptionPane.showMessageDialog(popup, title + " added to cart.");
});

        details.add(addToCart);

        JButton purchase = new JButton("PURCHASE");
        purchase.setFont(new Font("Arial", Font.BOLD, 14));
        purchase.setBounds(320, 380, 220, 50);
        purchase.setFocusPainted(false);
        purchase.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        purchase.addActionListener(e -> JOptionPane.showMessageDialog(popup, "Proceeding to purchase: " + title));
        purchase.setBackground(new Color(10, 40, 90));
        purchase.setForeground(Color.WHITE);
        purchase.setBorder(new RoundedFieldBorder(25));
        details.add(purchase);

        popup.getRootPane().registerKeyboardAction(e -> popup.dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        popup.setModal(true);
        popup.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AfterLoginPage::new);
    }
}
