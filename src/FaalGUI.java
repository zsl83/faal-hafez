import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FaalGUI extends JFrame implements ActionListener {

    private static final String GET_URL = "https://faal.spclashers.workers.dev/api";
    private JButton getFaal;
    private JButton backButton;
    private JPanel homePage;
    private JPanel faalPage;

    public FaalGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("فال حافظ");
        this.setFont(new Font("Arial", Font.BOLD, 14));

        homePage = new JPanel();
        faalPage = new JPanel();
        HomePage();

        this.setSize(700, 900);
        this.setVisible(true);
    }

    private static Faal getFaal() throws IOException {
        URL url = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            Faal faal = mapper.readValue(response.toString(), Faal.class);

            // Replace all variations of newline characters with a single standard newline character
            String cleanedPoem = faal.getPoem().replace("\\r", "").replace("\\n", "\n");
            faal.setPoem(cleanedPoem);

            return faal;
        } else {
            throw new IOException("Failed to fetch Faal: " + responseCode);
        }
    }

    public void HomePage() {
        homePage.removeAll();
        homePage.setLayout(null);

        JLabel label2 = new JLabel("نیت کنید ...");
        label2.setFont(new Font("Arial", Font.BOLD, 30));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setForeground(Color.WHITE);
        label2.setBounds(280, 300, 200, 50);

        JLabel label3 = new JLabel("در صورت اتمام نیت خود، روی دکمه‌ی زیر کلیک کنید.");
        label3.setFont(new Font("Arial", Font.BOLD, 30));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150, 400, 400, 50);

        getFaal = new JButton("دریافت فال");
        getFaal.setFont(new Font("Arial", Font.BOLD, 35));
        getFaal.setBackground(new Color(60,90,100));
        getFaal.setForeground(Color.white);
        getFaal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        getFaal.setBounds(250, 600, 200, 50);
        getFaal.addActionListener(this);
        getFaal.setFocusable(false);

        JLabel image = new JLabel();
        ImageIcon image1 = new ImageIcon(getClass().getResource("/hafez2.png"));
        image.setIcon(image1);
        image.setBounds(200, 50, 300, 200);

        homePage.add(image);
        homePage.add(label2);
        homePage.add(label3);
        homePage.add(getFaal);
        homePage.setBackground(new Color(30, 60, 70));

        this.setContentPane(homePage);
    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void FaalPage(String poem, String interpretation) {
        faalPage.removeAll();
        faalPage.setLayout(new BoxLayout(faalPage, BoxLayout.Y_AXIS));
        faalPage.setAlignmentX(Component.CENTER_ALIGNMENT);
        faalPage.setAlignmentY(Component.CENTER_ALIGNMENT);

        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label = new JLabel("شعر");
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JPanel poemPanel = new JPanel();
        poemPanel.setLayout(new GridBagLayout());

        JTextArea label2 = new JTextArea(poem);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setForeground(Color.WHITE);
        label2.setEditable(false);
        label2.setAlignmentY(Component.CENTER_ALIGNMENT);
        label2.setBackground(null);

        JLabel label3 = new JLabel("تفسیر");
        label3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setFont(new Font("Arial", Font.BOLD, 20));
        label3.setForeground(Color.WHITE);

        JTextArea label4 = new JTextArea(interpretation);
        label4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label4.setEditable(false);
        label4.setBackground(null);
        label4.setLineWrap(true);
        label4.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        label4.setFont(new Font("Arial", Font.BOLD, 20));
        label4.setForeground(Color.WHITE);

        backButton = new JButton("بازگشت");
        backButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.setFocusable(false);
        backButton.setBackground(new Color(56,70 ,110));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);

        faalPage.add(label);
        poemPanel.add(label2);
        faalPage.add(poemPanel);
        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));
        faalPage.add(label3);
        faalPage.add(Box.createRigidArea(new Dimension(0, 40)));
        faalPage.add(label4);
        faalPage.add(Box.createRigidArea(new Dimension(0, 40)));
        faalPage.add(backButton);
        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        faalPage.setBackground(new Color(50,70,90));
        poemPanel.setBackground(new Color(50,70,80));
        this.setContentPane(faalPage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getFaal) {
            this.getContentPane().removeAll();

            try {
                Faal faal = getFaal();
                FaalPage(faal.getPoem(), faal.getInterpretation());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            this.revalidate();
            this.repaint();
        }

        if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            HomePage();
            this.revalidate();
            this.repaint();
        }
    }

}







