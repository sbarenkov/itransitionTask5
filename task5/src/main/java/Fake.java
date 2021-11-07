import au.com.bytecode.opencsv.CSVWriter;
import com.github.javafaker.Faker;


import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

public class Fake {
    public String fullName, address, phoneNumber;

    public Fake(String fullName, String address, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static void createFakeUsers(int recordsNum, String region) throws IOException {
        if (recordsNum < 1) {
            System.out.println("The number of records is small");
        }
        else {
            Faker faker = new Faker(new Locale(region));
            for (int i = 0; i < recordsNum; i++) {
                Fake fakeUser = new Fake(faker.name().fullName(),
                        String.join(",", faker.address().city(), faker.address().streetAddress()),
                        faker.phoneNumber().cellPhone());
                fakeUser.toCSV();
            }
        }
    }

    public void toCSV() throws IOException {
        StringWriter writer = new StringWriter();
        CSVWriter csvData = new CSVWriter(writer, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                "");
        csvData.writeNext(this.fullName, this.address, this.phoneNumber);
        csvData.close();
        System.out.println(writer);
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            if (args[0].matches("\\d+")) {
                createFakeUsers(Integer.parseInt(args[0]), args[1]);
            }
        }
    }
}