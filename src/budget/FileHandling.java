package budget;

import budget.enums.PurchaseActions;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandling {
    static void save(Data data, String filePath) {
        File file = new File(filePath);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            writeFile(data, printWriter);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void writeFile(Data data, PrintWriter printWriter) {
        printWriter.println(data.getBalance());
        printWriter.println(data.getTotalPurchasesAmount());
        for (var purchaseType :
                PurchaseActions.values()) {
            if (!data.getPurchaseMap().get(purchaseType.ordinal()).isEmpty()) {
                printWriter.println("BOC"); //beginning of categorie marker
                printWriter.println(purchaseType.ordinal());
                printWriter.println(data.getAmountMap().get(purchaseType.ordinal()));
                data.getPurchaseMap().get(purchaseType.ordinal()).forEach((value) -> printWriter.println(value.toString()));
                printWriter.println("EOC"); //End of categorie marker
            }
        }
        printWriter.println("EOF"); //End of file marker
    }

    static Data load(Data data, String filePath) {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            read(data, scanner);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return data;
    }

    static private void read(Data data, Scanner scanner) {
        if (scanner.hasNextLine()) {
            data.setBalance(scanner.nextDouble());
            data.setTotalPurchasesAmount(scanner.nextDouble());
            while ("BOC".equals(scanner.next())) {
                int ordinal = scanner.nextInt();
                data.getAmountMap().put(ordinal, scanner.nextDouble());
                scanner.nextLine();
                while (true) {
                    String buffer = scanner.nextLine();
                    if ("EOC".equals(buffer)) {
                        break;
                    } else {
                        String[] splited = buffer.split(" \\$");
                        if (splited.length == 2) {
                            data.getPurchaseMap().get(ordinal).add(new PurchaseData(Double.parseDouble(splited[1]), splited[0]));
                        } else {
                            StringBuilder stringBuilder = new StringBuilder(splited[0]);
                            for (int i = 1; i < splited.length - 1; i++) {
                                stringBuilder.append(" $");
                                stringBuilder.append(splited[i]);
                            }
                            data.getPurchaseMap().get(ordinal).add(new PurchaseData(Double.parseDouble(splited[splited.length - 1]), stringBuilder.toString()));
                        }
                    }
                }
            }
        }
    }
}
