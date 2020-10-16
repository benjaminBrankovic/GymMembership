import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-10-14
 * Time: 21:34
 * Project: BigJavaMap
 */
public class IOUtil {

    //Metod som kollar igenom alla användare i filen och letar efter det som skrivs in. Ignorerar stora och småbokstäver
    //Splitar raderna i filen och letar efter personnummer, förnamn och efternamn.
    public static Member findMemberInFile(String filePath, String input) throws IOException {
        Path path = Paths.get(filePath);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String[] currentLine = scanner.nextLine().split(", ");
                if (currentLine.length == 2) {
                    String SSN = currentLine[0];
                    String fullName = currentLine[1];
                    String firstName = fullName.split(" ")[0];
                    String lastName = fullName.split(" ")[1];
                    if (SSN.equalsIgnoreCase(input) || fullName.equalsIgnoreCase(input) || firstName.equalsIgnoreCase(input) || lastName.equalsIgnoreCase(input)) {
                        if (!scanner.hasNextLine())
                            throw new NullPointerException("Date not found");

                        var date = LocalDate.parse(scanner.nextLine());

                        return new Member(fullName, SSN, date);
                    }
                }
            }
            return null;
        }
    }

    //Metod som skriver in att person checkat in på gymmet. Fortmaterar datum och tid.
    public static void writeDataToFile(String folderPath, Member member) {
        Path outFilePath = Paths.get(folderPath + "\\" + member.getFullName() + " " + member.getSocialSecurityNumber() + ".txt");
        try (FileWriter w = new FileWriter(outFilePath.toFile(), true)) {
            w.write("Kunden checkade in sig: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }
}