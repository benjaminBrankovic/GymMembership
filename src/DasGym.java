import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-10-14
 * Time: 22:01
 * Project: BigJavaMap
 */
public class DasGym {

    //Metod som kollar om person har atkivt medlemskap från dagens datum och ett år tillbaka
    public static boolean isMemberActive(Member m) {
        if (m.getDateOfMembership() == null)
            throw new NullPointerException("Date of bought membership may not be null");
        LocalDate lastValidDate = LocalDate.now().minusYears(1);
        return lastValidDate.isBefore(m.getDateOfMembership());
    }

    public DasGym() {

        String filePath = "src/customers.txt";
        String folderPath = "src/ActiveCustomerFolder";

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Välkommen till gymmet! Vänligen ange namn eller personnummer.", "Gymregister", JOptionPane.INFORMATION_MESSAGE);

                if (input == null)
                    System.exit(0);

                Member member = IOUtil.findMemberInFile(filePath, input);

                var isMemberValid = false;
                if (member != null)
                    isMemberValid = isMemberActive(member);

                if (member == null) {
                    JOptionPane.showMessageDialog(null, "Personen hittas ej i systemet, vänligen pröva igen.",
                            "Not Found", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                if (isMemberValid) {
                    JOptionPane.showMessageDialog(null, "Aktivt medlemskap");
                    IOUtil.writeDataToFile(folderPath, member);
                    JOptionPane.showMessageDialog(null, "Incheckad" + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } else {
                    JOptionPane.showMessageDialog(null, "Medlemskap har upphört");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DasGym gym = new DasGym();
    }
}

