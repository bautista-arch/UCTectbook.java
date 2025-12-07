import java.awt.*;
import javax.swing.*;

public class AfterLoginPage extends JFrame {

    public AfterLoginPage() {

        setTitle("UC Textbook Portal - Home");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        addTopNav();
        addBanner();
        addMenu();
        addCategoryPanel();

        setVisible(true);
    }

    // ---------------- TOP NAVIGATION BAR ----------------
    private void addTopNav() {
        JPanel topNav = new JPanel(null);
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

        add(topNav);
    }

    // ---------------- BANNER SECTION ----------------
    private void addBanner() {
        JPanel banner = new JPanel(null);
        banner.setBackground(Color.BLACK);
        banner.setBounds(50, 90, 1100, 220);
        banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        JLabel bannerText = new JLabel("BANNER");
        bannerText.setForeground(Color.WHITE);
        bannerText.setFont(new Font("Arial", Font.BOLD, 36));
        bannerText.setBounds(430, 80, 300, 50);
        banner.add(bannerText);

        JLabel dots = new JLabel("● ● ●");
        dots.setFont(new Font("Arial", Font.BOLD, 18));
        dots.setForeground(Color.WHITE);
        dots.setBounds(520, 150, 80, 30);
        banner.add(dots);

        add(banner);
    }

    // ---------------- MENU BAR ----------------
    private void addMenu() {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 5));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBounds(0, 320, 1200, 40);

        String[] menuItems = {"Home", "Departments", "Recently Added", "New Arrivals"};

        for (String item : menuItems) {
            JLabel menuLabel = new JLabel(item);
            menuLabel.setFont(new Font("Arial", Font.BOLD, 14));
            menuLabel.setForeground(new Color(20, 40, 70));
            menuPanel.add(menuLabel);
        }

        add(menuPanel);
    }

    // ---------------- CATEGORY PANEL ----------------
    private void addCategoryPanel() {
        JPanel category = new JPanel(null);
        category.setBackground(new Color(233, 236, 239));
        category.setBounds(50, 370, 1100, 320);
        category.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        JLabel ccsTitle = new JLabel("CCS", SwingConstants.CENTER);
        ccsTitle.setFont(new Font("Arial", Font.BOLD, 28));
        ccsTitle.setForeground(new Color(20, 40, 100));
        ccsTitle.setBounds(0, 10, 1100, 40);
        category.add(ccsTitle);

        JLabel subtitle = new JLabel("COLLEGE OF COMPUTER STUDIES", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setBounds(0, 45, 1100, 30);
        category.add(subtitle);

        // 4 rectangular blocks
        for (int i = 0; i < 4; i++) {
            JPanel block = new JPanel();
            block.setBackground(Color.WHITE);
            block.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            block.setBounds(70 + (i * 250), 100, 200, 180);
            category.add(block);
        }

        add(category);
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        new AfterLoginPage();
    }
}
